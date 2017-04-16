package my.edu.umk.pams.account.workflow;

import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.workflow.service.WorkflowService;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author PAMS
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class ReceiptWorkflowTest {

    private static final Logger LOG = LoggerFactory.getLogger(ReceiptWorkflowTest.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BillingService billingService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private IdentityService identityService;

    @Before
    public void before() {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("bursary", "abc123");
        Authentication authed = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authed);
    }

    /**
     * NOTE: receipt has 2 layers of workflow
     * NOTE: DRAFTED > REGISTERED > APPROVED
     * NOTE: before creating bursary receipt,
     * NOTE: invoice must exists
     */
    @Test
    @Rollback
    public void testWorkflow() {}
//        // create invoice first
//        // and retrieve the invoice
//        String invoiceReferenceNo = createInvoice();
//        AcInvoice invoice = billingService.findInvoiceByReferenceNo(invoiceReferenceNo);
//
//        // find account
//        AcStudent student = identityService.findStudentByMatricNo("A17P001");
//        AcAccount account = accountService.findAccountByActor(student);
//        AcAcademicSession academicSession = accountService.findCurrentAcademicSession();
//
//        // issue an invoice then start workflow
//        // transition to DRAFTED
//        AcBursaryReceipt receipt = new AcBursaryReceiptImpl();
//        receipt.setTotalAmount(BigDecimal.ZERO);
//        receipt.setDescription("RECEIPT");
//        receipt.setPaymentMethod(AcPaymentMethod.CASH);
//        receipt.setTotalApplied(BigDecimal.ZERO);
//        receipt.setTotalReceived(BigDecimal.ZERO);
//        receipt.setAccount(account);
//        String referenceNo = billingService.startReceiptTask(receipt);
//        LOG.debug("receipt is created with referenceNo {}", referenceNo);
//
//        // KAUNTER
//        // find and pick assigned drafted receipt
//        // assuming there is one
//        List<Task> draftedTasks = billingService.findAssignedReceiptTasks(0, 100);
//        Assert.notEmpty(draftedTasks, "Tasks should not be empty");
//        Task draftedTask = draftedTasks.get(0);
//        AcReceipt draftedReceipt = billingService.findReceiptByTaskId(draftedTask.getId());
//
//        // add invoice  to receipt
//        AcReceiptItem item1 = new AcReceiptItemImpl();
//        item1.setChargeCode(accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79321"));
//        item1.setDescription("YURAN PENDAFTARAN");
//        item1.setTotalAmount(BigDecimal.valueOf(2000.00)); // ??
//        item1.setAdjustedAmount(BigDecimal.valueOf(2000.00));// ??
//        item1.setAppliedAmount(BigDecimal.valueOf(2000.00));// ??
//        item1.setDueAmount(BigDecimal.valueOf(2000.00));// ??
//        item1.setUnit(1);
//        item1.setInvoice(invoice);  // previous invoice
//        billingService.addReceiptItem(draftedReceipt, item1);
//
//        // we're done, let's submit drafted task
//        // transition to REGISTERED
//        workflowService.completeTask(draftedTask);
//
//        // PEGAWAI
//        // find and pick pooled registered receipt
//        // assuming there is exactly one
//        List<Task> pooledRegisteredReceipts = billingService.findPooledReceiptTasks(0, 100);
//        Assert.notEmpty(pooledRegisteredReceipts, "Tasks should not be empty");
//        workflowService.assignTask(pooledRegisteredReceipts.get(0));
//
//        // find and complete assigned registered receipt
//        // assuming there is exactly one
//        // transition to APPROVED (COMPLETED)
//        List<Task> assignedRegisteredReceipts = billingService.findAssignedReceiptTasks(0, 100);
//        Assert.notEmpty(assignedRegisteredReceipts, "Tasks should not be empty");
//        workflowService.completeTask(assignedRegisteredReceipts.get(0));
//    }

//    @Test
//    @Rollback
//    public String createInvoice() {
//        // find account
//        AcStudent student = identityService.findStudentByMatricNo("A17P001");
//        AcAccount account = accountService.findAccountByActor(student);
//        AcAcademicSession academicSession = accountService.findCurrentAcademicSession();
//        // issue an invoice then start workflow
//        // transition to DRAFTED
//        AcInvoice invoice = new AcInvoiceImpl();
//        invoice.setTotalAmount(BigDecimal.valueOf(100));
//        invoice.setDescription("INVOICE");
//        invoice.setIssuedDate(new Date());
//        invoice.setPaid(false);
//        invoice.setAccount(account);
//        invoice.setSession(academicSession);
//        String referenceNo = billingService.startInvoiceTask(invoice);
//        LOG.debug("invoice is created with referenceNo {}", referenceNo);
//
//        // find and pick assigned drafted invoice
//        // assuming there is one
//        List<Task> draftedTasks = billingService.findAssignedInvoiceTasks(0, 100);
//        Task draftedTask = draftedTasks.get(0);
//        AcInvoice draftedInvoice = billingService.findInvoiceByTaskId(draftedTask.getId());
//
//        // add items to invoice
//        AcInvoiceItem item1 = new AcInvoiceItemImpl();
//        item1.setChargeCode(accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79321"));
//        item1.setDescription("YURAN PENDAFTARAN");
//        item1.setAmount(BigDecimal.valueOf(2000.00));
//        billingService.addInvoiceItem(draftedInvoice, item1);
//
//        AcInvoiceItem item2 = new AcInvoiceItemImpl();
//        item2.setChargeCode(accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79331"));
//        item2.setDescription("YURAN KAD MATRIK");
//        item2.setAmount(BigDecimal.valueOf(20.00));
//        billingService.addInvoiceItem(draftedInvoice, item2);
//
//        // we're done, let's submit drafted task
//        // transition to REGISTERED
//        workflowService.completeTask(draftedTask);
//
//        // PEMBANTU PEGAWAI
//        // find and pick pooled registered invoice
//        // assuming there is exactly one
//        List<Task> pooledRegisteredInvoices = billingService.findPooledInvoiceTasks(0, 100);
//        workflowService.assignTask(pooledRegisteredInvoices.get(0));
//
//        // find and complete assigned registered invoice
//        // assuming there is exactly one
//        // transition to VERIFIED
//        List<Task> assignedRegisteredInvoices = billingService.findAssignedInvoiceTasks(0, 100);
//        workflowService.completeTask(assignedRegisteredInvoices.get(0));
//
//        // PEGAWAI
//        // find and pick pooled verified invoice
//        // assuming there is exactly one
//        List<Task> pooledVerifiedInvoices = billingService.findPooledInvoiceTasks(0, 100);
//        workflowService.assignTask(pooledVerifiedInvoices.get(0));
//
//        // find and complete assigned verified invoice
//        // assuming there is exactly one
//        // transition to APPROVED (COMPLETED)
//        List<Task> assignedVerifiedInvoices = billingService.findAssignedInvoiceTasks(0, 100);
//        workflowService.completeTask(assignedVerifiedInvoices.get(0)); // TO APPROVE
//        return referenceNo;
//    }

}
