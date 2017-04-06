package my.edu.umk.pams.account.financialaid.workflow.task;

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

@Component("waiverApplication_register_ST")
public class WaiverApplicationRegisterTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    private static final Logger LOG = LoggerFactory.getLogger(WaiverApplicationRegisterTask.class);

    @Autowired
    private FinancialAidService financialAidService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long applicationId = (Long) execution.getVariable(WAIVER_APPLICATION_ID);
        AcWaiverApplication application = financialAidService.findWaiverApplicationById(applicationId);

        LOG.debug("registering application {}", application.getReferenceNo());

        application.getFlowdata().setState(AcFlowState.REGISTERED);
        application.getFlowdata().setRegisteredDate(new Timestamp(System.currentTimeMillis()));
        application.getFlowdata().setRegistererId(securityService.getCurrentUser().getId());
        financialAidService.updateWaiverApplication(application);
    }
}
