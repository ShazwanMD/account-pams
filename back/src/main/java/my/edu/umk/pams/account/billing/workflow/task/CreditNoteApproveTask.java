package my.edu.umk.pams.account.billing.workflow.task;

import my.edu.umk.pams.account.billing.model.AcCreditNote;
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

import static my.edu.umk.pams.account.AccountConstants.CREDIT_NOTE_ID;

@Component("creditNote_approve_ST")
public class CreditNoteApproveTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    private static final Logger LOG = LoggerFactory.getLogger(CreditNoteApproveTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long creditNoteId = (Long) execution.getVariable(CREDIT_NOTE_ID);
        AcCreditNote creditNote = billingService.findCreditNoteById(creditNoteId);
        LOG.debug("approving creditNote {}", creditNote.getReferenceNo());

        // update flow state
        creditNote.getFlowdata().setState(AcFlowState.APPROVED);
        creditNote.getFlowdata().setApprovedDate(new Timestamp(System.currentTimeMillis()));
        creditNote.getFlowdata().setApproverId(securityService.getCurrentUser().getId());
        billingService.updateCreditNote(creditNote);
    }
}
