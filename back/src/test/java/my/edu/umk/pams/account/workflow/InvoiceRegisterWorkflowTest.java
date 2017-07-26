package my.edu.umk.pams.account.workflow;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceImpl;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcInvoiceItemImpl;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author PAMS
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class InvoiceRegisterWorkflowTest {

    private static final Logger LOG = LoggerFactory.getLogger(InvoiceRegisterWorkflowTest.class);

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
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("root", "abc123");
        Authentication authed = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authed);
    }

    /**
     * NOTE: invoice has 3 layers of workflow
     * NOTE: this only test up to DRAFTED
     */
    @Test
    @Rollback(false)
    public void testDraftWorkflow() {
        // find account
        AcStudent student = identityService.findStudentByMatricNo("A17P001");
        AcAccount account = accountService.findAccountByActor(student);
        AcAcademicSession academicSession = accountService.findCurrentAcademicSession();

        for(int i = 0; i < 5; i++){
            // issue an invoice then start workflow
            // transition to DRAFTED
            AcInvoice invoice = new AcInvoiceImpl();
            invoice.setTotalAmount(BigDecimal.valueOf(100));
            invoice.setBalanceAmount(BigDecimal.valueOf(100));
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
            Assert.notEmpty(draftedTasks, "Tasks should not be empty");
            Task draftedTask = draftedTasks.get(0);
            AcInvoice draftedInvoice = billingService.findInvoiceByTaskId(draftedTask.getId());

            // add items to invoice
            AcChargeCode chargeCode1 = accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79321");
            AcInvoiceItem item1 = new AcInvoiceItemImpl();
            item1.setChargeCode(chargeCode1);
            item1.setTaxCode(chargeCode1.getTaxCode());
            item1.setDescription("YURAN PENDAFTARAN");
            item1.setAmount(BigDecimal.valueOf(2000.00));
            billingService.addInvoiceItem(draftedInvoice, item1);

            AcChargeCode chargeCode2 = accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79331");
            AcInvoiceItem item2 = new AcInvoiceItemImpl();
            item2.setChargeCode(chargeCode2);
            item2.setTaxCode(chargeCode2.getTaxCode());
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
            Assert.notEmpty(pooledRegisteredInvoices, "Tasks should not be empty");
            workflowService.assignTask(pooledRegisteredInvoices.get(0));

        }
    }
}
