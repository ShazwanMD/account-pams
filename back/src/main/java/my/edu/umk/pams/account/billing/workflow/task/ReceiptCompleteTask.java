package my.edu.umk.pams.account.billing.workflow.task;

import my.edu.umk.pams.account.account.event.AccountRevisedEvent;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.event.ReceiptApprovedEvent;
import my.edu.umk.pams.account.billing.model.AcReceipt;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.connector.payload.AccountPayload;

import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import static my.edu.umk.pams.account.AccountConstants.RECEIPT_ID;

import java.math.BigDecimal;

@Component("receipt_complete_ST")
public class ReceiptCompleteTask extends BpmnActivityBehavior implements ActivityBehavior {

    private static final Logger LOG = LoggerFactory.getLogger(ReceiptCompleteTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private ApplicationContext applicationContext;
    
    @Autowired
    private AccountService accountService;
    
    

    public void execute(ActivityExecution execution) throws Exception {

        // retrieve receipt from variable
        Long receiptId = (Long) execution.getVariable(RECEIPT_ID);
        AcReceipt receipt = billingService.findReceiptById(receiptId);

        LOG.debug("completing bendahari receipt {}", receipt.getReferenceNo());

        // update flow state
        receipt.getFlowdata().setState(AcFlowState.COMPLETED);
        billingService.updateReceipt(receipt);

        // Mintak TOLONG jangan buat apa-apa pada complete task, complete hanya 
        // untuk pergi next task. 
        // Task yang last baru send ke event (untuk buat calculation) iaitu approve task
    }
}
