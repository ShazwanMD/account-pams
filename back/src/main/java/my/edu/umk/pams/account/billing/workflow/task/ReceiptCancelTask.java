package my.edu.umk.pams.account.billing.workflow.task;

import my.edu.umk.pams.account.billing.model.AcReceipt;
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
import static my.edu.umk.pams.account.AccountConstants.RECEIPT_ID;
import static my.edu.umk.pams.account.core.AcFlowState.CANCELLED;
import static org.slf4j.LoggerFactory.getLogger;

@Component("receipt_cancel_ST")
public class ReceiptCancelTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    private static final Logger LOG = getLogger(ReceiptCancelTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long receiptId = (Long) execution.getVariable(RECEIPT_ID);
        AcReceipt receipt = billingService.findReceiptById(receiptId);

        LOG.debug("cancelling invoice receipt {}", receipt.getReferenceNo());

        receipt.getFlowdata().setState(CANCELLED);
        receipt.getFlowdata().setCancelledDate(new Timestamp(currentTimeMillis()));
        receipt.getFlowdata().setCancelerId(securityService.getCurrentUser().getId());
        billingService.updateReceipt(receipt);
    }
}
