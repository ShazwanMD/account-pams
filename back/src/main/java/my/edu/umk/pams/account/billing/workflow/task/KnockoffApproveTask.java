package my.edu.umk.pams.account.billing.workflow.task;

import static my.edu.umk.pams.account.AccountConstants.KNOCKOFF_ID;

import java.sql.Timestamp;

import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.account.billing.event.KnockoffApprovedEvent;
import my.edu.umk.pams.account.billing.event.WaiverApprovedEvent;
import my.edu.umk.pams.account.billing.model.AcKnockoff;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.security.service.SecurityService;

@Component("knockoff_approve_ST")
public class KnockoffApproveTask extends BpmnActivityBehavior implements ActivityBehavior {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(KnockoffVerifyTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;
    
	@Autowired
	private ApplicationContext applicationContext;

    public void execute(ActivityExecution execution) throws Exception {
    	Long knockoffId = (Long) execution.getVariable(KNOCKOFF_ID);
        AcKnockoff knockoff = billingService.findKnockoffById(knockoffId);

        LOG.debug("verifying knockoff {}", knockoff.getReferenceNo());

        knockoff.getFlowdata().setState(AcFlowState.APPROVED);
        knockoff.getFlowdata().setVerifiedDate(new Timestamp(System.currentTimeMillis()));
        knockoff.getFlowdata().setVerifierId(securityService.getCurrentUser().getId());
        billingService.updateKnockoff(knockoff);
        //billingService.post(knockoff);
        applicationContext.publishEvent(new KnockoffApprovedEvent(knockoff));
        
        // Approve Task hok ni hantar ko event nak buat calculation
        // Tak perlu tambah method post sebab dalam event tu sendiri dah ada method untuk post terus
        // pada account transaction
    }

}
