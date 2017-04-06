package my.edu.umk.pams.account.financialaid.workflow.task;

import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.security.service.SecurityService;
import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

import static java.lang.System.currentTimeMillis;
import static my.edu.umk.pams.account.AccountConstants.WAIVER_APPLICATION_ID;
import static my.edu.umk.pams.account.core.AcFlowState.DRAFTED;
import static org.slf4j.LoggerFactory.getLogger;

@Component("waiverApplication_draft_ST")
public class WaiverApplicationDraftTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    private static Logger LOG = getLogger(WaiverApplicationDraftTask.class);

    @Autowired
    private FinancialAidService financialAidService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long applicationId = (Long) execution.getVariable(WAIVER_APPLICATION_ID);
        AcWaiverApplication application = financialAidService.findWaiverApplicationById(applicationId);

        LOG.debug("drafting application {}", application.getReferenceNo());

        application.getFlowdata().setState(DRAFTED);
        application.getFlowdata().setDraftedDate(new Timestamp(currentTimeMillis()));
        application.getFlowdata().setDrafterId(securityService.getCurrentUser().getId());
        financialAidService.updateWaiverApplication(application);
    }
}
