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
import org.springframework.transaction.annotation.Transactional;

import static my.edu.umk.pams.account.AccountConstants.CREDIT_NOTE_ID;

@Component("creditNote_complete_ST")
@Transactional
public class CreditNoteCompleteTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CreditNoteCompleteTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long creditNoteId = (Long) execution.getVariable(CREDIT_NOTE_ID);
        AcCreditNote creditNote = billingService.findCreditNoteById(creditNoteId);

        LOG.debug("completing creditNote {}", creditNote.getReferenceNo());

        creditNote.getFlowdata().setState(AcFlowState.COMPLETED);
        billingService.updateCreditNote(creditNote);

        // Mintak TOLONG jangan buat apa-apa pada complete task, complete hanya 
        // untuk pergi next task. 
        // Task yang last baru send ke event (untuk buat calculation) iaitu approve task
    }
}
