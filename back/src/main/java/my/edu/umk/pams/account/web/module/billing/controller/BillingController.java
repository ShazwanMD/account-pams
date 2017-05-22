package my.edu.umk.pams.account.web.module.billing.controller;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.*;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.security.integration.AcAutoLoginToken;
import my.edu.umk.pams.account.system.service.SystemService;
import my.edu.umk.pams.account.web.module.billing.vo.*;
import my.edu.umk.pams.account.workflow.service.WorkflowService;

import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static my.edu.umk.pams.account.AccountConstants.RECEIPT_REFERENCE_NO;

@Transactional
@RestController
@RequestMapping("/api/billing")
public class BillingController {

    private static final Logger LOG = LoggerFactory.getLogger(BillingController.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private BillingTransformer billingTransformer;

    @Autowired
    private AuthenticationManager authenticationManager;

    // todo(uda): limit, offset
    // todo(uda); filter

    // ==================================================================================================== //
    //  INVOICE
    // ==================================================================================================== //

    @RequestMapping(value = "/invoices/", method = RequestMethod.GET)
    public ResponseEntity<List<Invoice>> findInvoices() {
        List<AcInvoice> invoices = billingService.findInvoices("%", 0, 100);
        return new ResponseEntity<List<Invoice>>(billingTransformer.toInvoiceVos(invoices), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/{referenceNo}", method = RequestMethod.GET)
    public ResponseEntity<Invoice> findInvoiceByReferenceNo(@PathVariable String referenceNo) {
        AcInvoice invoice = (AcInvoice) billingService.findInvoiceByReferenceNo(referenceNo);
        return new ResponseEntity<Invoice>(billingTransformer.toInvoiceVo(invoice), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/{referenceNo}", method = RequestMethod.PUT)
    public ResponseEntity<Invoice> updateInvoice(@PathVariable String referenceNo, @RequestBody Invoice vo) {
        AcInvoice invoice = (AcInvoice) billingService.findInvoiceByReferenceNo(referenceNo);
        return new ResponseEntity<Invoice>(billingTransformer.toInvoiceVo(invoice), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/{referenceNo}/invoiceItems", method = RequestMethod.GET)
    public ResponseEntity<List<InvoiceItem>> findInvoiceItems(@PathVariable String referenceNo) {
        dummyLogin();
        AcInvoice invoice = billingService.findInvoiceByReferenceNo(referenceNo);
        return new ResponseEntity<List<InvoiceItem>>(billingTransformer
                .toInvoiceItemVos(billingService.findInvoiceItems(invoice)), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/{referenceNo}/invoiceItems", method = RequestMethod.POST)
    public ResponseEntity<String> addInvoiceItem(@PathVariable String referenceNo, @RequestBody InvoiceItem item) {
        dummyLogin();

        LOG.debug("referenceNo: {}", referenceNo);
        LOG.debug("chargeCode: {}", item.getChargeCode().getCode());

        AcInvoice invoice = billingService.findInvoiceByReferenceNo(referenceNo);
        AcInvoiceItem e = new AcInvoiceItemImpl();
        e.setChargeCode(accountService.findChargeCodeById(item.getChargeCode().getId()));
        e.setAmount(item.getAmount());
        e.setDescription(item.getDescription());
        billingService.addInvoiceItem(invoice, e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/{referenceNo}/invoiceItems/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateInvoiceItems(@PathVariable String referenceNo, @PathVariable Long id, @RequestBody InvoiceItem item) {
        dummyLogin();
        AcInvoice invoice = billingService.findInvoiceByReferenceNo(referenceNo);
        AcInvoiceItem e = billingService.findInvoiceItemById(item.getId());
        e.setChargeCode(accountService.findChargeCodeById(item.getChargeCode().getId()));
        e.setAmount(item.getAmount());
        e.setDescription(item.getDescription());
        billingService.updateInvoiceItem(invoice, e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/{referenceNo}/invoiceItems/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteInvoiceItems(@PathVariable String referenceNo, @PathVariable Long id) {
        dummyLogin();
        AcInvoice invoice = billingService.findInvoiceByReferenceNo(referenceNo);
        AcInvoiceItem e = billingService.findInvoiceItemById(id);
        billingService.deleteInvoiceItem(invoice, e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/assignedTasks", method = RequestMethod.GET)
    public ResponseEntity<List<InvoiceTask>> findAssignedInvoices() {
        dummyLogin();
        List<Task> tasks = billingService.findAssignedInvoiceTasks(0, 100);
        return new ResponseEntity<List<InvoiceTask>>(billingTransformer.toInvoiceTaskVos(tasks), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/pooledTasks", method = RequestMethod.GET)
    public ResponseEntity<List<InvoiceTask>> findPooledInvoices() {
        dummyLogin();
        List<Task> tasks = billingService.findPooledInvoiceTasks(0, 100);
        return new ResponseEntity<List<InvoiceTask>>(billingTransformer.toInvoiceTaskVos(tasks), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/startTask", method = RequestMethod.POST)
    public ResponseEntity<String> startInvoiceTask(@RequestBody Invoice vo) throws Exception {
        dummyLogin();

        AcAccount account = accountService.findAccountById(vo.getAccount().getId());
        AcInvoice invoice = new AcInvoiceImpl();
        invoice.setDescription(vo.getDescription());
        invoice.setTotalAmount(BigDecimal.ZERO);
        invoice.setBalanceAmount(BigDecimal.ZERO);
        invoice.setIssuedDate(new Date());
        invoice.setPaid(false);
        invoice.setSession(accountService.findCurrentAcademicSession());
        invoice.setAccount(account);
        return new ResponseEntity<String>(billingService.startInvoiceTask(invoice), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/viewTask/{taskId}", method = RequestMethod.GET)
    public ResponseEntity<InvoiceTask> findInvoiceTaskByTaskId(@PathVariable String taskId) {
        return new ResponseEntity<InvoiceTask>(billingTransformer
                .toInvoiceTaskVo(
                        billingService.findInvoiceTaskByTaskId(taskId)), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/claimTask", method = RequestMethod.POST)
    public ResponseEntity<String> claimInvoiceTask(@RequestBody InvoiceTask vo) {
        dummyLogin();
        Task task = billingService.findInvoiceTaskByTaskId(vo.getTaskId());
        workflowService.claimTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/releaseTask", method = RequestMethod.POST)
    public ResponseEntity<String> releaseInvoiceTask(@RequestBody InvoiceTask vo) {
        dummyLogin();
        Task task = billingService.findInvoiceTaskByTaskId(vo.getTaskId());
        workflowService.releaseTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/completeTask", method = RequestMethod.POST)
    public ResponseEntity<String> completeInvoiceTask(@RequestBody InvoiceTask vo) {
        dummyLogin();
        Task task = billingService.findInvoiceTaskByTaskId(vo.getTaskId());
        workflowService.completeTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/state/{state}", method = RequestMethod.GET)
    public ResponseEntity<List<Invoice>> findInvoicesByFlowState(@PathVariable String state) {
        List<AcInvoice> invoices = billingService.findInvoicesByFlowState(AcFlowState.valueOf(state));
        return new ResponseEntity<List<Invoice>>(billingTransformer.toInvoiceVos(invoices), HttpStatus.OK);
    }

    // ==================================================================================================== //
    //  RECEIPT
    // ==================================================================================================== //

    @RequestMapping(value = "/receipts/", method = RequestMethod.GET)
    public ResponseEntity<List<Receipt>> findReceipts() {
        List<AcReceipt> receipts = billingService.findReceipts("%", 0, 100);
        return new ResponseEntity<List<Receipt>>(billingTransformer.toReceiptVos(receipts), HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts/{referenceNo}", method = RequestMethod.GET)
    public ResponseEntity<Receipt> findReceiptByReferenceNo(@PathVariable String referenceNo) {
        AcReceipt receipt = (AcReceipt) billingService.findReceiptByReferenceNo(referenceNo);
        return new ResponseEntity<Receipt>(billingTransformer.toReceiptVo(receipt), HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts/{referenceNo}", method = RequestMethod.POST)
    public ResponseEntity<Receipt> updateReceipt(@PathVariable String referenceNo, @RequestBody Receipt vo) {
        AcReceipt receipt = (AcReceipt) billingService.findReceiptByReferenceNo(referenceNo);
        return new ResponseEntity<Receipt>(billingTransformer.toReceiptVo(receipt), HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts/{referenceNo}/receiptItems", method = RequestMethod.GET)
    public ResponseEntity<List<ReceiptItem>> findReceiptItems(@PathVariable String referenceNo) {
        dummyLogin();
        AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
        return new ResponseEntity<List<ReceiptItem>>(billingTransformer
                .toReceiptItemVos(billingService.findReceiptItems(receipt)), HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts/{referenceNo}/receiptItems", method = RequestMethod.POST)
    public void addReceiptItems(@PathVariable String referenceNo, @RequestBody ReceiptItem vo) {
        dummyLogin();
        AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
        AcReceiptItem e = new AcReceiptItemImpl();
        e.setChargeCode(accountService.findChargeCodeById(vo.getChargeCode().getId()));
        e.setAdjustedAmount(vo.getAdjustedAmount());
        e.setAppliedAmount(vo.getAppliedAmount());
        e.setTotalAmount(vo.getTotalAmount());
        e.setUnit(vo.getUnit());
        e.setPrice(vo.getPrice());
        e.setDescription(vo.getDescription());
        billingService.addReceiptItem(receipt, e);
    }

    @RequestMapping(value = "/receipts/{referenceNo}/receiptItems", method = RequestMethod.PUT)
    public void updateReceiptItems(@PathVariable String referenceNo, @RequestBody ReceiptItem vo) {
        dummyLogin();
        AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
        AcReceiptItem e = billingService.findReceiptItemById(vo.getId());
        e.setChargeCode(accountService.findChargeCodeById(vo.getChargeCode().getId()));
        e.setTotalAmount(vo.getTotalAmount());
        e.setAdjustedAmount(vo.getAdjustedAmount());
        e.setAppliedAmount(vo.getAppliedAmount());
        e.setTotalAmount(vo.getTotalAmount());
        e.setDescription(vo.getDescription());
        billingService.updateReceiptItem(receipt, e);
    }

    @RequestMapping(value = "/receipts/assignedTasks", method = RequestMethod.GET)
    public ResponseEntity<List<ReceiptTask>> findAssignedReceipts() {
        dummyLogin();
        List<Task> tasks = billingService.findAssignedReceiptTasks(0, 100);
        return new ResponseEntity<List<ReceiptTask>>(billingTransformer.toReceiptTaskVos(tasks), HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts/pooledTasks", method = RequestMethod.GET)
    public ResponseEntity<List<ReceiptTask>> findPooledReceipts() {
        dummyLogin();
        List<Task> tasks = billingService.findPooledReceiptTasks(0, 100);
        return new ResponseEntity<List<ReceiptTask>>(billingTransformer.toReceiptTaskVos(tasks), HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts/startTask", method = RequestMethod.POST)
    public ResponseEntity<String> startReceiptTask(@RequestBody Receipt vo) throws Exception {
        dummyLogin();

        AcAccount account = accountService.findAccountById(vo.getAccount().getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("academicSession", accountService.findCurrentAcademicSession());
        String generated = systemService.generateFormattedReferenceNo(RECEIPT_REFERENCE_NO, map);

        AcReceipt receipt = new AcReceiptImpl();
        receipt.setReferenceNo(generated);
        receipt.setReceiptNo(vo.getReceiptNo());
        receipt.setSourceNo(vo.getSourceNo());
        receipt.setAuditNo(vo.getAuditNo());
        receipt.setDescription(vo.getDescription());
        receipt.setTotalApplied(vo.getTotalApplied());
        receipt.setTotalReceived(vo.getTotalReceived());
        receipt.setTotalAmount(vo.getTotalAmount());
        receipt.setAccount(account);
        return new ResponseEntity<String>(billingService.startReceiptTask(receipt), HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts/viewTask/{taskId}", method = RequestMethod.GET)
    public ResponseEntity<ReceiptTask> findReceiptTaskByTaskId(@PathVariable String taskId) {
        return new ResponseEntity<ReceiptTask>(billingTransformer
                .toReceiptTaskVo(
                        billingService.findReceiptTaskByTaskId(taskId)), HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts/claimTask", method = RequestMethod.POST)
    public ResponseEntity<String> claimReceiptTask(@RequestBody ReceiptTask vo) {
        dummyLogin();
        Task task = billingService.findReceiptTaskByTaskId(vo.getTaskId());
        workflowService.claimTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts/releaseTask", method = RequestMethod.POST)
    public ResponseEntity<String> releaseReceiptTask(@RequestBody ReceiptTask vo) {
        dummyLogin();
        Task task = billingService.findReceiptTaskByTaskId(vo.getTaskId());
        workflowService.releaseTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts/completeTask", method = RequestMethod.POST)
    public ResponseEntity<String> completeReceiptTask(@RequestBody ReceiptTask vo) {
        dummyLogin();
        Task task = billingService.findReceiptTaskByTaskId(vo.getTaskId());
        workflowService.completeTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    // ==================================================================================================== //
    //  DEBIT NOTE
    // ==================================================================================================== //

    @RequestMapping(value = "/debitNotes/", method = RequestMethod.GET)
    public ResponseEntity<List<DebitNote>> findDebitNotes(AcInvoice invoice) {
        List<AcDebitNote> debitNotes = billingService.findDebitNotes(invoice);
        return new ResponseEntity<List<DebitNote>>(billingTransformer.toDebitNoteVos(debitNotes), HttpStatus.OK);
    }

    @RequestMapping(value = "/debitNotes/{referenceNo}", method = RequestMethod.GET)
    public ResponseEntity<DebitNote> DebitNote(@PathVariable String referenceNo) {
        AcDebitNote debitNotes = (AcDebitNote) billingService.findDebitNoteByReferenceNo(referenceNo);
        return new ResponseEntity<DebitNote>(billingTransformer.toDebitNoteVo(debitNotes), HttpStatus.OK);
    }

    @RequestMapping(value = "/debitNotes/startTask", method = RequestMethod.POST)
    public ResponseEntity<String> startDebitNoteTask(@RequestBody DebitNote vo) throws Exception {
        dummyLogin();

        AcDebitNote debitNotes = new AcDebitNoteImpl();
        debitNotes.setReferenceNo(vo.getReferenceNo());
        debitNotes.setSourceNo(vo.getSourceNo());
        debitNotes.setAuditNo(vo.getAuditNo());
        debitNotes.setDescription(vo.getDescription());
        debitNotes.setTotalAmount(BigDecimal.ZERO);
        return new ResponseEntity<String>(billingService.startDebitNoteTask(debitNotes), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/debitNotes/{referenceNo}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateDebitNote(@PathVariable String referenceNo, @RequestBody DebitNote vo) {
        dummyLogin();

        AcDebitNote debitNotes = billingService.findDebitNoteByReferenceNo(referenceNo);
        debitNotes.setReferenceNo(vo.getReferenceNo());
        debitNotes.setSourceNo(vo.getSourceNo());
        debitNotes.setAuditNo(vo.getAuditNo());
        debitNotes.setDescription(vo.getDescription());
        debitNotes.setTotalAmount(BigDecimal.ZERO);
        billingService.updateDebitNote(debitNotes);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    
    // ====================================================================================================
    // PRIVATE METHODS
    // ====================================================================================================

    private void dummyLogin() {
        AcAutoLoginToken token = new AcAutoLoginToken("root");
        Authentication authed = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authed);
    }
}
