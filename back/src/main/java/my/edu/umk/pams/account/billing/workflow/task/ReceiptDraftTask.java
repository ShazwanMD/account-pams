package my.edu.umk.pams.account.billing.workflow.task;

import my.edu.umk.pams.account.billing.model.AcReceipt;
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

import static my.edu.umk.pams.account.AccountConstants.RECEIPT_ID;

@Component("receipt_draft_ST")
public class ReceiptDraftTask extends BpmnActivityBehavior implements ActivityBehavior {

    private static final Logger LOG = LoggerFactory.getLogger(ReceiptDraftTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        // retrieve receipt from variable
        Long receiptId = (Long) execution.getVariable(RECEIPT_ID);
        AcReceipt receipt = billingService.findReceiptById(receiptId);

        LOG.debug("drafting receipt {}", receipt.getReferenceNo());

        // update flow state
        receipt.getFlowdata().setState(AcFlowState.DRAFTED);
        receipt.getFlowdata().setDraftedDate(new Timestamp(System.currentTimeMillis()));
        receipt.getFlowdata().setDrafterId(securityService.getCurrentUser().getId());
        billingService.updateReceipt(receipt);
    }
}
