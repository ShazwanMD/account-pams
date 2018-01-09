package my.edu.umk.pams.account.web.module.billing.controller;

import static java.lang.Boolean.TRUE;
import static my.edu.umk.pams.account.AccountConstants.RECEIPT_REFERENCE_NO;
import static my.edu.umk.pams.account.workflow.service.WorkflowConstants.REMOVE_DECISION;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import my.edu.umk.pams.account.AccountConstants;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcAdvancePayment;
import my.edu.umk.pams.account.billing.model.AcCreditNote;
import my.edu.umk.pams.account.billing.model.AcCreditNoteImpl;
import my.edu.umk.pams.account.billing.model.AcCreditNoteItem;
import my.edu.umk.pams.account.billing.model.AcCreditNoteItemImpl;
import my.edu.umk.pams.account.billing.model.AcDebitNote;
import my.edu.umk.pams.account.billing.model.AcDebitNoteImpl;
import my.edu.umk.pams.account.billing.model.AcDebitNoteItem;
import my.edu.umk.pams.account.billing.model.AcDebitNoteItemImpl;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceImpl;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcInvoiceItemImpl;
import my.edu.umk.pams.account.billing.model.AcKnockoff;
import my.edu.umk.pams.account.billing.model.AcKnockoffAccountCharge;
import my.edu.umk.pams.account.billing.model.AcKnockoffAccountChargeImpl;
import my.edu.umk.pams.account.billing.model.AcKnockoffDebitNote;
import my.edu.umk.pams.account.billing.model.AcKnockoffDebitNoteImpl;
import my.edu.umk.pams.account.billing.model.AcKnockoffImpl;
import my.edu.umk.pams.account.billing.model.AcKnockoffInvoice;
import my.edu.umk.pams.account.billing.model.AcKnockoffItem;
import my.edu.umk.pams.account.billing.model.AcKnockoffItemImpl;
import my.edu.umk.pams.account.billing.model.AcReceipt;
import my.edu.umk.pams.account.billing.model.AcReceiptAccountCharge;
import my.edu.umk.pams.account.billing.model.AcReceiptAccountChargeImpl;
import my.edu.umk.pams.account.billing.model.AcReceiptDebitNote;
import my.edu.umk.pams.account.billing.model.AcReceiptDebitNoteImpl;
import my.edu.umk.pams.account.billing.model.AcReceiptImpl;
import my.edu.umk.pams.account.billing.model.AcReceiptInvoice;
import my.edu.umk.pams.account.billing.model.AcReceiptInvoiceImpl;
import my.edu.umk.pams.account.billing.model.AcReceiptItem;
import my.edu.umk.pams.account.billing.model.AcReceiptItemImpl;
import my.edu.umk.pams.account.billing.model.AcReceiptType;
import my.edu.umk.pams.account.billing.model.AcRefundPayment;
import my.edu.umk.pams.account.billing.model.AcRefundPaymentImpl;
import my.edu.umk.pams.account.billing.model.AcWaiverAccountCharge;
import my.edu.umk.pams.account.billing.model.AcWaiverAccountChargeImpl;
import my.edu.umk.pams.account.billing.model.AcWaiverDebitNote;
import my.edu.umk.pams.account.billing.model.AcWaiverDebitNoteImpl;
import my.edu.umk.pams.account.billing.model.AcWaiverFinanceApplication;
import my.edu.umk.pams.account.billing.model.AcWaiverFinanceApplicationImpl;
import my.edu.umk.pams.account.billing.model.AcWaiverInvoice;
import my.edu.umk.pams.account.billing.model.AcWaiverInvoiceImpl;
import my.edu.umk.pams.account.billing.model.AcWaiverItem;
import my.edu.umk.pams.account.billing.model.AcWaiverItemImpl;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.model.AcPaymentMethod;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.account.system.service.SystemService;
import my.edu.umk.pams.account.util.DaoQuery;
import my.edu.umk.pams.account.web.module.account.vo.Account;
import my.edu.umk.pams.account.web.module.billing.vo.AdvancePayment;
import my.edu.umk.pams.account.web.module.billing.vo.CreditNote;
import my.edu.umk.pams.account.web.module.billing.vo.CreditNoteItem;
import my.edu.umk.pams.account.web.module.billing.vo.CreditNoteTask;
import my.edu.umk.pams.account.web.module.billing.vo.DebitNote;
import my.edu.umk.pams.account.web.module.billing.vo.DebitNoteItem;
import my.edu.umk.pams.account.web.module.billing.vo.DebitNoteTask;
import my.edu.umk.pams.account.web.module.billing.vo.Invoice;
import my.edu.umk.pams.account.web.module.billing.vo.InvoiceItem;
import my.edu.umk.pams.account.web.module.billing.vo.InvoiceTask;
import my.edu.umk.pams.account.web.module.billing.vo.Knockoff;
import my.edu.umk.pams.account.web.module.billing.vo.KnockoffAccountCharge;
import my.edu.umk.pams.account.web.module.billing.vo.KnockoffDebitNote;
import my.edu.umk.pams.account.web.module.billing.vo.KnockoffInvoice;
import my.edu.umk.pams.account.web.module.billing.vo.KnockoffItem;
import my.edu.umk.pams.account.web.module.billing.vo.KnockoffTask;
import my.edu.umk.pams.account.web.module.billing.vo.Receipt;
import my.edu.umk.pams.account.web.module.billing.vo.ReceiptAccountCharge;
import my.edu.umk.pams.account.web.module.billing.vo.ReceiptDebitNote;
import my.edu.umk.pams.account.web.module.billing.vo.ReceiptInvoice;
import my.edu.umk.pams.account.web.module.billing.vo.ReceiptItem;
import my.edu.umk.pams.account.web.module.billing.vo.ReceiptTask;
import my.edu.umk.pams.account.web.module.billing.vo.RefundPayment;
import my.edu.umk.pams.account.web.module.billing.vo.RefundPaymentTask;
import my.edu.umk.pams.account.web.module.billing.vo.WaiverAccountCharge;
import my.edu.umk.pams.account.web.module.billing.vo.WaiverDebitNote;
import my.edu.umk.pams.account.web.module.billing.vo.WaiverFinanceApplication;
import my.edu.umk.pams.account.web.module.billing.vo.WaiverFinanceApplicationTask;
import my.edu.umk.pams.account.web.module.billing.vo.WaiverInvoice;
import my.edu.umk.pams.account.web.module.billing.vo.WaiverItem;
import my.edu.umk.pams.account.web.module.util.vo.CovalentDtQuery;
import my.edu.umk.pams.account.workflow.service.WorkflowService;

@Transactional
@RestController
@RequestMapping("/api/billing")
public class BillingController {

    private static final Logger LOG = LoggerFactory.getLogger(BillingController.class);

    @Autowired
    private BillingService billingService;
    
    @Autowired
    private SecurityService securityService;

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
        for(AcInvoice inv: invoices){
        	LOG.debug("unpaid invoice {}", inv.getInvoiceNo());
        }
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
    
    @RequestMapping(value = "/invoices/removeTask", method = RequestMethod.POST)
 	public void removeInvoiceTask(@RequestBody InvoiceTask vo) {

       Task task = billingService.findInvoiceTaskByTaskId(vo.getTaskId());
       LOG.debug("Task id {}", task.getId());
       Map<String, Object> variables = new HashMap<String, Object>();
       variables.put(REMOVE_DECISION, TRUE);
       workflowService.completeTask(task, variables);
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

    @RequestMapping(value = "/receipts/{referenceNo}", method = RequestMethod.PUT)
    public ResponseEntity<Receipt> updateReceipt(@PathVariable String referenceNo, @RequestBody Receipt vo) {
        AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
        receipt.setTotalPayment(vo.getTotalPayment());
        billingService.updateReceipt(receipt);
        return new ResponseEntity<Receipt>(billingTransformer.toReceiptVo(receipt), HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts/{referenceNo}/receiptItems", method = RequestMethod.GET)
    public ResponseEntity<List<ReceiptItem>> findReceiptItems(@PathVariable String referenceNo) {
        
        AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
        return new ResponseEntity<List<ReceiptItem>>(billingTransformer
                .toReceiptItemVos(billingService.findReceiptItems(receipt)), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/receipts/{referenceNo}/items/invoices/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<ReceiptItem>> findInvoiceReceiptItems(@PathVariable String referenceNo, @PathVariable Long id) {
        
        AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
        AcInvoice invoice = billingService.findInvoiceById(id);
        return new ResponseEntity<List<ReceiptItem>>(billingTransformer
                .toReceiptItemVos(billingService.findReceiptItems(receipt, invoice)), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/receipts/{referenceNo}/items/debitNotes/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<ReceiptItem>> findDebitNoteReceiptItems(@PathVariable String referenceNo, @PathVariable Long id) {
        
        AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
        AcDebitNote debitNote = billingService.findDebitNoteById(id);
        return new ResponseEntity<List<ReceiptItem>>(billingTransformer
                .toReceiptItemVos(billingService.findReceiptItems(receipt, debitNote)), HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts/{referenceNo}/receiptItems", method = RequestMethod.POST)
    public void addReceiptItems(@PathVariable String referenceNo, @RequestBody ReceiptItem vo) {
        
        AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
        AcReceiptItem e = new AcReceiptItemImpl();
        
     switch (vo.getReceiptItemType()) {  
        case ACCOUNT_CHARGE:             	       	
        AcAccountCharge accountCharge = accountService.findAccountChargeById(vo.getAccountCharge().getId());
       	Boolean chargeRcptItem = billingService.hasChargeReceiptItem(accountCharge, receipt);
    	
       	if(chargeRcptItem == false) {
        e.setAdjustedAmount(vo.getAdjustedAmount());
        e.setAppliedAmount(vo.getAppliedAmount());
        e.setTotalAmount(vo.getTotalAmount());
        e.setDueAmount(vo.getDueAmount());
        e.setUnit(vo.getUnit());
        e.setPrice(vo.getPrice());
        e.setDescription(vo.getDescription());
        if (null != vo.getAccountCharge())
        e.setAccountCharge(accountCharge);
        e.setChargeCode(accountService.findChargeCodeById(0L));
        billingService.addReceiptItem(receipt, e);
     }
        break;        
        
        case DEBIT_NOTE:       	       	
        AcDebitNote debitNote = billingService.findDebitNoteById(vo.getDebitNote().getId());
      	Boolean debitRcptItem = billingService.hasDebitReceiptItem(debitNote, receipt);        	
      	
      	if(debitRcptItem == false) {
          e.setAdjustedAmount(vo.getAdjustedAmount());
          e.setAppliedAmount(vo.getAppliedAmount());
          e.setTotalAmount(vo.getTotalAmount());
          e.setDueAmount(vo.getDueAmount());
          e.setUnit(vo.getUnit());
          e.setPrice(vo.getPrice());
          e.setDescription(vo.getDescription());
          if (null != vo.getDebitNote())
          e.setDebitNote(debitNote);
          billingService.addReceiptItem(receipt, e);
      	}
        break;
     }
    }
        

    @RequestMapping(value = "/receipts/{referenceNo}/receiptItems/{id}", method = RequestMethod.PUT)
    public void updateReceiptItems(@PathVariable String referenceNo, @PathVariable Long id, @RequestBody ReceiptItem vo) {
        
        AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
        AcReceiptItem e = billingService.findReceiptItemById(id);
        if (null != vo.getChargeCode())
        e.setChargeCode(accountService.findChargeCodeById(vo.getChargeCode().getId()));
        if (null != vo.getAccountCharge())
        e.setAccountCharge(accountService.findAccountChargeById(vo.getAccountCharge().getId()));
        e.setChargeCode(accountService.findChargeCodeById(0L));
        if (null != vo.getDebitNote())
        e.setDebitNote(billingService.findDebitNoteById(vo.getDebitNote().getId()));
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
    
    @RequestMapping(value = "/receipts/{referenceNo}/receiptAccountCharge", method = RequestMethod.GET)
    public ResponseEntity<List<ReceiptAccountCharge>> findReceiptsByAccountCharge(@PathVariable String referenceNo) {
        
        AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
        return new ResponseEntity<List<ReceiptAccountCharge>>(billingTransformer.
        		toReceiptAccountChargeVos(billingService.findReceiptsAccountCharge(receipt)), HttpStatus.OK);
             
    }
    
    @RequestMapping(value = "/receipts/{referenceNo}/receiptDebitNote", method = RequestMethod.GET)
    public ResponseEntity<List<ReceiptDebitNote>> findReceiptsByDebitNote(@PathVariable String referenceNo) {
        
        AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
        return new ResponseEntity<List<ReceiptDebitNote>>(billingTransformer.
        		toReceiptDebitNoteVos(billingService.findReceiptsDebitNote(receipt)), HttpStatus.OK);
             
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

    	String referenceNo = vo.getReceipt().getReferenceNo();
    	AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
  
    	int receiptItem = billingService.countReceiptItem(receipt);
    	
    	LOG.debug("receiptItem {}", receiptItem);
    	
    	if (receiptItem == 0) {
    		throw new IllegalArgumentException("Please Enter Receipt Item");
    	}
    	else if (receiptItem > 0) {
    		LOG.debug("receiptItem {}", receiptItem);
    		Task task = billingService.findReceiptTaskByTaskId(vo.getTaskId());
    		workflowService.completeTask(task);   	}
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
        Boolean rcptItem = billingService.hasReceiptItem(invoice, receipt);
        
        if(rcptItem == false) {
        	billingService.itemToReceiptItem(invoice, receipt); 
        }
    }
    
    @RequestMapping(value = "/receipts/updateReceiptItems/{referenceNo}/receiptItems/{id}", method = RequestMethod.PUT)
    public void updateItemToReceipt(@PathVariable String referenceNo, @PathVariable Long id, @RequestBody ReceiptItem vo) {
    	
    	AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
    	AcReceiptItem receiptItem = billingService.findReceiptItemById(id);
    	
    		receiptItem.setAppliedAmount(vo.getAppliedAmount());
    		billingService.updateReceiptItem(receipt, receiptItem);
    		
        //billingService.updateItemToReceipt(receipt);
    }
    
    @RequestMapping(value = "/receipts/{referenceNo}/accountCharge/{id}", method = RequestMethod.POST)
    public void addReceiptCharge(@PathVariable String referenceNo, @PathVariable Long id) {
        
        AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
        AcAccountCharge charge = accountService.findAccountChargeById(id);
        AcReceiptAccountCharge e = new AcReceiptAccountChargeImpl();
        e.setReceipt(receipt);
        e.setAccountCharge(charge);
        billingService.addReceiptCharge(receipt, charge);
    }
    
    @RequestMapping(value = "/receipts/{referenceNo}/debitNote/{id}", method = RequestMethod.POST)
    public void addReceiptDebitNote(@PathVariable String referenceNo, @PathVariable Long id) {
        
        AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
        AcDebitNote debitNote = billingService.findDebitNoteById(id);
        AcReceiptDebitNote e = new AcReceiptDebitNoteImpl();
        e.setReceipt(receipt);
        e.setDebitNote(debitNote);
        billingService.addReceiptDebitNote(receipt, debitNote);
    }
    
    @RequestMapping(value = "/receipts/removeTask", method = RequestMethod.POST)
 	public void removeReceiptTask(@RequestBody ReceiptTask vo) {

       Task task = billingService.findReceiptTaskByTaskId(vo.getTaskId());
       LOG.debug("Task id {}", task.getId());
       Map<String, Object> variables = new HashMap<String, Object>();
       variables.put(REMOVE_DECISION, TRUE);
       workflowService.completeTask(task, variables);
    }
    
    @RequestMapping(value = "/receiptInvoices/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteReceiptInvoices(@PathVariable Long id) {
        
        AcReceiptInvoice e = billingService.findReceiptInvoiceById(id);
        billingService.deleteReceiptInvoice(e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/receiptAccCharges/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteReceiptAccCharges(@PathVariable Long id) {
        
    	AcReceiptAccountCharge e = billingService.findReceiptAccChargeById(id);
        billingService.deleteReceiptAccCharge(e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/receiptDebitNotes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteReceiptDebitNotes(@PathVariable Long id) {
        
    	AcReceiptDebitNote e = billingService.findReceiptDebitNoteById(id);
        billingService.deleteReceiptDebitNote(e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/receipts/{referenceNo}/receiptItems/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteReceiptItem(@PathVariable String referenceNo, @PathVariable Long id) {
        
    	AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
    	AcReceiptItem receiptItem = billingService.findReceiptItemById(id);
        billingService.deleteReceiptItem(receipt, receiptItem);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/receipts/{referenceNo}/invoices/{id}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> hasReceiptItem(@PathVariable String referenceNo, @PathVariable Long id) {

    	AcReceipt receipt = billingService.findReceiptByReferenceNo(referenceNo);
        AcInvoice invoice = billingService.findInvoiceById(id);
        billingService.hasReceiptItem(invoice, receipt);
        return new ResponseEntity<Boolean>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/receipts/{referenceNo}/debitNotes/{id}", method = RequestMethod.POST)
    public void debitItemToReceiptItem(@PathVariable Long id, @PathVariable String referenceNo) {
    	
    	AcReceipt receipt = billingService.findReceiptById(id);
    	AcDebitNote debitNote = billingService.findDebitNoteByReferenceNo(referenceNo);
        Boolean rcptItem = billingService.hasDebitReceiptItem(debitNote, receipt);
        
        if(rcptItem == false) {
        	billingService.debitItemToReceiptItem(debitNote, receipt); 
        }
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
    public ResponseEntity<List<DebitNote>> findArchivedDebitNotes() {
        List<AcDebitNote> debitNotes = billingService.findDebitNotesByFlowStates(AcFlowState.CANCELLED, AcFlowState.REMOVED, AcFlowState.COMPLETED);
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
        e.setBalanceAmount(item.getAmount());
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
        e.setBalanceAmount(item.getAmount());
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
        //debitNote.setChargeCode(accountService.findChargeCodeById(vo.getChargeCode().getId()));
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
    
    @RequestMapping(value = "/debitnotes/unpaidDebitNotes/{code}", method = RequestMethod.GET)
    public ResponseEntity<List<DebitNote>> findUnpaidDebitNotes(@PathVariable String code) {
        AcAccount account = accountService.findAccountByCode(code);
        LOG.debug("unpaid acc debit notes {}", account.getCode());
        List<AcDebitNote> debitNotes = billingService.findUnpaidDebitNotes(account, 0, 100);
        for(AcDebitNote inv: debitNotes){
        	LOG.debug("unpaid debit notes {}", inv.getReferenceNo());
        }
        return new ResponseEntity<List<DebitNote>>(billingTransformer.toDebitNoteVos(debitNotes), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/debitnotes/removeTask", method = RequestMethod.POST)
 	public void removeDebitNoteTask(@RequestBody DebitNoteTask vo) {

       Task task = billingService.findDebitNoteTaskByTaskId(vo.getTaskId());
       LOG.debug("Task id {}", task.getId());
       Map<String, Object> variables = new HashMap<String, Object>();
       variables.put(REMOVE_DECISION, TRUE);
       workflowService.completeTask(task, variables);
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
    public ResponseEntity<List<CreditNote>> findArchivedCreditNotes() {
        List<AcCreditNote> creditNotes = billingService.findCreditNotesByFlowStates(AcFlowState.CANCELLED, AcFlowState.REMOVED, AcFlowState.COMPLETED);
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
        e.setBalanceAmount(item.getBalanceAmount());
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
        e.setBalanceAmount(item.getBalanceAmount());
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
        //creditNote.setTotalAmount(vo.getTotalAmount());
        creditNote.setInvoice(billingService.findInvoiceById(vo.getInvoice().getId()));
        //creditNote.setChargeCode(accountService.findChargeCodeById(vo.getChargeCode().getId()));
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
    
    @RequestMapping(value = "/creditNotes/removeTask", method = RequestMethod.POST)
 	public void removeCreditNoteTask(@RequestBody CreditNoteTask vo) {

       Task task = billingService.findCreditNoteTaskByTaskId(vo.getTaskId());
       LOG.debug("Task id {}", task.getId());
       Map<String, Object> variables = new HashMap<String, Object>();
       variables.put(REMOVE_DECISION, TRUE);
       workflowService.completeTask(task, variables);
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
    
    @RequestMapping(value = "/knockoffs/{referenceNo}/knockoffInvoice", method = RequestMethod.GET)
    public ResponseEntity<List<KnockoffInvoice>> findKnockoffsByInvoice(@PathVariable String referenceNo) {
        
        AcKnockoff knockoff = billingService.findKnockoffByReferenceNo(referenceNo);
        return new ResponseEntity<List<KnockoffInvoice>>(billingTransformer
                .toKnockoffInvoiceVos(billingService.findKnockoffs(knockoff)), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/knockoffs/{referenceNo}/knockoffAccountCharge", method = RequestMethod.GET)
    public ResponseEntity<List<KnockoffAccountCharge>> findKnockoffsByAccountCharge(@PathVariable String referenceNo) {
        
        AcKnockoff knockoff = billingService.findKnockoffByReferenceNo(referenceNo);
        return new ResponseEntity<List<KnockoffAccountCharge>>(billingTransformer
                .toKnockoffAccountChargeVos(billingService.findKnockoffAccountCharges(knockoff)), HttpStatus.OK);
    }
    
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
        knockoff.setTotalAmount(vo.getTotalAmount());
        knockoff.setBalanceAmount(vo.getBalanceAmount());
        knockoff.setPayments(billingService.findAdvancePaymentById(vo.getPayments().getId()));
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
        
    	String referenceNo = vo.getKnockoff().getReferenceNo();
    	AcKnockoff knockoff = billingService.findKnockoffByReferenceNo(referenceNo);
    	
    	int knockoffItem = billingService.countKnockoffItem(knockoff);
    	
    	if(knockoffItem == 0) {
    		throw new IllegalArgumentException("Please enter knockoff item"); }
    	else if (knockoffItem > 0) {
	        Task task = billingService.findKnockoffTaskByTaskId(vo.getTaskId());
	        workflowService.completeTask(task); }
	        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/knockoffs/removeTask", method = RequestMethod.POST)
 	public void removeKnockoffTask(@RequestBody KnockoffTask vo) {

       Task task = billingService.findKnockoffTaskByTaskId(vo.getTaskId());
       LOG.debug("Task id {}", task.getId());
       Map<String, Object> variables = new HashMap<String, Object>();
       variables.put(REMOVE_DECISION, TRUE);
       workflowService.completeTask(task, variables);
    }
    
    @RequestMapping(value = "/knockoffs/{referenceNo}/accountCharge/{id}", method = RequestMethod.POST)
    public void addKnockoffAccountCharge(@PathVariable String referenceNo, @PathVariable Long id) {
        
        AcKnockoff knockoff = billingService.findKnockoffByReferenceNo(referenceNo);
        AcAccountCharge accountCharge = accountService.findAccountChargeById(id);
        AcKnockoffAccountCharge e = new AcKnockoffAccountChargeImpl();
        e.setKnockoff(knockoff);
        e.setAccountCharge(accountCharge);
        billingService.addKnockoffAccountCharge(knockoff, accountCharge);
    }
    
    @RequestMapping(value = "/knockoffs/{referenceNo}/invoice/{id}", method = RequestMethod.POST)
    public void addKnockoffInvoice(@PathVariable String referenceNo, @PathVariable Long id) {
        
        AcKnockoff knockoff = billingService.findKnockoffByReferenceNo(referenceNo);
        AcInvoice invoice = billingService.findInvoiceById(id);
//        AcKnockoffInvoice e = new AcKnockoffInvoiceImpl();
//        e.setKnockoff(knockoff);
//        e.setInvoice(invoice);
        billingService.addKnockoffInvoice(knockoff, invoice);
    }
    
    @RequestMapping(value = "/knockoffs/{referenceNo}/invoices/{id}", method = RequestMethod.POST)
    public void itemToKnockoffItem(@PathVariable Long id, @PathVariable String referenceNo) {
    	
    	AcKnockoff knockoff = billingService.findKnockoffByReferenceNo(referenceNo);
        AcInvoice invoice = billingService.findInvoiceById(id);
        Boolean kncfItem = billingService.hasKnockoff(knockoff, invoice);
        
        if(kncfItem == false) {
        	billingService.itemToKnockoffItem(invoice, knockoff);
        }
    }
    
    @RequestMapping(value = "/knockoffs/{referenceNo}/debitNotes/{id}", method = RequestMethod.POST)
    public void debitToKnockoffItem(@PathVariable Long id, @PathVariable String referenceNo) {
    	
    	AcKnockoff knockoff = billingService.findKnockoffByReferenceNo(referenceNo);
    	AcDebitNote debitNote = billingService.findDebitNoteById(id);
        Boolean rcptItem = billingService.hasDebitKnockoffItem(debitNote, knockoff);
        
        if(rcptItem == false) {
        	billingService.debitToKnockoffItem(debitNote, knockoff); 
        }
    }
    
    @RequestMapping(value = "/knockoffs/{referenceNo}/knockoffItems", method = RequestMethod.GET)
    public ResponseEntity<List<KnockoffItem>> findKnockoffItems(@PathVariable String referenceNo) {
        
    	AcKnockoff knockoff = billingService.findKnockoffByReferenceNo(referenceNo);
        return new ResponseEntity<List<KnockoffItem>>(billingTransformer
                .toKnockoffItemVos(billingService.findAcKnockoffs(knockoff)), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/knockoffs/{referenceNo}/knockoffItems/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<KnockoffItem>> findKnockoffItemsByInvoice(@PathVariable String referenceNo, @PathVariable Long id) {
        
    	AcKnockoff knockoff = billingService.findKnockoffByReferenceNo(referenceNo);
    	AcInvoice invoice = billingService.findInvoiceById(id);
        return new ResponseEntity<List<KnockoffItem>>(billingTransformer
                .toKnockoffItemVos(billingService.findAcKnockoffs(knockoff,invoice)), HttpStatus.OK);
    }
       
    @RequestMapping(value = "/knockoffs/updateKnockoffItems/{referenceNo}/knockoffItems/{id}", method = RequestMethod.PUT)
    public void updateitemToKnockoff(@PathVariable String referenceNo, @PathVariable Long id, @RequestBody ReceiptItem vo) {
    	
    	AcKnockoff knockoff = billingService.findKnockoffByReferenceNo(referenceNo);
    	AcKnockoffItem knockoffItem = billingService.findKnockoffItemById(id);
    	
    	knockoffItem.setAppliedAmount(vo.getAppliedAmount());
    	billingService.updateKnockoffItem(knockoff, knockoffItem);
    		
        //billingService.updateItemToReceipt(receipt);
    }
    
    @RequestMapping(value = "/knockoffs/{referenceNo}/debitNote/{id}", method = RequestMethod.POST)
    public void addKnockoffDebitNote(@PathVariable String referenceNo, @PathVariable Long id) {
        
        AcKnockoff knockoff = billingService.findKnockoffByReferenceNo(referenceNo);
        AcDebitNote debitNote = billingService.findDebitNoteById(id);
        AcKnockoffDebitNote e = new AcKnockoffDebitNoteImpl();
        e.setKnockoff(knockoff);
        e.setDebitNote(debitNote);
        billingService.addKnockoffDebitNote(knockoff, debitNote);
    }
    
    @RequestMapping(value = "/knockoffs/{referenceNo}", method = RequestMethod.PUT)
    public ResponseEntity<Knockoff> updateKnockoff(@PathVariable String referenceNo, @RequestBody Knockoff vo) {
    	AcKnockoff knockoff = billingService.findKnockoffByReferenceNo(referenceNo);
    	knockoff.setTotalAmount(vo.getTotalAmount());
        billingService.updateKnockoff(knockoff);
        return new ResponseEntity<Knockoff>(billingTransformer.toKnockoffVo(knockoff), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/knockoffs/{referenceNo}/knockoffDebitNote", method = RequestMethod.GET)
    public ResponseEntity<List<KnockoffDebitNote>> findKnockoffsByDebitNote(@PathVariable String referenceNo) {
        
        AcKnockoff knockoff = billingService.findKnockoffByReferenceNo(referenceNo);
        return new ResponseEntity<List<KnockoffDebitNote>>(billingTransformer
                .toKnockoffDebitNoteVos(billingService.findKnockoffDebitNotes(knockoff)), HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/knockoffs/{referenceNo}/knockoffItems", method = RequestMethod.POST)
    public void addKnockoffItem(@PathVariable String referenceNo, @RequestBody KnockoffItem vo) {
        
        LOG.debug("referenceNo: {}", referenceNo);

        AcKnockoff knockoff = billingService.findKnockoffByReferenceNo(referenceNo);
        AcKnockoffItem e = new AcKnockoffItemImpl();
        
        LOG.debug("knockoff: {}", knockoff);
        LOG.debug("knockoff item: {}", e);
        
        switch (vo.getKnockoffItemType()) {  
        case ACCOUNT_CHARGE:             	       	
        AcAccountCharge accountCharge = accountService.findAccountChargeById(vo.getAccountCharge().getId());
       	Boolean chargeKnockoffItem = billingService.hasChargeKnockoffItem(accountCharge, knockoff);
    	
       	if(chargeKnockoffItem == false) {
        e.setAppliedAmount(vo.getAppliedAmount());
        e.setTotalAmount(vo.getTotalAmount());
        e.setDueAmount(vo.getDueAmount());
        e.setDescription(vo.getDescription());
        if (null != vo.getAccountCharge())
        e.setAccountCharge(accountCharge);
        e.setChargeCode(accountService.findChargeCodeById(0L));
        billingService.addKnockoffItem(knockoff, e);
     }
        break;        
        
        case DEBIT_NOTE:       	       	
        AcDebitNote debitNote = billingService.findDebitNoteById(vo.getDebitNote().getId());
      	Boolean debitKnockoffItem = billingService.hasDebitKnockoffItem(debitNote, knockoff);        	
      	
      	if(debitKnockoffItem == false) {
          e.setAppliedAmount(vo.getAppliedAmount());
          e.setTotalAmount(vo.getTotalAmount());
          e.setDueAmount(vo.getDueAmount());
          e.setDescription(vo.getDescription());
          if (null != vo.getDebitNote())
          e.setDebitNote(debitNote);
          billingService.addKnockoffItem(knockoff, e);
      	}
        break;
     }
    }
    

           
    @RequestMapping(value = "/knockoffs/{referenceNo}/knockoffItems/{id}", method = RequestMethod.PUT)
    public void updateKnockoffItems(@PathVariable String referenceNo, @PathVariable Long id, @RequestBody KnockoffItem vo) {
        
    	AcKnockoff knockoff = billingService.findKnockoffByReferenceNo(referenceNo);
    	LOG.debug("Knockoff cntroller {}", knockoff.getReferenceNo());
    	AcKnockoffItem e = billingService.findKnockoffItemById(id);
    	
        if (null != vo.getChargeCode())
        e.setChargeCode(accountService.findChargeCodeById(vo.getChargeCode().getId()));
        if (null != vo.getAccountCharge())
        e.setAccountCharge(accountService.findAccountChargeById(vo.getAccountCharge().getId()));
        //e.setChargeCode(accountService.findChargeCodeById(0L));
        if (null != vo.getDebitNote())
        e.setDebitNote(billingService.findDebitNoteById(vo.getDebitNote().getId()));
        e.setTotalAmount(vo.getTotalAmount());
        e.setAppliedAmount(vo.getAppliedAmount());
        e.setTotalAmount(vo.getTotalAmount());
        e.setDescription(vo.getDescription());
        billingService.updateKnockoffItem(knockoff, e);
    }
    
    @RequestMapping(value = "/knockoffs/{referenceNo}/items/invoices/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<KnockoffItem>> findInvoiceKnockoffItems(@PathVariable String referenceNo, @PathVariable Long id) {
        
    	AcKnockoff knockoff = billingService.findKnockoffByReferenceNo(referenceNo);
        AcInvoice invoice = billingService.findInvoiceById(id);
        return new ResponseEntity<List<KnockoffItem>>(billingTransformer
        		.toKnockoffItemVos(billingService.findInvoiceKnockoffItem(invoice, knockoff)), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/knockoffs/{referenceNo}/items/debits/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<KnockoffItem>> findDebitKnockoffItems(@PathVariable String referenceNo, @PathVariable Long id) {
        
    	AcKnockoff knockoff = billingService.findKnockoffByReferenceNo(referenceNo);
    	AcDebitNote debitNote = billingService.findDebitNoteById(id);
        return new ResponseEntity<List<KnockoffItem>>(billingTransformer
                .toKnockoffItemVos(billingService.findDebitKnockoffItem(debitNote, knockoff)), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/knockoffDebitNotes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteKnockoffDebitNotes(@PathVariable Long id) {
        
    	AcKnockoffDebitNote e = billingService.findKnockoffDebitNoteById(id);
        billingService.deleteKnockoffDebitNote(e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/knockoffInvoices/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteKnockoffInvoices(@PathVariable Long id) {
        
    	AcKnockoffInvoice e = billingService.findKnockoffInvoiceById(id);
        billingService.deleteKnockoffInvoice(e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/knockoffAccCharges/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteKnockoffAccCharges(@PathVariable Long id) {
        
    	AcKnockoffAccountCharge e = billingService.findKnockoffAccChargeById(id);
        billingService.deleteKnockoffAccCharge(e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/knockoffs/{referenceNo}/knockoffItems/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteKnockoffItem(@PathVariable String referenceNo, @PathVariable Long id) {
        
    	AcKnockoff knockoff = billingService.findKnockoffByReferenceNo(referenceNo);
    	AcKnockoffItem e = billingService.findKnockoffItemById(id);
        billingService.deleteKnockoffItem(knockoff, e);
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
    
    @RequestMapping(value = "/refundPayments/state/COMPLETED", method = RequestMethod.GET)
    public ResponseEntity<List<RefundPayment>> findCompletedRefundPayments() {
    	
        List<AcRefundPayment> refundPayments = billingService.findRefundPaymentsByFlowStates(AcFlowState.COMPLETED);
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
    
    @RequestMapping(value = "/refundPayments/removeTask", method = RequestMethod.POST)
 	public void removeRefundPaymentTask(@RequestBody RefundPaymentTask vo) {

       Task task = billingService.findRefundPaymentTaskByTaskId(vo.getTaskId());
       LOG.debug("Task id {}", task.getId());
       Map<String, Object> variables = new HashMap<String, Object>();
       variables.put(REMOVE_DECISION, TRUE);
       workflowService.completeTask(task, variables);
    }
    
    @RequestMapping(value = "/refundPayments/vouchers/{referenceNo}", method = RequestMethod.PUT)
 	public void updateRefundPayments(@PathVariable String referenceNo, @RequestBody RefundPayment vo) {

       AcRefundPayment refundPayment = billingService.findRefundPaymentByReferenceNo(referenceNo);
	   LOG.debug("refundPayment controller {}", refundPayment.getReferenceNo());
       LOG.debug("refundPayment voucher controller {}", vo.getVoucherNo());
       refundPayment.setVoucherNo(vo.getVoucherNo());
       refundPayment.getFlowdata().setUpperApprovedDate(new Timestamp(System.currentTimeMillis()));
       refundPayment.getFlowdata().setUpperApproverId(securityService.getCurrentUser().getId());
       billingService.updateRefundPayment(refundPayment);
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
        waiverFinanceApplication.setGracedAmount(vo.getWaivedAmount());
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
        
    @RequestMapping(value = "/waiverFinanceApplications/{referenceNo}/waiverFinanceApplicationItems", method = RequestMethod.POST)
    public void addWaiverItem(@PathVariable String referenceNo, @RequestBody WaiverItem vo) {
        
        LOG.debug("referenceNo: {}", referenceNo);

        AcWaiverFinanceApplication waiverFinanceApplication = billingService.findWaiverFinanceApplicationByReferenceNo(referenceNo);
        AcWaiverItem e = new AcWaiverItemImpl();
        
        switch (vo.getWaiverItemType()) {  
        case ACCOUNT_CHARGE:             	       	
        AcAccountCharge accountCharge = accountService.findAccountChargeById(vo.getAccountCharge().getId());
       	Boolean chargeWaiverItem = billingService.hasChargeWaiverItem(accountCharge, waiverFinanceApplication);
    	
       	if(chargeWaiverItem == false) {
        e.setAppliedAmount(vo.getAppliedAmount());
        e.setTotalAmount(vo.getTotalAmount());
        e.setDueAmount(vo.getDueAmount());
        e.setDescription(vo.getDescription());
        if (null != vo.getAccountCharge())
        e.setAccountCharge(accountCharge);
        e.setChargeCode(accountService.findChargeCodeById(0L));
        billingService.addWaiverItem(waiverFinanceApplication, e);
     }
        break;        
        
        case DEBIT_NOTE:       	       	
        AcDebitNote debitNote = billingService.findDebitNoteById(vo.getDebitNote().getId());
      	Boolean debitWaiverItem = billingService.hasDebitWaiverItem(debitNote, waiverFinanceApplication);        	
      	
      	if(debitWaiverItem == false) {
          e.setAppliedAmount(vo.getAppliedAmount());
          e.setTotalAmount(vo.getTotalAmount());
          e.setDueAmount(vo.getDueAmount());
          e.setDescription(vo.getDescription());
          if (null != vo.getDebitNote())
          e.setDebitNote(debitNote);
          billingService.addWaiverItem(waiverFinanceApplication, e);
      	}
        break;
     }
    }
    
    @RequestMapping(value = "/waiverFinanceApplications/{referenceNo}/waiverFinanceApplicationItems/{id}", method = RequestMethod.PUT)
    public void updateWaiverItems(@PathVariable String referenceNo, @PathVariable Long id, @RequestBody WaiverItem vo) {
        
    	AcWaiverFinanceApplication waiverApplication = billingService.findWaiverFinanceApplicationByReferenceNo(referenceNo);
        AcWaiverItem e = billingService.findWaiverItemById(id);
        if (null != vo.getChargeCode())
        e.setChargeCode(accountService.findChargeCodeById(vo.getChargeCode().getId()));
        if (null != vo.getAccountCharge())
        e.setAccountCharge(accountService.findAccountChargeById(vo.getAccountCharge().getId()));
        //e.setChargeCode(accountService.findChargeCodeById(0L));
        if (null != vo.getDebitNote())
        e.setDebitNote(billingService.findDebitNoteById(vo.getDebitNote().getId()));
        e.setTotalAmount(vo.getTotalAmount());
        e.setAppliedAmount(vo.getAppliedAmount());
        e.setTotalAmount(vo.getTotalAmount());
        e.setDescription(vo.getDescription());
        billingService.updateWaiverItem(waiverApplication, e);
    }
    
    @RequestMapping(value = "/waiverFinanceApplications/{referenceNo}/invoice/{id}", method = RequestMethod.POST)
    public void addWaiverInvoice(@PathVariable String referenceNo, @PathVariable Long id) {
        
    	AcWaiverFinanceApplication waiverApplication = (AcWaiverFinanceApplication) billingService.findWaiverFinanceApplicationByReferenceNo(referenceNo);
        AcInvoice invoice = billingService.findInvoiceById(id);
        AcWaiverInvoice e = new AcWaiverInvoiceImpl();
        e.setWaiverFinanceApplication(waiverApplication);
        e.setInvoice(invoice);
        billingService.addWaiverInvoice(waiverApplication, invoice);
    }
    
    @RequestMapping(value = "/waiverFinanceApplications/{referenceNo}/accountCharge/{id}", method = RequestMethod.POST)
    public void addWaiverAccountCharge(@PathVariable String referenceNo, @PathVariable Long id) {
        
    	AcWaiverFinanceApplication waiverApplication = (AcWaiverFinanceApplication) billingService.findWaiverFinanceApplicationByReferenceNo(referenceNo);
        AcAccountCharge accountCharge = accountService.findAccountChargeById(id);
        
        AcWaiverAccountCharge e = new AcWaiverAccountChargeImpl();
        e.setWaiverFinanceApplication(waiverApplication);
        e.setAccountCharge(accountCharge);
        billingService.addWaiverAccountCharge(waiverApplication, accountCharge);
    }
    
    @RequestMapping(value = "/waiverFinanceApplications/{referenceNo}/debitNote/{id}", method = RequestMethod.POST)
    public void addWaiverDebitNote(@PathVariable String referenceNo, @PathVariable Long id) {
        
    	AcWaiverFinanceApplication waiverApplication = (AcWaiverFinanceApplication) billingService.findWaiverFinanceApplicationByReferenceNo(referenceNo);
        AcDebitNote debitNote = billingService.findDebitNoteById(id);
        
        AcWaiverDebitNote e = new AcWaiverDebitNoteImpl();
        e.setWaiverFinanceApplication(waiverApplication);
        e.setDebitNote(debitNote);
        billingService.addWaiverDebitNote(waiverApplication, debitNote);
    }
    
    @RequestMapping(value = "/waiverFinanceApplications/{referenceNo}/invoices/{id}", method = RequestMethod.POST)
    public void itemToWaiverItem(@PathVariable Long id, @PathVariable String referenceNo) {
    	
    	AcWaiverFinanceApplication waiverApplication = billingService.findWaiverFinanceApplicationByReferenceNo(referenceNo);
        AcInvoice invoice = billingService.findInvoiceById(id);
        Boolean wvrItem = billingService.hasWaiver(waiverApplication, invoice);
        
        if(wvrItem == false) {
        	billingService.itemToWaiverItem(waiverApplication, invoice);
        }
    }
    
    @RequestMapping(value = "/waiverFinanceApplications/{referenceNo}/debitNotes/{id}", method = RequestMethod.POST)
    public void debitToWaiverItem(@PathVariable Long id, @PathVariable String referenceNo) {
    	
    	AcWaiverFinanceApplication waiverApplication = billingService.findWaiverFinanceApplicationByReferenceNo(referenceNo);
    	AcDebitNote debitNote = billingService.findDebitNoteById(id);
        Boolean wvrItem = billingService.hasDebitWaiverItem(debitNote, waiverApplication);
        
        if(wvrItem == false) {
        	billingService.debitToWaiverItem(waiverApplication, debitNote);
        }
    }
    
    @RequestMapping(value = "/waiverFinanceApplications/waiverItems/{referenceNo}", method = RequestMethod.GET)
    public ResponseEntity<List<WaiverItem>> findWaiverItems(@PathVariable String referenceNo) {
    	AcWaiverFinanceApplication waiverApplication = (AcWaiverFinanceApplication) billingService.findWaiverFinanceApplicationByReferenceNo(referenceNo);
        return new ResponseEntity<List<WaiverItem>>(billingTransformer.toWaiverItemVos(billingService.findWaiverItems(waiverApplication)), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/waiverFinanceApplications/waiverInvoices/{referenceNo}", method = RequestMethod.GET)
    public ResponseEntity<List<WaiverInvoice>> findWaivers(@PathVariable String referenceNo) {
    	AcWaiverFinanceApplication waiverApplication = billingService.findWaiverFinanceApplicationByReferenceNo(referenceNo);
        return new ResponseEntity<List<WaiverInvoice>>(billingTransformer.toWaiverInvoiceVos(billingService.findWaivers(waiverApplication)), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/waiverFinanceApplications/{referenceNo}/updateWaivers", method = RequestMethod.PUT)
    public ResponseEntity<String> updateWaivers(@PathVariable String referenceNo, @RequestBody WaiverFinanceApplication vo) {
    	AcWaiverFinanceApplication waiverApplication = billingService.findWaiverFinanceApplicationByReferenceNo(referenceNo);
    	waiverApplication.setWaivedAmount(vo.getWaivedAmount());
        billingService.updateWaiverFinanceApplication(waiverApplication);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/waiverFinanceApplications/{referenceNo}/debitNotes", method = RequestMethod.GET)
    public ResponseEntity<List<WaiverDebitNote>> findWaiverByDebitNote(@PathVariable String referenceNo) {
        
    	AcWaiverFinanceApplication waiverApplication = billingService.findWaiverFinanceApplicationByReferenceNo(referenceNo);;
        return new ResponseEntity<List<WaiverDebitNote>>(billingTransformer
                .toWaiverDebitNoteVos(billingService.findDebitNote(waiverApplication)), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/waiverFinanceApplications/{referenceNo}/accountCharges", method = RequestMethod.GET)
    public ResponseEntity<List<WaiverAccountCharge>> findWaiverByAccountCharge(@PathVariable String referenceNo) {
        
    	AcWaiverFinanceApplication waiverApplication = billingService.findWaiverFinanceApplicationByReferenceNo(referenceNo);
        return new ResponseEntity<List<WaiverAccountCharge>>(billingTransformer
                .toWaiverAccountChargeVos(billingService.findWaiverAccountCharge(waiverApplication)), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/waiverFinanceApplications/removeTask", method = RequestMethod.POST)
 	public void removeWaiverFinanceApplicationTask(@RequestBody WaiverFinanceApplicationTask vo) {

       Task task = billingService.findWaiverFinanceApplicationTaskByTaskId(vo.getTaskId());
       LOG.debug("Task id {}", task.getId());
       Map<String, Object> variables = new HashMap<String, Object>();
       variables.put(REMOVE_DECISION, TRUE);
       workflowService.completeTask(task, variables);
    }
    
    @RequestMapping(value = "/waiverFinanceApplications/{referenceNo}/items/invoices/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<WaiverItem>> findInvoiceWaiverItems(@PathVariable String referenceNo, @PathVariable Long id) {
        
    	AcWaiverFinanceApplication waiverApplication = billingService.findWaiverFinanceApplicationByReferenceNo(referenceNo);
        AcInvoice invoice = billingService.findInvoiceById(id);
        return new ResponseEntity<List<WaiverItem>>(billingTransformer
                .toWaiverItemVos(billingService.findWaiverItems(waiverApplication, invoice)), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/waiverFinanceApplications/{referenceNo}/items/debitNotes/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<WaiverItem>> findDebitWaiverItems(@PathVariable String referenceNo, @PathVariable Long id) {
        
    	AcWaiverFinanceApplication waiverApplication = billingService.findWaiverFinanceApplicationByReferenceNo(referenceNo);
    	AcDebitNote debitNote = billingService.findDebitNoteById(id);
        return new ResponseEntity<List<WaiverItem>>(billingTransformer
                .toWaiverItemVos(billingService.findWaiverItems(waiverApplication, debitNote)), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/waiverDebitNotes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteWaiverDebitNotes(@PathVariable Long id) {
        
    	AcWaiverDebitNote e = billingService.findWaiverDebitNoteById(id);
        billingService.deleteWaiverDebitNote(e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/waiverInvoices/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteWaiverInvoices(@PathVariable Long id) {
        
    	AcWaiverInvoice e = billingService.findWaiverInvoiceById(id);
        billingService.deleteWaiverInvoice(e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/waiverAccCharges/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteWaiverAccCharges(@PathVariable Long id) {
        
    	AcWaiverAccountCharge e = billingService.findWaiverAccChargeById(id);
        billingService.deleteWaiverAccountCharge(e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/waiverFinanceApplications/{referenceNo}/waiverItems/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteWaiverItem(@PathVariable String referenceNo, @PathVariable Long id) {
        
    	AcWaiverFinanceApplication waiverApplication = billingService.findWaiverFinanceApplicationByReferenceNo(referenceNo);
        AcWaiverItem e = billingService.findWaiverItemById(id);
        billingService.deleteWaiverItem(waiverApplication, e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

}
