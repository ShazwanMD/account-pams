package my.edu.umk.pams.account.billing.workflow.task;

import my.edu.umk.pams.account.account.event.AccountRevisedEvent;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.event.InvoiceApprovedEvent;
import my.edu.umk.pams.account.billing.model.AcInvoice;
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

import static my.edu.umk.pams.account.AccountConstants.INVOICE_ID;

@Component("invoice_approve_ST")
public class InvoiceApproveTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    private static final Logger LOG = LoggerFactory.getLogger(InvoiceApproveTask.class);

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ApplicationContext applicationContext;

    public void execute(ActivityExecution execution) throws Exception {
        Long invoiceId = (Long) execution.getVariable(INVOICE_ID);
        AcInvoice invoice = billingService.findInvoiceById(invoiceId);
        LOG.debug("approving invoice {}", invoice.getReferenceNo());

        invoice.setTotalAmount(invoice.getTotalAmount());
        invoice.setBalanceAmount(invoice.getTotalAmount());

        // update flow state
        invoice.getFlowdata().setState(AcFlowState.APPROVED);
        invoice.getFlowdata().setApprovedDate(new Timestamp(System.currentTimeMillis()));
        invoice.getFlowdata().setApproverId(securityService.getCurrentUser().getId());
        billingService.updateInvoice(invoice);

        billingService.post(invoice);

        // fire event
        applicationContext.publishEvent(new InvoiceApprovedEvent(invoice));

        AcAccount account = invoice.getAccount();
        AccountPayload payload = new AccountPayload();
        payload.setCode(account.getCode());
        payload.setMatricNo(account.getActor().getIdentityNo());
        payload.setOutstanding(accountService.hasBalance(account));
        payload.setBalance(accountService.sumBalanceAmount(account));
        AccountRevisedEvent event = new AccountRevisedEvent(payload);
    }
}
