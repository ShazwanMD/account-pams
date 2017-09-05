package my.edu.umk.pams.account.billing.workflow.task;

import static my.edu.umk.pams.account.AccountConstants.REFUND_ID;
import static my.edu.umk.pams.account.workflow.service.WorkflowConstants.REMOVE_COMMENT;

import java.sql.Timestamp;

import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.account.billing.model.AcKnockoff;
import my.edu.umk.pams.account.billing.model.AcRefundPayment;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.security.service.SecurityService;

@Component("refund_remove_ST")
public class RefundPaymentRemoveTask extends BpmnActivityBehavior implements ActivityBehavior {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(RefundPaymentVerifyTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
    	Long refundId = (Long) execution.getVariable(REFUND_ID);
        AcRefundPayment refund = billingService.findRefundPaymentById(refundId);
        
        LOG.debug("verifying refund {}", refund.getReferenceNo());
        String removeComment = (String) execution.getVariable(REMOVE_COMMENT);
        
        refund.getFlowdata().setState(AcFlowState.REMOVED);
        refund.getFlowdata().setVerifiedDate(new Timestamp(System.currentTimeMillis()));
        refund.getFlowdata().setVerifierId(securityService.getCurrentUser().getId());
        billingService.updateRefundPayment(refund);

    }
}
