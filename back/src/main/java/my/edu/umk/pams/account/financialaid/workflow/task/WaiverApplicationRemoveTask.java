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
import static my.edu.umk.pams.account.workflow.service.WorkflowConstants.REMOVE_COMMENT;


@Component("waiverApplication_remove_ST")
public class WaiverApplicationRemoveTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(WaiverApplicationRemoveTask.class);

    @Autowired
    private FinancialAidService financialAidService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long applicationId = (Long) execution.getVariable(WAIVER_APPLICATION_ID);
        AcWaiverApplication application = financialAidService.findWaiverApplicationById(applicationId);

        LOG.debug("removing application {}", application.getReferenceNo());
        String removeComment = (String) execution.getVariable(REMOVE_COMMENT);
        application.setRemoveComment(removeComment);

        application.getFlowdata().setState(AcFlowState.REMOVED);
        application.getFlowdata().setRemovedDate(new Timestamp(System.currentTimeMillis()));
        application.getFlowdata().setRemoverId(securityService.getCurrentUser().getId());
        financialAidService.updateWaiverApplication(application);
    }
}
