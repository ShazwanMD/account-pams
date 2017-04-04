package my.edu.umk.pams.account.billing.workflow.task;

import my.edu.umk.pams.account.billing.model.AcCreditNote;
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
import static my.edu.umk.pams.account.AccountConstants.CREDIT_NOTE_ID;
import static my.edu.umk.pams.account.core.AcFlowState.DRAFTED;
import static org.slf4j.LoggerFactory.getLogger;

@Component("creditNote_draft_ST")
public class CreditNoteDraftTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    private static Logger LOG = getLogger(CreditNoteDraftTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long creditNoteId = (Long) execution.getVariable(CREDIT_NOTE_ID);
        AcCreditNote creditNote = billingService.findCreditNoteById(creditNoteId);

        LOG.debug("drafting creditNote {}", creditNote.getReferenceNo());

        creditNote.getFlowdata().setState(DRAFTED);
        creditNote.getFlowdata().setDraftedDate(new Timestamp(currentTimeMillis()));
        creditNote.getFlowdata().setDrafterId(securityService.getCurrentUser().getId());
        billingService.updateCreditNote(creditNote);
    }
}
