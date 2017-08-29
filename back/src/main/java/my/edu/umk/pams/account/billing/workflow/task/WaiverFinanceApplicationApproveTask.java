package my.edu.umk.pams.account.billing.workflow.task;

import static my.edu.umk.pams.account.AccountConstants.WAIVER_FINANCE_APPLICATION_ID;

import java.sql.Timestamp;

import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.account.account.model.AcAccountWaiver;
import my.edu.umk.pams.account.account.model.AcAccountWaiverImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcWaiverFinanceApplication;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.security.service.SecurityService;

@Component("waiverFinanceApplication_approve_ST")
public class WaiverFinanceApplicationApproveTask extends BpmnActivityBehavior implements ActivityBehavior{

	private static final Logger LOG = LoggerFactory.getLogger(WaiverFinanceApplicationApproveTask.class);
    
    @Autowired
    private BillingService billingService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long applicationId = (Long) execution.getVariable(WAIVER_FINANCE_APPLICATION_ID);
        AcWaiverFinanceApplication application = billingService.findWaiverFinanceApplicationById(applicationId);
        LOG.debug("approving application {}", application.getReferenceNo());

        // update flow state
        application.getFlowdata().setState(AcFlowState.APPROVED);
        application.getFlowdata().setApprovedDate(new Timestamp(System.currentTimeMillis()));
        application.getFlowdata().setApproverId(securityService.getCurrentUser().getId());
        billingService.updateWaiverFinanceApplication(application);

        // setup account waiver
        AcAccountWaiver waiver = new AcAccountWaiverImpl();
        waiver.setSourceNo(application.getReferenceNo());
        waiver.setAmount(application.getWaivedAmount());
        waiver.setSession(application.getSession());
        waiver.setAccount(application.getAccount());

        // save waiver
        accountService.addAccountWaiver(application.getAccount(), application.getSession(), waiver);
    }
}
