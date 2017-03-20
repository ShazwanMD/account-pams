package my.edu.umk.pams.account.billing.workflow.task;

import my.edu.umk.pams.account.billing.model.AcReceipt;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.account.system.service.SystemService;
import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

import static java.lang.System.currentTimeMillis;
import static my.edu.umk.pams.account.AccountConstants.RECEIPT_ID;
import static my.edu.umk.pams.account.AccountConstants.RECEIPT_RECEIPT_NO;
import static my.edu.umk.pams.account.core.AcFlowState.REGISTERED;
import static my.edu.umk.pams.account.workflow.service.WorkflowConstants.QUERY_DECISION;
import static my.edu.umk.pams.account.workflow.service.WorkflowConstants.REMOVE_DECISION;
import static org.slf4j.LoggerFactory.getLogger;

@Component("receipt_register_ST")
@Transactional
public class ReceiptRegisterTask extends BpmnActivityBehavior implements ActivityBehavior {

    private static final Logger LOG = getLogger(ReceiptRegisterTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SystemService systemService;

    public void execute(ActivityExecution execution) throws Exception {
        LOG.debug("register receipt");
        LOG.debug("current remove decision: " + execution.getVariable(REMOVE_DECISION));
        LOG.debug("current query decision: " + execution.getVariable(QUERY_DECISION));

        Long receiptId = (Long) execution.getVariable(RECEIPT_ID);
        AcReceipt receipt = billingService.findReceiptById(receiptId);

        LOG.debug("registering bendahari receipt {}", receipt.getReferenceNo());

        // create receipt no
        receipt.setReceiptNo(systemService.generateReferenceNo(RECEIPT_RECEIPT_NO));

        // update flow state
        receipt.getFlowdata().setState(REGISTERED);
        receipt.getFlowdata().setRegisteredDate(new Timestamp(currentTimeMillis()));
        receipt.getFlowdata().setRegistererId(securityService.getCurrentUser().getId());
        billingService.updateReceipt(receipt);

    }
}
