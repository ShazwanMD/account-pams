package my.edu.umk.pams.account.billing.workflow.task;

import static java.lang.System.currentTimeMillis;
import static my.edu.umk.pams.account.AccountConstants.WAIVER_FINANCE_APPLICATION_ID;
import static my.edu.umk.pams.account.core.AcFlowState.DRAFTED;
import static org.slf4j.LoggerFactory.getLogger;

import java.sql.Timestamp;

import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.account.billing.model.AcWaiverFinanceApplication;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.financialaid.workflow.task.WaiverApplicationDraftTask;
import my.edu.umk.pams.account.security.service.SecurityService;

@Component("waiverFinanceApplication_draft_ST")
public class WaiverFinanceApplicationDraftTask extends BpmnActivityBehavior implements ActivityBehavior  {
	

    private static Logger LOG = getLogger(WaiverFinanceApplicationDraftTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long applicationId = (Long) execution.getVariable(WAIVER_FINANCE_APPLICATION_ID);
        AcWaiverFinanceApplication application = billingService.findWaiverFinanceApplicationById(applicationId);

        LOG.debug("drafting application {}", application.getReferenceNo());

        application.getFlowdata().setState(DRAFTED);
        application.getFlowdata().setDraftedDate(new Timestamp(currentTimeMillis()));
        application.getFlowdata().setDrafterId(securityService.getCurrentUser().getId());
        billingService.updateWaiverFinanceApplication(application);
    }

}
