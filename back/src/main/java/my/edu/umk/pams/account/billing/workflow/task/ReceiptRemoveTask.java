package my.edu.umk.pams.account.billing.workflow.task;

import my.edu.umk.pams.account.AccountConstants;
import my.edu.umk.pams.account.billing.model.AcReceipt;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.security.service.SecurityService;
import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;

import java.sql.Timestamp;

import static java.lang.System.currentTimeMillis;
import static my.edu.umk.pams.account.core.AcFlowState.REMOVED;
import static my.edu.umk.pams.account.workflow.service.WorkflowConstants.REMOVE_COMMENT;
import static org.slf4j.LoggerFactory.getLogger;

@Component("receipt_remove_ST")
@Rollback(true)
public class ReceiptRemoveTask extends BpmnActivityBehavior implements ActivityBehavior {

    private static final Logger LOG = getLogger(ReceiptRemoveTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long receiptId = (Long) execution.getVariable(AccountConstants.RECEIPT_ID);
        AcReceipt receipt = billingService.findReceiptById(receiptId);

        LOG.debug("removing invoice receipt {}", receipt.getReferenceNo());

        // serialize remove comment
        String removeComment = (String) execution.getVariable(REMOVE_COMMENT);

        // update flow state
        receipt.getFlowdata().setState(REMOVED);
        receipt.getFlowdata().setRemovedDate(new Timestamp(currentTimeMillis()));
        receipt.getFlowdata().setRemoverId(securityService.getCurrentUser().getId());
        billingService.updateReceipt(receipt);
        
        
    }
}
