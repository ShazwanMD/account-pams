package my.edu.umk.pams.account.billing.workflow.task;

import my.edu.umk.pams.account.billing.model.AcDebitNote;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.security.service.SecurityService;
import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

import static java.lang.System.currentTimeMillis;
import static my.edu.umk.pams.account.AccountConstants.DEBIT_NOTE_ID;
import static my.edu.umk.pams.account.core.AcFlowState.DRAFTED;
import static org.slf4j.LoggerFactory.getLogger;

@Component("debitNote_draft_ST")
public class DebitNoteDraftTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    private static Logger LOG = getLogger(DebitNoteDraftTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long debitNoteId = (Long) execution.getVariable(DEBIT_NOTE_ID);
        AcDebitNote debitNote = billingService.findDebitNoteById(debitNoteId);

        LOG.debug("drafting debitNote {}", debitNote.getReferenceNo());

        debitNote.getFlowdata().setState(DRAFTED);
        debitNote.getFlowdata().setDraftedDate(new Timestamp(currentTimeMillis()));
        debitNote.getFlowdata().setDrafterId(securityService.getCurrentUser().getId());
        billingService.updateDebitNote(debitNote);
    }
}
