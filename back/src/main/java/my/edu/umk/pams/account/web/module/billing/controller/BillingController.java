package my.edu.umk.pams.account.web.module.billing.controller;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceImpl;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcInvoiceItemImpl;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.security.integration.AcAutoLoginToken;
import my.edu.umk.pams.account.system.service.SystemService;
import my.edu.umk.pams.account.web.module.billing.vo.Invoice;
import my.edu.umk.pams.account.web.module.billing.vo.InvoiceItem;
import my.edu.umk.pams.account.web.module.billing.vo.InvoiceTask;
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
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static my.edu.umk.pams.account.AccountConstants.INVOICE_REFERENCE_NO;

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

    @RequestMapping(value = "/invoices/{referenceNo}", method = RequestMethod.POST)
    public ResponseEntity<Invoice> updateInvoice(@PathVariable String referenceNo, @RequestBody Invoice vo) {
        AcInvoice invoice = (AcInvoice) billingService.findInvoiceByReferenceNo(referenceNo);
        return new ResponseEntity<Invoice>(billingTransformer.toInvoiceVo(invoice), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/{referenceNo:.+}/invoiceItems", method = RequestMethod.GET)
    public ResponseEntity<List<InvoiceItem>> findInvoiceItems(@PathVariable String referenceNo) {
        dummyLogin();
        String decode = URLDecoder.decode(referenceNo);
        LOG.debug("decoded: {}", decode);
        AcInvoice invoice = billingService.findInvoiceByReferenceNo(decode);
        return new ResponseEntity<List<InvoiceItem>>(billingTransformer
                .toInvoiceItemVos(billingService.findInvoiceItems(invoice)), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/{referenceNo}/invoiceItems", method = RequestMethod.POST)
    public void updateInvoiceItems(@PathVariable String referenceNo, @RequestBody InvoiceItem item) {
        dummyLogin();
        AcInvoice invoice = billingService.findInvoiceByReferenceNo(referenceNo);
        if (null == item.getId()) { // new
            AcInvoiceItem e = new AcInvoiceItemImpl();
            e.setChargeCode(accountService.findChargeCodeById(item.getChargeCode().getId()));
            e.setAmount(item.getAmount());
            e.setDescription(item.getDescription());
            billingService.addInvoiceItem(invoice, e);
        } else { // update
            AcInvoiceItem e = billingService.findInvoiceItemById(item.getId());
            e.setChargeCode(accountService.findChargeCodeById(item.getChargeCode().getId()));
            e.setAmount(item.getAmount());
            e.setDescription(item.getDescription());
            billingService.updateInvoiceItem(invoice, e);
        }
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
    public void startInvoiceTask(@RequestBody Invoice vo) throws Exception {
        dummyLogin();

        AcAccount account = accountService.findAccountById(vo.getPayer().getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("academicSession", accountService.findCurrentAcademicSession());
        String generated = systemService.generateFormattedReferenceNo(INVOICE_REFERENCE_NO, map);

        AcInvoice invoice = new AcInvoiceImpl();
        invoice.setReferenceNo(generated);
        invoice.setDescription(vo.getDescription());
        invoice.setTotalAmount(BigDecimal.ZERO);
        invoice.setBalanceAmount(BigDecimal.ZERO);
        invoice.setIssuedDate(new Date());
        invoice.setPaid(false);
        invoice.setAccount(account);
        billingService.startInvoiceTask(invoice);
    }

    @RequestMapping(value = "/invoices/viewTask/{taskId}", method = RequestMethod.GET)
    public ResponseEntity<InvoiceTask> findInvoiceTaskByTaskId(@PathVariable String taskId) {
        return new ResponseEntity<InvoiceTask>(billingTransformer
                .toInvoiceTaskVo(
                        billingService.findInvoiceTaskByTaskId(taskId)), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/claimTask", method = RequestMethod.POST)
    public void claimInvoiceTask(@RequestBody InvoiceTask vo) {
        dummyLogin();
        Task task = billingService.findInvoiceTaskByTaskId(vo.getTaskId());
        workflowService.claimTask(task);
    }

    @RequestMapping(value = "/invoices/completeTask", method = RequestMethod.POST)
    public void completeInvoiceTask(@RequestBody InvoiceTask vo) {
        Task task = billingService.findInvoiceTaskByTaskId(vo.getTaskId());
        workflowService.completeTask(task);
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
