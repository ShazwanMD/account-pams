package my.edu.umk.pams.account.billing.workflow.task;

import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcRefundPayment;
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

import static my.edu.umk.pams.account.AccountConstants.INVOICE_ID;
import static my.edu.umk.pams.account.AccountConstants.REFUND_ID;

@Component("refund_register_ST")
public class RefundRegisterTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    private static final Logger LOG = LoggerFactory.getLogger(RefundRegisterTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
    	Long refundId = (Long) execution.getVariable(REFUND_ID);
        AcRefundPayment refund = billingService.findRefundPaymentById(refundId);

        LOG.debug("verifying refund {}", refund.getReferenceNo());

        refund.getFlowdata().setState(AcFlowState.REGISTERED);
        billingService.updateRefundPayment(refund);
    }
}
