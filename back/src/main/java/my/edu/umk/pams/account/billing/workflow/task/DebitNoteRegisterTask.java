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

@Component("debitNote_register_ST")
public class DebitNoteRegisterTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    private static final Logger LOG = LoggerFactory.getLogger(DebitNoteRegisterTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long debitNoteId = (Long) execution.getVariable(DEBIT_NOTE_ID);
        AcDebitNote debitNote = billingService.findDebitNoteById(debitNoteId);

        LOG.debug("registering debitNote {}", debitNote.getReferenceNo());

        debitNote.getFlowdata().setState(AcFlowState.REGISTERED);
        debitNote.getFlowdata().setRegisteredDate(new Timestamp(System.currentTimeMillis()));
        debitNote.getFlowdata().setRegistererId(securityService.getCurrentUser().getId());
        billingService.updateDebitNote(debitNote);
    }
}
