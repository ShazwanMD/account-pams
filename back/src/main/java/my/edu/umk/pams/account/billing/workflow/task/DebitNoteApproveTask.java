package my.edu.umk.pams.account.billing.workflow.task;

import my.edu.umk.pams.account.billing.model.AcDebitNote;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.security.service.SecurityService;
import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

import static my.edu.umk.pams.account.AccountConstants.DEBIT_NOTE_ID;

@Component("debitNote_approve_ST")
public class DebitNoteApproveTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    private static final Logger LOG = LoggerFactory.getLogger(DebitNoteApproveTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long debitNoteId = (Long) execution.getVariable(DEBIT_NOTE_ID);
        AcDebitNote debitNote = billingService.findDebitNoteById(debitNoteId);
        LOG.debug("approving debitNote {}", debitNote.getReferenceNo());

        // update flow state
        debitNote.getFlowdata().setState(AcFlowState.APPROVED);
        debitNote.getFlowdata().setApprovedDate(new Timestamp(System.currentTimeMillis()));
        debitNote.getFlowdata().setApproverId(securityService.getCurrentUser().getId());
        billingService.updateDebitNote(debitNote);

        billingService.post(debitNote);

    }
}
