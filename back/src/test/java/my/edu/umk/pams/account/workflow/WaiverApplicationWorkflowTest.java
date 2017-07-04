package my.edu.umk.pams.account.workflow;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplicationImpl;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
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
import java.util.List;

/**
 * @author PAMS
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class WaiverApplicationWorkflowTest {

    private static final Logger LOG = LoggerFactory.getLogger(WaiverApplicationWorkflowTest.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountService accountService;

    @Autowired
    private FinancialAidService financialAidService;

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
     * NOTE: waiver application has 3 layers of workflow
     * NOTE: DRAFTED > REGISTERED > VERIFIED > APPROVED
     */
    @Test
    @Rollback
    public void testWorkflow() {
        // find account
        AcStudent student = identityService.findStudentByMatricNo("A17P001");
        AcAccount account = accountService.findAccountByActor(student);
        AcAcademicSession academicSession = accountService.findCurrentAcademicSession();

        // create waiver application then start workflow
        // transition to DRAFTED
        AcWaiverApplication application = new AcWaiverApplicationImpl();
        application.setDescription("WAIVER APPLICATION");
        application.setAccount(account);
        application.setSession(academicSession);
        application.setBalance(BigDecimal.ZERO);
        application.setEffectiveBalance(BigDecimal.ZERO);
        application.setGracedAmount(BigDecimal.ZERO);
        application.setWaivedAmount(BigDecimal.ZERO);
        String referenceNo = financialAidService.startWaiverApplicationTask(application);
        LOG.debug("waiver application is started with referenceNo {}", referenceNo);

        // find and pick assigned drafted invoice
        // assuming there is one
        List<Task> draftedTasks = financialAidService.findAssignedWaiverApplicationTasks(0, 100);
        Assert.notEmpty(draftedTasks, "Tasks should not be empty");
        Task draftedTask = draftedTasks.get(0);
        AcWaiverApplication draftedApplication = financialAidService.findWaiverApplicationByTaskId(draftedTask.getId());

        // fill in the rest of the information
        draftedApplication.setBalance(accountService.sumBalanceAmount(account));
        draftedApplication.setEffectiveBalance(accountService.sumBalanceAmount(account)); // TODO:
        draftedApplication.setGracedAmount(BigDecimal.valueOf(2000));
        draftedApplication.setWaivedAmount(BigDecimal.valueOf(200));
        draftedApplication.setReason("MASALAH KEWANGAN");

        // we're done, let's submit drafted task
        // transition to REGISTERED
        workflowService.completeTask(draftedTask);

        // PEGAWAI CPS/MGSEB
        // find and pick pooled registered invoice
        // assuming there is exactly one
        List<Task> pooledRegisteredInvoices = financialAidService.findPooledWaiverApplicationTasks(0, 100);
        Assert.notEmpty(pooledRegisteredInvoices, "Tasks should not be empty");
        workflowService.assignTask(pooledRegisteredInvoices.get(0));

        // find and complete assigned registered invoice
        // assuming there is exactly one
        // transition to VERIFIED
        List<Task> assignedRegisteredInvoices = financialAidService.findAssignedWaiverApplicationTasks(0, 100);
        Assert.notEmpty(assignedRegisteredInvoices, "Tasks should not be empty");
        workflowService.completeTask(assignedRegisteredInvoices.get(0));

        // PENDAFTAR
        // find and pick pooled verified invoice
        // assuming there is exactly one
        List<Task> pooledVerifiedInvoices = financialAidService.findPooledWaiverApplicationTasks(0, 100);
        Assert.notEmpty(pooledVerifiedInvoices, "Tasks should not be empty");
        workflowService.assignTask(pooledVerifiedInvoices.get(0));

        // find and complete assigned verified invoice
        // assuming there is exactly one
        // transition to APPROVED (COMPLETED)
        List<Task> assignedVerifiedInvoices = financialAidService.findAssignedWaiverApplicationTasks(0, 100);
        Assert.notEmpty(assignedVerifiedInvoices, "Tasks should not be empty");
        workflowService.completeTask(assignedVerifiedInvoices.get(0)); // TO APPROVE
    }
}
