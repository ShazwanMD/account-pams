package my.edu.umk.pams.account.billing.workflow.task;

import static my.edu.umk.pams.account.AccountConstants.WAIVER_FINANCE_APPLICATION_ID;

import java.sql.Timestamp;

import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.account.billing.model.AcWaiverFinanceApplication;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.financialaid.workflow.task.WaiverApplicationVerifyTask;
import my.edu.umk.pams.account.security.service.SecurityService;

@Component("waiverFinanceApplication_verify_ST")
public class WaiverFinanceApplicationVerifyTask extends BpmnActivityBehavior implements ActivityBehavior {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(WaiverFinanceApplicationVerifyTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long applicationId = (Long) execution.getVariable(WAIVER_FINANCE_APPLICATION_ID);
        AcWaiverFinanceApplication application = billingService.findWaiverFinanceApplicationById(applicationId);

        LOG.debug("verifying application {}", application.getReferenceNo());

        application.getFlowdata().setState(AcFlowState.VERIFIED);
        application.getFlowdata().setVerifiedDate(new Timestamp(System.currentTimeMillis()));
        application.getFlowdata().setVerifierId(securityService.getCurrentUser().getId());
        billingService.updateWaiverFinanceApplication(application);
    }
}
