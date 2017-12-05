package my.edu.umk.pams.account.billing.workflow.task;

import static my.edu.umk.pams.account.AccountConstants.WAIVER_FINANCE_APPLICATION_ID;

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
import my.edu.umk.pams.account.financialaid.workflow.task.WaiverApplicationCompleteTask;
import my.edu.umk.pams.account.security.service.SecurityService;

@Component("waiverFinanceApplication_complete_ST")
public class WaiverFinanceApplicationCompleteTask extends BpmnActivityBehavior implements ActivityBehavior {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(WaiverFinanceApplicationCompleteTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long applicationId = (Long) execution.getVariable(WAIVER_FINANCE_APPLICATION_ID);
        AcWaiverFinanceApplication application = billingService.findWaiverFinanceApplicationById(applicationId);

        LOG.debug("completing application {}", application.getReferenceNo());

        application.getFlowdata().setState(AcFlowState.COMPLETED);
        billingService.updateWaiverFinanceApplication(application);
        
        // Mintak TOLONG jangan buat apa-apa pada complete task, complete hanya 
        // untuk pergi next task. 
        // Task yang last baru send ke event (untuk buat calculation) iaitu approve task
    }
}
