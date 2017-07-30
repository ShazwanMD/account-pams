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

import java.sql.Timestamp;

import static my.edu.umk.pams.account.AccountConstants.RECEIPT_ID;


@Component("receipt_approve_ST")
public class ReceiptApproveTask extends BpmnActivityBehavior implements ActivityBehavior {

    private static final Logger LOG = LoggerFactory.getLogger(ReceiptApproveTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private AccountService accountService;

    @Autowired
    private ApplicationContext applicationContext;

    public void execute(ActivityExecution execution) throws Exception {
        // retrieve receipt from variable
        Long receiptId = (Long) execution.getVariable(RECEIPT_ID);
        AcReceipt receipt = billingService.findReceiptById(receiptId);

        LOG.debug("approving bendahari receipt {}", receipt.getReferenceNo());

        //. email user
        // hantar notification
        // send credit/debit to accounting

        // update flow state
        receipt.getFlowdata().setState(AcFlowState.APPROVED);
        receipt.getFlowdata().setApprovedDate(new Timestamp(System.currentTimeMillis()));
        receipt.getFlowdata().setApproverId(securityService.getCurrentUser().getId());
        billingService.updateReceipt(receipt);
        billingService.post(receipt);
        applicationContext.publishEvent(new ReceiptApprovedEvent(receipt));

        AcAccount account = receipt.getAccount();
        AccountPayload payload = new AccountPayload();
        payload.setCode(account.getCode());
        payload.setMatricNo(account.getActor().getIdentityNo());
        payload.setOutstanding(accountService.hasBalance(account));
        payload.setBalance(accountService.sumBalanceAmount(account));
        AccountRevisedEvent event = new AccountRevisedEvent(payload);
    }
}
