package my.edu.umk.pams.account.financialaid.workflow.task;

import my.edu.umk.pams.account.account.model.AcAccountWaiver;
import my.edu.umk.pams.account.account.model.AcAccountWaiverImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.security.service.SecurityService;
import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

import static my.edu.umk.pams.account.AccountConstants.WAIVER_APPLICATION_ID;


@Component("waiverApplication_approve_ST")
public class WaiverApplicationApproveTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    private static final Logger LOG = LoggerFactory.getLogger(WaiverApplicationApproveTask.class);

    @Autowired
    private FinancialAidService financialAidService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long applicationId = (Long) execution.getVariable(WAIVER_APPLICATION_ID);
        AcWaiverApplication application = financialAidService.findWaiverApplicationById(applicationId);
        LOG.debug("approving application {}", application.getReferenceNo());

        // update flow state
        application.getFlowdata().setState(AcFlowState.APPROVED);
        application.getFlowdata().setApprovedDate(new Timestamp(System.currentTimeMillis()));
        application.getFlowdata().setApproverId(securityService.getCurrentUser().getId());
        financialAidService.updateWaiverApplication(application);

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
