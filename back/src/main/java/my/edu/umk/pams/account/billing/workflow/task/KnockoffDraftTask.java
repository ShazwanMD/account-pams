package my.edu.umk.pams.account.billing.workflow.task;

import static java.lang.System.currentTimeMillis;
import static my.edu.umk.pams.account.AccountConstants.INVOICE_ID;
import static my.edu.umk.pams.account.core.AcFlowState.DRAFTED;
import static org.slf4j.LoggerFactory.getLogger;

import java.sql.Timestamp;

import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.account.billing.model.AcKnockoff;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.security.service.SecurityService;
import static my.edu.umk.pams.account.AccountConstants.KNOCKOFF_ID;

@Component("knockoff_draft_ST")
public class KnockoffDraftTask extends BpmnActivityBehavior implements ActivityBehavior {

	private static Logger LOG = getLogger(KnockoffDraftTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long knockoffId = (Long) execution.getVariable(KNOCKOFF_ID);
        AcKnockoff knockoff = billingService.findKnockoffById(knockoffId);

        LOG.debug("drafting knockoff {}", knockoff.getReferenceNo());

        // append charge to invoice
        // appending will increase total/balance amount
        // appending will add invoice item

        knockoff.getFlowdata().setState(DRAFTED);
        knockoff.getFlowdata().setDraftedDate(new Timestamp(currentTimeMillis()));
        knockoff.getFlowdata().setDrafterId(securityService.getCurrentUser().getId());
        billingService.updateKnockoff(knockoff);
    }
}
