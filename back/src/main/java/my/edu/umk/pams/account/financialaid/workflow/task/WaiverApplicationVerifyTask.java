package my.edu.umk.pams.account.financialaid.workflow.task;

import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.security.service.SecurityService;
import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

import static my.edu.umk.pams.account.AccountConstants.WAIVER_APPLICATION_ID;

@Component("waiverApplication_verify_ST")
public class WaiverApplicationVerifyTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(WaiverApplicationVerifyTask.class);

    @Autowired
    private FinancialAidService financialAidService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long applicationId = (Long) execution.getVariable(WAIVER_APPLICATION_ID);
        AcWaiverApplication application = financialAidService.findWaiverApplicationById(applicationId);

        LOG.debug("verifying application {}", application.getReferenceNo());

        application.getFlowdata().setState(AcFlowState.VERIFIED);
        application.getFlowdata().setVerifiedDate(new Timestamp(System.currentTimeMillis()));
        application.getFlowdata().setVerifierId(securityService.getCurrentUser().getId());
        financialAidService.updateWaiverApplication(application);
    }
}
