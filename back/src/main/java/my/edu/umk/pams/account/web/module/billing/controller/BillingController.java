package my.edu.umk.pams.account.web.module.billing.controller;

import my.edu.umk.pams.account.AccountConstants;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountChargeType;
import my.edu.umk.pams.account.account.model.AcAccountImpl;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.account.model.AcFeeSchedule;
import my.edu.umk.pams.account.account.model.AcFeeScheduleImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.*;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.model.AcPaymentMethod;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplicationImpl;
import my.edu.umk.pams.account.identity.model.AcActorType;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.security.integration.AcAutoLoginToken;
import my.edu.umk.pams.account.system.service.SystemService;
import my.edu.umk.pams.account.util.DaoQuery;
import my.edu.umk.pams.account.web.module.account.vo.Account;
import my.edu.umk.pams.account.web.module.account.vo.FeeSchedule;
import my.edu.umk.pams.account.web.module.billing.vo.*;
import my.edu.umk.pams.account.web.module.financialaid.vo.WaiverApplication;
import my.edu.umk.pams.account.web.module.financialaid.vo.WaiverApplicationTask;
import my.edu.umk.pams.account.web.module.util.vo.CovalentDtQuery;
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

    // ==================================================================================================== //
    //  INVOICE
    // ==================================================================================================== //

    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    public ResponseEntity<List<Invoice>> findInvoices() {
        List<AcInvoice> invoices = billingService.findInvoices("%", 0, 100);
        return new ResponseEntity<List<Invoice>>(billingTransformer.toInvoiceVos(invoices), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/dt/covalent", method = RequestMethod.POST)
    public ResponseEntity<List<Invoice>> findInvoicesWithCovalentDtQuery(@RequestBody CovalentDtQuery query) {
        List<AcInvoice> invoices = billingService.findInvoices(query.getSearchTerm(), query.getCurrentPage(), query.getPageSize(), DaoQuery.format(query.getColumns()));
        return new ResponseEntity<List<Invoice>>(billingTransformer.toInvoiceVos(invoices), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/{filter}/{offset}/{limit}", method = RequestMethod.GET)
    public ResponseEntity<List<Invoice>> findInvoicesWithFilterAndPagination(@PathVariable String filter, @PathVariable Integer offset, @PathVariable Integer limit) {
        List<AcInvoice> invoices = billingService.findInvoices(filter, offset, limit);
        return new ResponseEntity<List<Invoice>>(billingTransformer.toInvoiceVos(invoices), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/account/{code}", method = RequestMethod.GET)
    public ResponseEntity<List<Invoice>> findInvoicesByAccount(@PathVariable String code) {
        List<AcInvoice> invoices = billingService.findInvoices(code, 0, 100);
        return new ResponseEntity<List<Invoice>>(billingTransformer.toInvoiceVos(invoices), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/invoices/state/{state}", method = RequestMethod.GET)
    public ResponseEntity<List<Invoice>> findInvoicesByFlowState(@PathVariable String state) {
        List<AcInvoice> invoices = billingService.findInvoicesByFlowState(AcFlowState.valueOf(state));
        return new ResponseEntity<List<Invoice>>(billingTransformer.toInvoiceVos(invoices), HttpStatus.OK);
    }

    // todo: archive will be using ACL
    @RequestMapping(value = "/invoices/archived", method = RequestMethod.GET)
    public ResponseEntity<List<Invoice>> findArchivedInvoices() {
    	
        List<AcInvoice> invoices = billingService.findInvoicesByFlowStates(AcFlowState.CANCELLED, AcFlowState.REMOVED, AcFlowState.COMPLETED);
        return new ResponseEntity<List<Invoice>>(billingTransformer.toInvoiceVos(invoices), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/{referenceNo}", method = RequestMethod.GET)
    public ResponseEntity<Invoice> findInvoiceByReferenceNo(@PathVariable String referenceNo) {
        AcInvoice invoice = billingService.findInvoiceByReferenceNo(referenceNo);
        return new ResponseEntity<Invoice>(billingTransformer.toInvoiceVo(invoice), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/unpaidInvoices/{code}", method = RequestMethod.GET)
    public ResponseEntity<List<Invoice>> findUnpaidInvoices(@PathVariable String code) {
        AcAccount account = accountService.findAccountByCode(code);
        List<AcInvoice> invoices = billingService.findUnpaidInvoices(account, 0, 100);
        return new ResponseEntity<List<Invoice>>(billingTransformer.toInvoiceVos(invoices), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/{referenceNo}", method = RequestMethod.PUT)
    public ResponseEntity<Invoice> updateInvoice(@PathVariable String referenceNo, @RequestBody Invoice vo) {
        AcInvoice invoice = billingService.findInvoiceByReferenceNo(referenceNo);
        return new ResponseEntity<Invoice>(billingTransformer.toInvoiceVo(invoice), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/{referenceNo}/cancel", method = RequestMethod.POST)
    public ResponseEntity<String> cancelInvoice(@PathVariable String referenceNo) {
    	
        AcInvoice invoice = billingService.findInvoiceByReferenceNo(referenceNo);
        billingService.cancelInvoice(invoice);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/{referenceNo}/invoiceItems", method = RequestMethod.GET)
    public ResponseEntity<List<InvoiceItem>> findInvoiceItems(@PathVariable String referenceNo) {
        
        AcInvoice invoice = billingService.findInvoiceByReferenceNo(referenceNo);
        return new ResponseEntity<List<InvoiceItem>>(billingTransformer
                .toInvoiceItemVos(billingService.findInvoiceItems(invoice)), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/{referenceNo}/invoiceItems", method = RequestMethod.POST)
    public ResponseEntity<String> addInvoiceItem(@PathVariable String referenceNo, @RequestBody InvoiceItem item) {
        
        LOG.debug("referenceNo: {}", referenceNo);
        LOG.debug("chargeCode: {}", item.getChargeCode().getCode());

        AcChargeCode chargeCode = accountService.findChargeCodeById(item.getChargeCode().getId());
        AcInvoice invoice = billingService.findInvoiceByReferenceNo(referenceNo);
        AcInvoiceItem e = new AcInvoiceItemImpl();

        e.setDescription(item.getDescription());
        e.setChargeCode(chargeCode);
        e.setTaxCode(chargeCode.getTaxCode());
        e.setAmount(item.getAmount());
        e.setBalanceAmount(item.getAmount());
        billingService.calculateNetAmount(e);
        billingService.addInvoiceItem(invoice, e);      
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/{referenceNo}/invoiceItems/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateInvoiceItems(@PathVariable String referenceNo, @PathVariable Long id, @RequestBody InvoiceItem item) {
        
        AcChargeCode chargeCode = accountService.findChargeCodeById(item.getChargeCode().getId());
        AcInvoice invoice = billingService.findInvoiceByReferenceNo(referenceNo);
        AcInvoiceItem e = billingService.findInvoiceItemById(item.getId());
        e.setChargeCode(chargeCode);
        e.setTaxCode(chargeCode.getTaxCode());
        e.setDescription(item.getDescription());
        e.setAmount(item.getAmount());
        e.setTaxAmount(item.getTaxAmount());
        e.setNetAmount(item.getNetAmount());
        e.setBalanceAmount(item.getAmount());
        billingService.calculateNetAmount(e);
        billingService.updateInvoiceItem(invoice, e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/{referenceNo}/invoiceItems/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteInvoiceItems(@PathVariable String referenceNo, @PathVariable Long id) {
        
        AcInvoice invoice = billingService.findInvoiceByReferenceNo(referenceNo);
        AcInvoiceItem e = billingService.findInvoiceItemById(id);
        billingService.deleteInvoiceItem(invoice, e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/{referenceNo}/debitNotes", method = RequestMethod.GET)
    public ResponseEntity<List<DebitNote>> findDebitNotesByInvoice(@PathVariable String referenceNo) {
        

        LOG.debug("referenceNo: {}", referenceNo);
        AcInvoice invoice = billingService.findInvoiceByReferenceNo(referenceNo);
        return new ResponseEntity<List<DebitNote>>(billingTransformer
                .toDebitNoteVos(billingService.findDebitNotes(invoice)), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/{referenceNo}/creditNotes", method = RequestMethod.GET)
    public ResponseEntity<List<CreditNote>> findCreditNotesByInvoice(@PathVariable String referenceNo) {
        

        LOG.debug("referenceNo: {}", referenceNo);
        AcInvoice invoice = billingService.findInvoiceByReferenceNo(referenceNo);
        return new ResponseEntity<List<CreditNote>>(billingTransformer
                .toCreditNoteVos(billingService.findCreditNotes(invoice)), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/assignedTasks", method = RequestMethod.GET)
    public ResponseEntity<List<InvoiceTask>> findAssignedInvoices() {
        
        List<Task> tasks = billingService.findAssignedInvoiceTasks(0, 100);
        return new ResponseEntity<List<InvoiceTask>>(billingTransformer.toInvoiceTaskVos(tasks), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/pooledTasks", method = RequestMethod.GET)
    public ResponseEntity<List<InvoiceTask>> findPooledInvoices() {
        
        List<Task> tasks = billingService.findPooledInvoiceTasks(0, 100);
        return new ResponseEntity<List<InvoiceTask>>(billingTransformer.toInvoiceTaskVos(tasks), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/startTask", method = RequestMethod.POST)
    public ResponseEntity<String> startInvoiceTask(@RequestBody Invoice vo) throws Exception {
        

        AcAccount account = accountService.findAccountById(vo.getAccount().getId());
        AcInvoice invoice = new AcInvoiceImpl();
        invoice.setDescription(vo.getDescription());
        invoice.setTotalAmount(BigDecimal.ZERO);
        invoice.setBalanceAmount(BigDecimal.ZERO);
        invoice.setIssuedDate(vo.getIssuedDate());
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
        
        Task task = billingService.findInvoiceTaskByTaskId(vo.getTaskId());
        workflowService.claimTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/releaseTask", method = RequestMethod.POST)
    public ResponseEntity<String> releaseInvoiceTask(@RequestBody InvoiceTask vo) {
        
        Task task = billingService.findInvoiceTaskByTaskId(vo.getTaskId());
        workflowService.releaseTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/completeTask", method = RequestMethod.POST)
    public ResponseEntity<String> completeInvoiceTask(@RequestBody InvoiceTask vo) {
        
        Task task = billingService.findInvoiceTaskByTaskId(vo.getTaskId());
        workflowService.completeTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    // ==================================================================================================== //
    //  RECEIPT
    // ==================================================================================================== //

    @RequestMapping(value = "/receipts/", method = RequestMethod.GET)
    public ResponseEntity<List<Receipt>> findReceipts() {
        List<AcReceipt> receipts = billingService.findReceipts("%", 0, 100);
        return new ResponseEntity<List<Receipt>>(billingTransformer.toReceiptVos(receipts), HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts/state/{state}", method = RequestMethod.GET)
    public ResponseEntity<List<Receipt>> findReceiptsByFlowState(@PathVariable String state) {
        List<AcReceipt> receipts = billingService.findReceiptsByFlowState(AcFlowState.valueOf(state));
        return new ResponseEntity<List<Receipt>>(billingTransformer.toReceiptVos(receipts), HttpStatus.OK);
    }

    // todo: archive will be using ACL
    @RequestMapping(value = "/receipts/archived", method = RequestMethod.GET)
    public ResponseEntity<List<Receipt>> findArchivedReceipts() {
        List<AcReceipt> receipts = billingService.findReceiptsByFlowState(AcFlowState.CANCELLED, AcFlowState.REMOVED, AcFlowState.COMPLETED);
        return new ResponseEntity<List<Receipt>>(billingTransformer.toReceiptVos(receipts), HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts/{referenceNo}", method = RequestMethod.GET)
    public ResponseEntity<Receipt> findReceiptByReferenceNo(@PathVariable String referenceNo) {
        AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
        return new ResponseEntity<Receipt>(billingTransformer.toReceiptVo(receipt), HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts/{referenceNo}", method = RequestMethod.POST)
    public ResponseEntity<Receipt> updateReceipt(@PathVariable String referenceNo, @RequestBody Receipt vo) {
        AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
        return new ResponseEntity<Receipt>(billingTransformer.toReceiptVo(receipt), HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts/{referenceNo}/receiptItems", method = RequestMethod.GET)
    public ResponseEntity<List<ReceiptItem>> findReceiptItems(@PathVariable String referenceNo) {
        
        AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
        return new ResponseEntity<List<ReceiptItem>>(billingTransformer
                .toReceiptItemVos(billingService.findReceiptItems(receipt)), HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts/{referenceNo}/receiptItems", method = RequestMethod.POST)
    public void addReceiptItems(@PathVariable String referenceNo, @RequestBody ReceiptItem vo) {
        
        AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
        AcReceiptItem e = new AcReceiptItemImpl();
        e.setChargeCode(accountService.findChargeCodeById(vo.getChargeCode().getId()));
        e.setAdjustedAmount(vo.getAdjustedAmount());
        e.setAppliedAmount(vo.getAppliedAmount());
        e.setTotalAmount(vo.getTotalAmount());
        e.setDueAmount(vo.getDueAmount());
        e.setUnit(vo.getUnit());
        e.setPrice(vo.getPrice());
        e.setDescription(vo.getDescription());
        e.setInvoice(billingService.findInvoiceById(vo.getInvoice().getId()));
        billingService.addReceiptItem(receipt, e);
    }

    @RequestMapping(value = "/receipts/{referenceNo}/receiptItems", method = RequestMethod.PUT)
    public void updateReceiptItems(@PathVariable String referenceNo, @RequestBody ReceiptItem vo) {
        
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
    
    @RequestMapping(value = "/receipts/{referenceNo}/invoice/{id}", method = RequestMethod.POST)
    public void addReceiptInvoiceItems(@PathVariable String referenceNo, @PathVariable Long id) {
        
        AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
        AcInvoice invoice = billingService.findInvoiceById(id);
        AcReceiptInvoice e = new AcReceiptInvoiceImpl();
        e.setReceipt(receipt);
        e.setInvoice(invoice);
        billingService.addReceiptInvoice(receipt, invoice);
    }
    
    @RequestMapping(value = "/receipts/{referenceNo}/receiptInvoice", method = RequestMethod.GET)
    public ResponseEntity<List<ReceiptInvoice>> findReceiptsByInvoice(@PathVariable String referenceNo) {
        
        AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
        return new ResponseEntity<List<ReceiptInvoice>>(billingTransformer
                .toReceiptInvoiceVos(billingService.findReceipts(receipt)), HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts/assignedTasks", method = RequestMethod.GET)
    public ResponseEntity<List<ReceiptTask>> findAssignedReceipts() {
        
        List<Task> tasks = billingService.findAssignedReceiptTasks(0, 100);
        return new ResponseEntity<List<ReceiptTask>>(billingTransformer.toReceiptTaskVos(tasks), HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts/pooledTasks", method = RequestMethod.GET)
    public ResponseEntity<List<ReceiptTask>> findPooledReceipts() {
        
        List<Task> tasks = billingService.findPooledReceiptTasks(0, 100);
        return new ResponseEntity<List<ReceiptTask>>(billingTransformer.toReceiptTaskVos(tasks), HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts/startTask", method = RequestMethod.POST)
    public ResponseEntity<String> startReceiptTask(@RequestBody Receipt vo) throws Exception {
        

        AcAccount account = accountService.findAccountById(vo.getAccount().getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("academicSession", accountService.findCurrentAcademicSession());
        String generated = systemService.generateFormattedReferenceNo(RECEIPT_REFERENCE_NO, map);

        AcReceipt receipt = new AcReceiptImpl();
        receipt.setReferenceNo(generated);
        receipt.setReceiptNo(vo.getReceiptNo());
        receipt.setSourceNo(vo.getSourceNo());
        receipt.setAuditNo(vo.getAuditNo());
        receipt.setReceivedDate(vo.getReceivedDate());
        receipt.setDescription(vo.getDescription());
        receipt.setTotalApplied(vo.getTotalApplied());
        receipt.setTotalReceived(vo.getTotalReceived());
        receipt.setTotalAmount(vo.getTotalAmount());
        receipt.setAccount(account);
        receipt.setReceiptType(AcReceiptType.get(vo.getReceiptType().ordinal()));
        receipt.setPaymentMethod(AcPaymentMethod.get(vo.getPaymentMethod().ordinal()));
        receipt.setSession(accountService.findCurrentAcademicSession());
        receipt.setTotalPayment(vo.getTotalPayment());
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
        
        Task task = billingService.findReceiptTaskByTaskId(vo.getTaskId());
        workflowService.claimTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts/releaseTask", method = RequestMethod.POST)
    public ResponseEntity<String> releaseReceiptTask(@RequestBody ReceiptTask vo) {
        
        Task task = billingService.findReceiptTaskByTaskId(vo.getTaskId());
        workflowService.releaseTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts/completeTask", method = RequestMethod.POST)
    public ResponseEntity<String> completeReceiptTask(@RequestBody ReceiptTask vo) {
        
        Task task = billingService.findReceiptTaskByTaskId(vo.getTaskId());
        workflowService.completeTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/receipts/{referenceNo}/account", method = RequestMethod.POST)
    public void calculateChargeInvoice(@PathVariable String referenceNo, @RequestBody Account vo) {
        
        LOG.debug("referenceNo {}", referenceNo);
        
        AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
        LOG.debug("receiptNo {}", receipt);
        AcAccount account = accountService.findAccountByCode(receipt.getAccount().getCode());
        LOG.debug("account {}", account);
        billingService.calculateChargeInvoice(receipt, account);
    }
    
    @RequestMapping(value = "/invoices/{referenceNo}/receipts/{id}", method = RequestMethod.POST)
    public void itemToReceiptItem(@PathVariable Long id, @PathVariable String referenceNo) {
    	
    	AcReceipt receipt = billingService.findReceiptById(id);
        AcInvoice invoice = billingService.findInvoiceByReferenceNo(referenceNo);
        billingService.itemToReceiptItem(invoice, receipt);
    }

    // ==================================================================================================== //
    //  DEBIT NOTE
    // ==================================================================================================== //


    @RequestMapping(value = "/debitNotes/state/{state}", method = RequestMethod.GET)
    public ResponseEntity<List<DebitNote>> findDebitNotesByFlowState(@PathVariable String state) {
        List<AcDebitNote> debitNotes = billingService.findDebitNotesByFlowState(AcFlowState.valueOf(state));
        return new ResponseEntity<List<DebitNote>>(billingTransformer.toDebitNoteVos(debitNotes), HttpStatus.OK);
    }

    // todo: archive will be using ACL
    @RequestMapping(value = "/debitNotes/archived", method = RequestMethod.GET)
    public ResponseEntity<List<DebitNote>> findArchivedDebitNotes(@PathVariable String state) {
        List<AcDebitNote> debitNotes = billingService.findDebitNotesByFlowState(AcFlowState.valueOf(state));
        return new ResponseEntity<List<DebitNote>>(billingTransformer.toDebitNoteVos(debitNotes), HttpStatus.OK);
    }

    @RequestMapping(value = "/debitNotes/{referenceNo}", method = RequestMethod.GET)
    public ResponseEntity<DebitNote> findDebitNoteByReferenceNo(@PathVariable String referenceNo) {
        AcDebitNote debitNotes = billingService.findDebitNoteByReferenceNo(referenceNo);
        return new ResponseEntity<DebitNote>(billingTransformer.toDebitNoteVo(debitNotes), HttpStatus.OK);
    }

    @RequestMapping(value = "/debitNotes/{referenceNo}/debitNoteItems", method = RequestMethod.GET)
    public ResponseEntity<List<DebitNoteItem>> findDebitNoteItems(@PathVariable String referenceNo) {
        
        AcDebitNote debitNote = billingService.findDebitNoteByReferenceNo(referenceNo);
        return new ResponseEntity<List<DebitNoteItem>>(billingTransformer
                .toDebitNoteItemVos(billingService.findDebitNoteItems(debitNote)), HttpStatus.OK);
    }

    @RequestMapping(value = "/debitNotes/{referenceNo}/debitNoteItems", method = RequestMethod.POST)
    public ResponseEntity<String> addDebitNoteItem(@PathVariable String referenceNo, @RequestBody DebitNoteItem item) {
        

        LOG.debug("referenceNo: {}", referenceNo);
        LOG.debug("chargeCode: {}", item.getChargeCode().getCode());

        AcDebitNote debitNote = billingService.findDebitNoteByReferenceNo(referenceNo);
        AcDebitNoteItem e = new AcDebitNoteItemImpl();
        e.setChargeCode(accountService.findChargeCodeById(item.getChargeCode().getId()));
        e.setAmount(item.getAmount());
        e.setDebitNoteItemDate(item.getDebitNoteItemDate());
        e.setDescription(item.getDescription());
        billingService.addDebitNoteItem(debitNote, e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/debitNotes/{referenceNo}/debitNoteItems/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateDebitNoteItems(@PathVariable String referenceNo, @PathVariable Long id, @RequestBody DebitNoteItem item) {
        
        AcDebitNote debitNote = billingService.findDebitNoteByReferenceNo(referenceNo);
        AcDebitNoteItem e = billingService.findDebitNoteItemById(item.getId());
        e.setChargeCode(accountService.findChargeCodeById(item.getChargeCode().getId()));
        e.setAmount(item.getAmount());
        e.setDescription(item.getDescription());
        e.setDebitNoteItemDate(item.getDebitNoteItemDate());
        billingService.updateDebitNoteItem(debitNote, e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/debitNotes/{referenceNo}/debitNoteItems/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteDebitNoteItems(@PathVariable String referenceNo, @PathVariable Long id) {
        
        AcDebitNote debitNote = billingService.findDebitNoteByReferenceNo(referenceNo);
        AcDebitNoteItem e = billingService.findDebitNoteItemById(id);
        billingService.deleteDebitNoteItem(debitNote, e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/debitNotes/assignedTasks", method = RequestMethod.GET)
    public ResponseEntity<List<DebitNoteTask>> findAssignedDebitNotes() {
        
        List<Task> tasks = billingService.findAssignedDebitNoteTasks(0, 100);
        return new ResponseEntity<List<DebitNoteTask>>(billingTransformer.toDebitNoteTaskVos(tasks), HttpStatus.OK);
    }

    @RequestMapping(value = "/debitNotes/pooledTasks", method = RequestMethod.GET)
    public ResponseEntity<List<DebitNoteTask>> findPooledDebitNotes() {
        
        List<Task> tasks = billingService.findPooledDebitNoteTasks(0, 100);
        return new ResponseEntity<List<DebitNoteTask>>(billingTransformer.toDebitNoteTaskVos(tasks), HttpStatus.OK);
    }

    @RequestMapping(value = "/debitNotes/startTask", method = RequestMethod.POST)
    public ResponseEntity<String> startDebitNoteTask(@RequestBody DebitNote vo) throws Exception {
        

        AcDebitNote debitNote = new AcDebitNoteImpl();
        debitNote.setSourceNo(vo.getSourceNo());
        debitNote.setAuditNo(vo.getAuditNo());
        debitNote.setDescription(vo.getDescription());
        debitNote.setTotalAmount(BigDecimal.ZERO);
        debitNote.setTotalAmount(vo.getTotalAmount());
        debitNote.setDebitNoteDate(vo.getDebitNoteDate());
        debitNote.setInvoice(billingService.findInvoiceById(vo.getInvoice().getId()));
        debitNote.setChargeCode(accountService.findChargeCodeById(vo.getChargeCode().getId()));
        return new ResponseEntity<String>(billingService.startDebitNoteTask(debitNote), HttpStatus.OK);
    }

    @RequestMapping(value = "/debitNotes/viewTask/{taskId}", method = RequestMethod.GET)
    public ResponseEntity<DebitNoteTask> findDebitNoteByTaskId(@PathVariable String taskId) {
        return new ResponseEntity<DebitNoteTask>(billingTransformer.toDebitNoteTaskVo(
                billingService.findDebitNoteTaskByTaskId(taskId)), HttpStatus.OK);
    }

    @RequestMapping(value = "/debitNotes/claimTask", method = RequestMethod.POST)
    public ResponseEntity<String> claimDebitNoteTask(@RequestBody DebitNoteTask vo) {
        
        Task task = billingService.findDebitNoteTaskByTaskId(vo.getTaskId());
        workflowService.claimTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/debitNotes/releaseTask", method = RequestMethod.POST)
    public ResponseEntity<String> releaseDebitNoteTask(@RequestBody DebitNoteTask vo) {
        
        Task task = billingService.findDebitNoteTaskByTaskId(vo.getTaskId());
        workflowService.releaseTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/debitNotes/completeTask", method = RequestMethod.POST)
    public ResponseEntity<String> completeDebitNoteTask(@RequestBody DebitNoteTask vo) {
        
        Task task = billingService.findDebitNoteTaskByTaskId(vo.getTaskId());
        workflowService.completeTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/debitNotes/{referenceNo}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateDebitNote(@PathVariable String referenceNo, @RequestBody DebitNote vo) {
        

        AcDebitNote debitNotes = billingService.findDebitNoteByReferenceNo(referenceNo);
        debitNotes.setReferenceNo(vo.getReferenceNo());
        debitNotes.setSourceNo(vo.getSourceNo());
        debitNotes.setAuditNo(vo.getAuditNo());
        debitNotes.setDescription(vo.getDescription());
        debitNotes.setTotalAmount(BigDecimal.ZERO);
        debitNotes.setTotalAmount(vo.getTotalAmount());
        billingService.updateDebitNote(debitNotes);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    // ==================================================================================================== //
    //  CREDIT NOTE
    // ==================================================================================================== //

    @RequestMapping(value = "/creditNotes/state/{state}", method = RequestMethod.GET)
    public ResponseEntity<List<CreditNote>> findCreditNotesByFlowState(@PathVariable String state) {
        List<AcCreditNote> creditNotes = billingService.findCreditNotesByFlowState(AcFlowState.valueOf(state));
        return new ResponseEntity<List<CreditNote>>(billingTransformer.toCreditNoteVos(creditNotes), HttpStatus.OK);
    }

    // todo: archive will be using ACL
    @RequestMapping(value = "/creditNotes/archived", method = RequestMethod.GET)
    public ResponseEntity<List<CreditNote>> findArchivedCreditNotes(@PathVariable String state) {
        List<AcCreditNote> creditNotes = billingService.findCreditNotesByFlowState(AcFlowState.valueOf(state));
        return new ResponseEntity<List<CreditNote>>(billingTransformer.toCreditNoteVos(creditNotes), HttpStatus.OK);
    }

    @RequestMapping(value = "/creditNotes/{referenceNo}", method = RequestMethod.GET)
    public ResponseEntity<CreditNote> findCreditNoteByReferenceNo(@PathVariable String referenceNo) {
        AcCreditNote creditNotes = billingService.findCreditNoteByReferenceNo(referenceNo);
        return new ResponseEntity<CreditNote>(billingTransformer.toCreditNoteVo(creditNotes), HttpStatus.OK);
    }

    @RequestMapping(value = "/creditNotes/{referenceNo}/creditNoteItems", method = RequestMethod.GET)
    public ResponseEntity<List<CreditNoteItem>> findCreditNoteItems(@PathVariable String referenceNo) {
        
        AcCreditNote creditNote = billingService.findCreditNoteByReferenceNo(referenceNo);
        return new ResponseEntity<List<CreditNoteItem>>(billingTransformer
                .toCreditNoteItemVos(billingService.findCreditNoteItems(creditNote)), HttpStatus.OK);
    }

    @RequestMapping(value = "/creditNotes/{referenceNo}/creditNoteItems", method = RequestMethod.POST)
    public ResponseEntity<String> addCreditNoteItem(@PathVariable String referenceNo, @RequestBody CreditNoteItem item) {
        

        LOG.debug("referenceNo: {}", referenceNo);
        LOG.debug("chargeCode: {}", item.getChargeCode().getCode());

        AcCreditNote creditNote = billingService.findCreditNoteByReferenceNo(referenceNo);
        AcCreditNoteItem e = new AcCreditNoteItemImpl();
        e.setChargeCode(accountService.findChargeCodeById(item.getChargeCode().getId()));
        e.setAmount(item.getAmount());
        e.setDescription(item.getDescription());
        e.setCreditNoteItemDate(item.getCreditNoteItemDate());
        billingService.addCreditNoteItem(creditNote, e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/creditNotes/{referenceNo}/creditNoteItems/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateCreditNoteItems(@PathVariable String referenceNo, @PathVariable Long id, @RequestBody CreditNoteItem item) {
        
        AcCreditNote creditNote = billingService.findCreditNoteByReferenceNo(referenceNo);
        AcCreditNoteItem e = billingService.findCreditNoteItemById(item.getId());
        e.setChargeCode(accountService.findChargeCodeById(item.getChargeCode().getId()));
        e.setAmount(item.getAmount());
        e.setDescription(item.getDescription());
        e.setCreditNoteItemDate(item.getCreditNoteItemDate());
        billingService.updateCreditNoteItem(creditNote, e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/creditNotes/{referenceNo}/creditNoteItems/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCreditNoteItems(@PathVariable String referenceNo, @PathVariable Long id) {
        
        AcCreditNote creditNote = billingService.findCreditNoteByReferenceNo(referenceNo);
        AcCreditNoteItem e = billingService.findCreditNoteItemById(id);
        billingService.deleteCreditNoteItem(creditNote, e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/creditNotes/claimTask", method = RequestMethod.POST)
    public ResponseEntity<String> claimCreditNoteTask(@RequestBody CreditNoteTask vo) {
        
        Task task = billingService.findCreditNoteTaskByTaskId(vo.getTaskId());
        workflowService.claimTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/creditNotes/assignedTasks", method = RequestMethod.GET)
    public ResponseEntity<List<CreditNoteTask>> findAssignedCreditNotes() {
        
        List<Task> tasks = billingService.findAssignedCreditNoteTasks(0, 100);
        return new ResponseEntity<List<CreditNoteTask>>(billingTransformer.toCreditNoteTaskVos(tasks), HttpStatus.OK);
    }

    @RequestMapping(value = "/creditNotes/pooledTasks", method = RequestMethod.GET)
    public ResponseEntity<List<CreditNoteTask>> findPooledCreditNotes() {
        
        List<Task> tasks = billingService.findPooledCreditNoteTasks(0, 100);
        return new ResponseEntity<List<CreditNoteTask>>(billingTransformer.toCreditNoteTaskVos(tasks), HttpStatus.OK);
    }

    @RequestMapping(value = "/creditNotes/startTask", method = RequestMethod.POST)
    public ResponseEntity<String> startCreditNoteTask(@RequestBody CreditNote vo) throws Exception {
        

        AcCreditNote creditNote = new AcCreditNoteImpl();
        creditNote.setSourceNo(vo.getSourceNo());
        creditNote.setAuditNo(vo.getAuditNo());
        creditNote.setDescription(vo.getDescription());
        creditNote.setCreditNoteDate(vo.getCreditNoteDate());
        creditNote.setTotalAmount(BigDecimal.ZERO);
        creditNote.setTotalAmount(vo.getTotalAmount());
        creditNote.setInvoice(billingService.findInvoiceById(vo.getInvoice().getId()));
        creditNote.setChargeCode(accountService.findChargeCodeById(vo.getChargeCode().getId()));
        return new ResponseEntity<String>(billingService.startCreditNoteTask(creditNote), HttpStatus.OK);
    }


    @RequestMapping(value = "/creditNotes/viewTask/{taskId}", method = RequestMethod.GET)
    public ResponseEntity<CreditNoteTask> findCreditNoteByTaskId(@PathVariable String taskId) {
        return new ResponseEntity<CreditNoteTask>(billingTransformer.toCreditNoteTaskVo(
                billingService.findCreditNoteTaskByTaskId(taskId)), HttpStatus.OK);
    }

    @RequestMapping(value = "/creditNotes/releaseTask", method = RequestMethod.POST)
    public ResponseEntity<String> releaseCreditNoteTask(@RequestBody CreditNoteTask vo) {
        Task task = billingService.findCreditNoteTaskByTaskId(vo.getTaskId());
        workflowService.releaseTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/creditNotes/completeTask", method = RequestMethod.POST)
    public ResponseEntity<String> completeCreditNoteTask(@RequestBody CreditNoteTask vo) {
        Task task = billingService.findCreditNoteTaskByTaskId(vo.getTaskId());
        workflowService.completeTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }


    @RequestMapping(value = "/creditNotes/{referenceNo}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateCreditNote(@PathVariable String referenceNo, @RequestBody CreditNote vo) {
        AcCreditNote creditNotes = billingService.findCreditNoteByReferenceNo(referenceNo);
        creditNotes.setReferenceNo(vo.getReferenceNo());
        creditNotes.setSourceNo(vo.getSourceNo());
        creditNotes.setAuditNo(vo.getAuditNo());
        creditNotes.setDescription(vo.getDescription());
        creditNotes.setTotalAmount(BigDecimal.ZERO);
        creditNotes.setTotalAmount(vo.getTotalAmount());
        billingService.updateCreditNote(creditNotes);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
    // ==================================================================================================== //
    //  KNOCKOFF
    // ==================================================================================================== //
   
    @RequestMapping(value = "/advancePayments", method = RequestMethod.GET)
    public ResponseEntity<List<AdvancePayment>> findAdvancePayments() {
        List<AcAdvancePayment> advancePayments = billingService.findAdvancePayments(false, "%", 0, 100);
        return new ResponseEntity<List<AdvancePayment>>(billingTransformer.toAdvancePaymentVos(advancePayments), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/advancePayments/{referenceNo}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateAdvancePayment(@PathVariable String referenceNo, @RequestBody AdvancePayment vo) {
        AcAdvancePayment payments = billingService.findAdvancePaymentByReferenceNo(referenceNo);
        payments.setBalanceAmount(vo.getBalanceAmount());
        payments.setStatus(vo.getStatus());
        billingService.updateAdvancePayment(payments);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
        
    @RequestMapping(value = "/advancePayments/unpaidInvoices/{code}", method = RequestMethod.GET)
    public ResponseEntity<List<AdvancePayment>> findUnpaidAdvancePayments(@PathVariable String code) {
        AcAccount account = accountService.findAccountByCode(code);
        List<AcAdvancePayment> payments = billingService.findUnpaidAdvancePayments(account, 0, 100);
        return new ResponseEntity<List<AdvancePayment>>(billingTransformer.toAdvancePaymentVos(payments), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/knockoffs", method = RequestMethod.GET)
    public ResponseEntity<List<Knockoff>> findKnockoffs() {
        List<AcKnockoff> knockoffs = billingService.findKnockoffs("%", 0, 100);
        return new ResponseEntity<List<Knockoff>>(billingTransformer.toKnockoffVos(knockoffs), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/knockoffs/{referenceNo}", method = RequestMethod.GET)
    public ResponseEntity<Knockoff> findKnockoffByReferenceNo(@PathVariable String referenceNo) {
        AcKnockoff knockoffs = billingService.findKnockoffByReferenceNo(referenceNo);
        return new ResponseEntity<Knockoff>(billingTransformer.toKnockoffVo(knockoffs), HttpStatus.OK);
    }
    
//    @RequestMapping(value = "/knockoffs/{referenceNo}", method = RequestMethod.POST)
//    public ResponseEntity<String> saveKnockoff(@PathVariable String referenceNo, @RequestBody Knockoff vo) {
//        
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("academicSession", accountService.findCurrentAcademicSession());
//        String refNo = systemService.generateFormattedReferenceNo(AccountConstants.KNOCKOFF_REFRENCE_NO, map);
//        
//        AcAdvancePayment payment = billingService.findAdvancePaymentByReferenceNo(referenceNo);
//        LOG.debug("payment controller" + payment);
//        
//        AcKnockoff knockoff = new AcKnockoffImpl();
//        knockoff.setAmount(vo.getAmount());
//        knockoff.setAuditNo(vo.getAuditNo());
//        knockoff.setDescription(vo.getDescription());
//        knockoff.setInvoice(billingService.findInvoiceById(vo.getInvoice().getId()));
//        knockoff.setIssuedDate(vo.getIssuedDate());
//        knockoff.setPayments(payment);
//        knockoff.setReferenceNo(refNo);
//        billingService.addKnockoff(knockoff);
//        return new ResponseEntity<String>("Success", HttpStatus.OK);
//    }
    
    @RequestMapping(value = "/knockoffs/state/{state}", method = RequestMethod.GET)
    public ResponseEntity<List<Knockoff>> findKnockoffsByFlowState(@PathVariable String state) {
        List<AcKnockoff> Knockoffs = billingService.findKnockoffsByFlowState(AcFlowState.valueOf(state));
        return new ResponseEntity<List<Knockoff>>(billingTransformer.toKnockoffVos(Knockoffs), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/knockoffs/assignedTasks", method = RequestMethod.GET)
    public ResponseEntity<List<KnockoffTask>> findAssignedKnockoffs() {
        
        List<Task> tasks = billingService.findAssignedKnockoffTasks(0, 100);
        return new ResponseEntity<List<KnockoffTask>>(billingTransformer.toKnockoffTaskVos(tasks), HttpStatus.OK);
    }

    @RequestMapping(value = "/knockoffs/pooledTasks", method = RequestMethod.GET)
    public ResponseEntity<List<KnockoffTask>> findPooledKnockoffTasks() {
        
        List<Task> tasks = billingService.findPooledKnockoffTasks(0, 100);
        return new ResponseEntity<List<KnockoffTask>>(billingTransformer.toKnockoffTaskVos(tasks), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/knockoffs/archived", method = RequestMethod.GET)
    public ResponseEntity<List<Knockoff>> findArchivedknockoffs() {
    	
        List<AcKnockoff> knockoffs = billingService.findKnockoffsByFlowStates(AcFlowState.CANCELLED, AcFlowState.REMOVED, AcFlowState.COMPLETED);
        return new ResponseEntity<List<Knockoff>>(billingTransformer.toKnockoffVos(knockoffs), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/knockoffs/startTask", method = RequestMethod.POST)
    public ResponseEntity<String> startKnockoffTask(@RequestBody Knockoff vo) throws Exception {

        AcKnockoff knockoff = new AcKnockoffImpl();
        knockoff.setSourceNo(vo.getSourceNo());
        knockoff.setAuditNo(vo.getAuditNo());
        knockoff.setIssuedDate(vo.getIssuedDate());
        knockoff.setDescription(vo.getDescription());
        knockoff.setAmount(vo.getAmount());
        knockoff.setPayments(billingService.findAdvancePaymentById(vo.getPayments().getId()));
        knockoff.setInvoice(billingService.findInvoiceById(vo.getInvoice().getId()));
        return new ResponseEntity<String>(billingService.startKnockoffTask(knockoff), HttpStatus.OK);
       
    }

    @RequestMapping(value = "/knockoffs/viewTask/{taskId}", method = RequestMethod.GET)
    public ResponseEntity<KnockoffTask> findKnockoffTaskByTaskId(@PathVariable String taskId) {
        return new ResponseEntity<KnockoffTask>(billingTransformer
                .toKnockoffTaskVo(
                        billingService.findKnockoffTaskByTaskId(taskId)), HttpStatus.OK);
    }

    @RequestMapping(value = "/knockoffs/claimTask", method = RequestMethod.POST)
    public ResponseEntity<String> claimKnockoffTask(@RequestBody KnockoffTask vo) {
        
        Task task = billingService.findKnockoffTaskByTaskId(vo.getTaskId());
        workflowService.claimTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/knockoffs/releaseTask", method = RequestMethod.POST)
    public ResponseEntity<String> releaseKnockoffTask(@RequestBody KnockoffTask vo) {
        
        Task task = billingService.findKnockoffTaskByTaskId(vo.getTaskId());
        workflowService.releaseTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/knockoffs/completeTask", method = RequestMethod.POST)
    public ResponseEntity<String> completeKnockoffTask(@RequestBody KnockoffTask vo) {
        
        Task task = billingService.findKnockoffTaskByTaskId(vo.getTaskId());
        workflowService.completeTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    

    // ==================================================================================================== //
    //  REFUND PAYMENT
    // ==================================================================================================== //
    
    @RequestMapping(value = "/refundPayments/{referenceNo}", method = RequestMethod.GET)
    public ResponseEntity<RefundPayment> findRefundPaymentByReferenceNo(@PathVariable String referenceNo) {
        AcRefundPayment refundPayments = billingService.findRefundPaymentByReferenceNo(referenceNo);
        return new ResponseEntity<RefundPayment>(billingTransformer.toRefundPaymentVo(refundPayments), HttpStatus.OK);
    }

    @RequestMapping(value = "/refundPayments", method = RequestMethod.GET)
    public ResponseEntity<List<RefundPayment>> findRefundPayments() {
    	List<AcRefundPayment> refundPayments = billingService.findRefundPayments("%", 0, 100);
    	return new ResponseEntity<List<RefundPayment>>(billingTransformer.toRefundPaymentVos(refundPayments), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/refundPayments/assignedTasks", method = RequestMethod.GET)
    public ResponseEntity<List<RefundPaymentTask>> findAssignedRefundPayments() {
        
        List<Task> tasks = billingService.findAssignedRefundPaymentTasks(0, 100);
        return new ResponseEntity<List<RefundPaymentTask>>(billingTransformer.toRefundPaymentTaskVos(tasks), HttpStatus.OK);
    }
	
	@RequestMapping(value = "/refundPayments/pooledTasks", method = RequestMethod.GET)
    public ResponseEntity<List<RefundPaymentTask>> findPooledRefundPayments() {
        
        List<Task> tasks = billingService.findPooledRefundPaymentTasks(0, 100);
        return new ResponseEntity<List<RefundPaymentTask>>(billingTransformer.toRefundPaymentTaskVos(tasks), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/refundPayments/archived", method = RequestMethod.GET)
    public ResponseEntity<List<RefundPayment>> findArchivedRefundPayments() {
    	
        List<AcRefundPayment> refundPayments = billingService.findRefundPaymentsByFlowStates(AcFlowState.CANCELLED, AcFlowState.REMOVED, AcFlowState.COMPLETED);
        return new ResponseEntity<List<RefundPayment>>(billingTransformer.toRefundPaymentVos(refundPayments), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/refundPayments/startTask/{referenceNo}", method = RequestMethod.POST)
    public ResponseEntity<String> startRefundPaymentTask(@PathVariable String referenceNo, @RequestBody RefundPayment vo) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("academicSession", accountService.findCurrentAcademicSession());
        String refNo = systemService.generateFormattedReferenceNo(AccountConstants.REFUND_REFRENCE_NO, map);

        AcRefundPayment refundPayment = new AcRefundPaymentImpl();
        refundPayment.setReferenceNo("123");
        refundPayment.setSourceNo(vo.getSourceNo());
        refundPayment.setAuditNo(vo.getAuditNo());
        refundPayment.setIssuedDate(vo.getIssuedDate());
        refundPayment.setDescription(vo.getDescription());
        refundPayment.setAmount(vo.getAmount());
        refundPayment.setPayments(billingService.findAdvancePaymentByReferenceNo(referenceNo));
        billingService.startRefundPaymentTask(refundPayment);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/refundPayments/viewTask/{taskId}", method = RequestMethod.GET)
    public ResponseEntity<RefundPaymentTask> findRefundPaymentTaskByTaskId(@PathVariable String taskId) {
        return new ResponseEntity<RefundPaymentTask>(billingTransformer
                .toRefundPaymentTaskVo(
                        billingService.findRefundPaymentTaskByTaskId(taskId)), HttpStatus.OK);
    }

    @RequestMapping(value = "/refundPayments/claimTask", method = RequestMethod.POST)
    public ResponseEntity<String> claimRefundPaymentTask(@RequestBody RefundPaymentTask vo) {
        
        Task task = billingService.findRefundPaymentTaskByTaskId(vo.getTaskId());
        workflowService.claimTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/refundPayments/releaseTask", method = RequestMethod.POST)
    public ResponseEntity<String> releaseRefundPaymentTask(@RequestBody RefundPaymentTask vo) {
        
        Task task = billingService.findRefundPaymentTaskByTaskId(vo.getTaskId());
        workflowService.releaseTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/refundPayments/completeTask", method = RequestMethod.POST)
    public ResponseEntity<String> completeRefundPaymentTask(@RequestBody RefundPaymentTask vo) {
        
        Task task = billingService.findRefundPaymentTaskByTaskId(vo.getTaskId());
        workflowService.completeTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    // ====================================================================================================
    // WAIVER FINANCE APPLICATION
    // ====================================================================================================

    @RequestMapping(value = "/waiverFinanceApplications", method = RequestMethod.GET)
    public ResponseEntity<List<WaiverFinanceApplication>> findWaiverFinanceApplications() {
        List<AcWaiverFinanceApplication> waiverApplications = billingService.findWaiverFinanceApplications("%", 0, 100);
        return new ResponseEntity<List<WaiverFinanceApplication>>(billingTransformer.toWaiverFinanceApplicationVos(waiverApplications), HttpStatus.OK);
    }

    @RequestMapping(value = "/waiverFinanceApplications/{referenceNo}", method = RequestMethod.GET)
    public ResponseEntity<WaiverFinanceApplication> findWaiverfinanceApplicationByReferenceNo(@PathVariable String referenceNo) {
    	AcWaiverFinanceApplication waiverApplication = (AcWaiverFinanceApplication) billingService.findWaiverFinanceApplicationByReferenceNo(referenceNo);
        return new ResponseEntity<WaiverFinanceApplication>(billingTransformer.toWaiverFinanceApplicationVo(waiverApplication), HttpStatus.OK);
    }

    @RequestMapping(value = "/waiverFinanceApplications/{referenceNo}", method = RequestMethod.PUT)
    public ResponseEntity<WaiverFinanceApplication> updatefinanceWaiverApplication(@PathVariable String referenceNo, @RequestBody WaiverFinanceApplication vo) {
    	AcWaiverFinanceApplication waiverApplication = (AcWaiverFinanceApplication) billingService.findWaiverFinanceApplicationByReferenceNo(referenceNo);
        return new ResponseEntity<WaiverFinanceApplication>(billingTransformer.toWaiverFinanceApplicationVo(waiverApplication), HttpStatus.OK);
    }

    @RequestMapping(value = "/waiverFinanceApplications/assignedTasks", method = RequestMethod.GET)
    public ResponseEntity<List<WaiverFinanceApplicationTask>> findAssignedWaiverFinanceApplications() {
        
        List<Task> tasks = billingService.findAssignedWaiverFinanceApplicationTasks(0, 100);
        return new ResponseEntity<List<WaiverFinanceApplicationTask>>(billingTransformer.toWaiverFinanceApplicationTaskVos(tasks), HttpStatus.OK);
    }

    @RequestMapping(value = "/waiverFinanceApplications/pooledTasks", method = RequestMethod.GET)
    public ResponseEntity<List<WaiverFinanceApplicationTask>> findPooledWaiverApplications() {
        
        List<Task> tasks = billingService.findPooledWaiverFinanceApplicationTasks(0, 100);
        return new ResponseEntity<List<WaiverFinanceApplicationTask>>(billingTransformer.toWaiverFinanceApplicationTaskVos(tasks), HttpStatus.OK);
    }

    @RequestMapping(value = "/waiverFinanceApplications/state/{state}", method = RequestMethod.GET)
    public ResponseEntity<List<WaiverFinanceApplication>> findWaiverFinanceApplicationsByFlowState(@PathVariable String state) {
        List<AcWaiverFinanceApplication> waiverApplications = billingService.findWaiverFinanceApplicationsByFlowState(AcFlowState.valueOf(state));
        return new ResponseEntity<List<WaiverFinanceApplication>>(billingTransformer.toWaiverFinanceApplicationVos(waiverApplications), HttpStatus.OK);
    }

    @RequestMapping(value = "/waiverFinanceApplications/archived", method = RequestMethod.GET)
    public ResponseEntity<List<WaiverFinanceApplication>> findArchivedWaiverFinanceApplications() {
        List<AcWaiverFinanceApplication> waiverApplications = billingService
                .findWaiverFinanceApplicationsByFlowStates(AcFlowState.COMPLETED, AcFlowState.CANCELLED, AcFlowState.REMOVED);
        return new ResponseEntity<List<WaiverFinanceApplication>>(billingTransformer
                .toWaiverFinanceApplicationVos(waiverApplications), HttpStatus.OK);
    }

    @RequestMapping(value = "/waiverFinanceApplications/startTask", method = RequestMethod.POST)
    public ResponseEntity<String> startWaiverFinanceApplicationTask(@RequestBody WaiverFinanceApplication vo) throws Exception {
        

        AcAcademicSession academicSession = accountService.findAcademicSessionById(vo.getAcademicSession().getId());
        AcAccount account = accountService.findAccountById(vo.getAccount().getId());
        AcWaiverFinanceApplication waiverFinanceApplication = new AcWaiverFinanceApplicationImpl();
        waiverFinanceApplication.setDescription(vo.getDescription());
        waiverFinanceApplication.setWaivedAmount(vo.getWaivedAmount());
        waiverFinanceApplication.setGracedAmount(BigDecimal.ZERO);
        waiverFinanceApplication.setEffectiveBalance(accountService.sumEffectiveBalanceAmount(account, academicSession));
        waiverFinanceApplication.setBalance(accountService.sumBalanceAmount(account));
        waiverFinanceApplication.setAccount(account);
        waiverFinanceApplication.setSession(academicSession);
        return new ResponseEntity<String>(billingService.startWaiverFinanceApplicationTask(waiverFinanceApplication), HttpStatus.OK);
    }

    @RequestMapping(value = "/waiverFinanceApplications/viewTask/{taskId}", method = RequestMethod.GET)
    public ResponseEntity<WaiverFinanceApplicationTask> findWaiverFinanceApplicationTaskByTaskId(@PathVariable String taskId) {
        return new ResponseEntity<WaiverFinanceApplicationTask>(billingTransformer
                .toWaiverFinanceApplicationTaskVo(
                		billingService.findWaiverFinanceApplicationTaskByTaskId(taskId)), HttpStatus.OK);
    }

    @RequestMapping(value = "/waiverFinanceApplications/claimTask", method = RequestMethod.POST)
    public void claimWaiverFinanceApplicationTask(@RequestBody WaiverFinanceApplicationTask vo) {
        
        Task task = billingService.findWaiverFinanceApplicationTaskByTaskId(vo.getTaskId());
        workflowService.claimTask(task);
    }

    @RequestMapping(value = "/waiverFinanceApplications/completeTask", method = RequestMethod.POST)
    public void completeWaiverFinanceApplicationTask(@RequestBody WaiverFinanceApplicationTask vo) {
        
        Task task = billingService.findWaiverFinanceApplicationTaskByTaskId(vo.getTaskId());
        workflowService.completeTask(task);
    }
   

//    @RequestMapping(value = "/refundPayments/pooledTasks", method = RequestMethod.GET)
//    public ResponseEntity<List<RefundPaymentTask>> findPooledRefundPayments() {
//        
//        List<Task> tasks = billingService.findPooledRefundPaymentTasks(0, 100);
//        return new ResponseEntity<List<RefundPaymentTask>>(billingTransformer.toRefundPaymentTaskVos(tasks), HttpStatus.OK);
//    }
//    
//    @RequestMapping(value = "/refundPayments/archived", method = RequestMethod.GET)
//    public ResponseEntity<List<RefundPayment>> findArchivedRefundPayments() {
//    	
//        List<AcRefundPayment> refundPayments = billingService.findRefundPaymentsByFlowStates(AcFlowState.CANCELLED, AcFlowState.REMOVED, AcFlowState.COMPLETED);
//        return new ResponseEntity<List<RefundPayment>>(billingTransformer.toRefundPaymentVos(refundPayments), HttpStatus.OK);
//    }
//    
//    @RequestMapping(value = "/refundPayments/startTask/{referenceNo}", method = RequestMethod.POST)
//    public ResponseEntity<String> startRefundPaymentTask(@PathVariable String referenceNo, @RequestBody RefundPayment vo) throws Exception {
//
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("academicSession", accountService.findCurrentAcademicSession());
//        String refNo = systemService.generateFormattedReferenceNo(AccountConstants.REFUND_REFRENCE_NO, map);
//
//        AcRefundPayment refundPayment = new AcRefundPaymentImpl();
//        refundPayment.setReferenceNo("123");
//        refundPayment.setSourceNo(vo.getSourceNo());
//        refundPayment.setAuditNo(vo.getAuditNo());
//        refundPayment.setIssuedDate(vo.getIssuedDate());
//        refundPayment.setDescription(vo.getDescription());
//        refundPayment.setAmount(vo.getAmount());
//        refundPayment.setPayments(billingService.findAdvancePaymentByReferenceNo(referenceNo));
//        billingService.startRefundPaymentTask(refundPayment);
//        return new ResponseEntity<String>(HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/refundPayments/viewTask/{taskId}", method = RequestMethod.GET)
//    public ResponseEntity<RefundPaymentTask> findRefundPaymentTaskByTaskId(@PathVariable String taskId) {
//        return new ResponseEntity<RefundPaymentTask>(billingTransformer
//                .toRefundPaymentTaskVo(
//                        billingService.findRefundPaymentTaskByTaskId(taskId)), HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/refundPayments/claimTask", method = RequestMethod.POST)
//    public ResponseEntity<String> claimRefundPaymentTask(@RequestBody RefundPaymentTask vo) {
//        
//        Task task = billingService.findRefundPaymentTaskByTaskId(vo.getTaskId());
//        workflowService.claimTask(task);
//        return new ResponseEntity<String>("Success", HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/refundPayments/releaseTask", method = RequestMethod.POST)
//    public ResponseEntity<String> releaseRefundPaymentTask(@RequestBody RefundPaymentTask vo) {
//        
//        Task task = billingService.findRefundPaymentTaskByTaskId(vo.getTaskId());
//        workflowService.releaseTask(task);
//        return new ResponseEntity<String>("Success", HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/refundPayments/completeTask", method = RequestMethod.POST)
//    public ResponseEntity<String> completeRefundPaymentTask(@RequestBody RefundPaymentTask vo) {
//        
//        Task task = billingService.findRefundPaymentTaskByTaskId(vo.getTaskId());
//        workflowService.completeTask(task);
//        return new ResponseEntity<String>("Success", HttpStatus.OK);
//    }
    
    // ====================================================================================================
    // PRIVATE METHODS
    // ====================================================================================================
}
