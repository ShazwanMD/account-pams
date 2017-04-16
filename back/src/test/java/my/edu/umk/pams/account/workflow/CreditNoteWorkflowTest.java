package my.edu.umk.pams.account.workflow;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.*;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.workflow.service.WorkflowService;
import org.activiti.engine.task.Task;
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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author PAMS
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class CreditNoteWorkflowTest {

    private static final Logger LOG = LoggerFactory.getLogger(CreditNoteWorkflowTest.class);

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
     * NOTE: credit note has 3 layers of workflow
     * NOTE: DRAFTED > REGISTERED > VERIFIED > APPROVED
     * NOTE: before creating credit noite,
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
//        AcCreditNote creditNote = new AcCreditNoteImpl();
//        creditNote.setTotalAmount(BigDecimal.ZERO);
//        creditNote.setDescription("CREDIT NOTE FOR " + invoice.getReferenceNo());
//        creditNote.setInvoice(invoice); // previous invoice
//        String referenceNo = billingService.startCreditNoteTask(creditNote);
//        LOG.debug("credit note is created with referenceNo {}", referenceNo);
//
//        // KERANI
//        // find and pick assigned drafted credit note
//        // assuming there is one
//        List<Task> draftedTasks = billingService.findAssignedCreditNoteTasks(0, 100);
//        Task draftedTask = draftedTasks.get(0);
//        AcCreditNote draftedCreditNote = billingService.findCreditNoteByTaskId(draftedTask.getId());
//
//        // link invoice  to credite note
//        // todo: per item ??
//        // todo: do we need CreditNoteItem??
//
//        // we're done, let's submit drafted task
//        // transition to REGISTERED
//        workflowService.completeTask(draftedTask);
//
//        // PEMBANTU PEGAWAI
//        // find and pick pooled registered note
//        // assuming there is exactly one
//        List<Task> pooledRegisteredNotes = billingService.findPooledCreditNoteTasks(0, 100);
//        workflowService.assignTask(pooledRegisteredNotes.get(0));
//
//        // find and complete assigned registered note
//        // assuming there is exactly one
//        // transition to APPROVED
//        List<Task> assignedRegisteredNotes = billingService.findAssignedCreditNoteTasks(0, 100);
//        workflowService.completeTask(assignedRegisteredNotes.get(0));
//
//        // PEGAWAI
//        // find and pick pooled verified note
//        // assuming there is exactly one
//        List<Task> pooledVerifiedNotes = billingService.findPooledCreditNoteTasks(0, 100);
//        workflowService.assignTask(pooledVerifiedNotes.get(0));
//
//        // find and complete assigned verified note
//        // assuming there is exactly one
//        // transition to APPROVED (COMPLETED)
//        List<Task> assignedVerifiedNotes = billingService.findAssignedCreditNoteTasks(0, 100);
//        workflowService.completeTask(assignedVerifiedNotes.get(0));
//    }

    @Test
    @Rollback
    public String createInvoice() {
        // find account
        AcStudent student = identityService.findStudentByMatricNo("A17P001");
        AcAccount account = accountService.findAccountByActor(student);
        AcAcademicSession academicSession = accountService.findCurrentAcademicSession();
        // issue an invoice then start workflow
        // transition to DRAFTED
        AcInvoice invoice = new AcInvoiceImpl();
        invoice.setTotalAmount(BigDecimal.valueOf(100));
        invoice.setDescription("INVOICE");
        invoice.setIssuedDate(new Date());
        invoice.setPaid(false);
        invoice.setAccount(account);
        invoice.setSession(academicSession);
        String referenceNo = billingService.startInvoiceTask(invoice);
        LOG.debug("invoice is created with referenceNo {}", referenceNo);

        // find and pick assigned drafted invoice
        // assuming there is one
        List<Task> draftedTasks = billingService.findAssignedInvoiceTasks(0, 100);
        Task draftedTask = draftedTasks.get(0);
        AcInvoice draftedInvoice = billingService.findInvoiceByTaskId(draftedTask.getId());

        // add items to invoice
        AcInvoiceItem item1 = new AcInvoiceItemImpl();
        item1.setChargeCode(accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79321"));
        item1.setDescription("YURAN PENDAFTARAN");
        item1.setAmount(BigDecimal.valueOf(2000.00));
        billingService.addInvoiceItem(draftedInvoice, item1);

        AcInvoiceItem item2 = new AcInvoiceItemImpl();
        item2.setChargeCode(accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79331"));
        item2.setDescription("YURAN KAD MATRIK");
        item2.setAmount(BigDecimal.valueOf(20.00));
        billingService.addInvoiceItem(draftedInvoice, item2);

        // we're done, let's submit drafted task
        // transition to REGISTERED
        workflowService.completeTask(draftedTask);

        // PEMBANTU PEGAWAI
        // find and pick pooled registered invoice
        // assuming there is exactly one
        List<Task> pooledRegisteredInvoices = billingService.findPooledInvoiceTasks(0, 100);
        workflowService.assignTask(pooledRegisteredInvoices.get(0));

        // find and complete assigned registered invoice
        // assuming there is exactly one
        // transition to VERIFIED
        List<Task> assignedRegisteredInvoices = billingService.findAssignedInvoiceTasks(0, 100);
        workflowService.completeTask(assignedRegisteredInvoices.get(0));

        // PEGAWAI
        // find and pick pooled verified invoice
        // assuming there is exactly one
        List<Task> pooledVerifiedInvoices = billingService.findPooledInvoiceTasks(0, 100);
        workflowService.assignTask(pooledVerifiedInvoices.get(0));

        // find and complete assigned verified invoice
        // assuming there is exactly one
        // transition to APPROVED (COMPLETED)
        List<Task> assignedVerifiedInvoices = billingService.findAssignedInvoiceTasks(0, 100);
        workflowService.completeTask(assignedVerifiedInvoices.get(0)); // TO APPROVE
        return referenceNo;
    }
}
