package my.edu.umk.pams.account.billing.workflow.task;

import my.edu.umk.pams.account.billing.model.AcCreditNote;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.security.service.SecurityService;
import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

import static my.edu.umk.pams.account.AccountConstants.CREDIT_NOTE_ID;
import static my.edu.umk.pams.account.workflow.service.WorkflowConstants.REMOVE_COMMENT;


@Component("creditNote_remove_ST")
public class CreditNoteRemoveTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CreditNoteRemoveTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long creditNoteId = (Long) execution.getVariable(CREDIT_NOTE_ID);
        AcCreditNote creditNote = billingService.findCreditNoteById(creditNoteId);

        LOG.debug("removing creditNote {}", creditNote.getReferenceNo());
        String removeComment = (String) execution.getVariable(REMOVE_COMMENT);
        creditNote.setRemoveComment(removeComment);

        creditNote.getFlowdata().setState(AcFlowState.REMOVED);
        creditNote.getFlowdata().setRemovedDate(new Timestamp(System.currentTimeMillis()));
        creditNote.getFlowdata().setRemoverId(securityService.getCurrentUser().getId());
        billingService.updateCreditNote(creditNote);
    }
}
