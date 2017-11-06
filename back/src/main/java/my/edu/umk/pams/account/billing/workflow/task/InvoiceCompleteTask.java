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

import java.math.BigDecimal;

import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static my.edu.umk.pams.account.AccountConstants.INVOICE_ID;

@Component("invoice_complete_ST")
@Transactional
public class InvoiceCompleteTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InvoiceCompleteTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private ApplicationContext applicationContext;
    
    @Autowired
    private AccountService accountService;

    public void execute(ActivityExecution execution) throws Exception {
        Long invoiceId = (Long) execution.getVariable(INVOICE_ID);
        AcInvoice invoice = billingService.findInvoiceById(invoiceId);

        LOG.debug("completing invoice {}", invoice.getReferenceNo());

        invoice.getFlowdata().setState(AcFlowState.COMPLETED);
        billingService.updateInvoice(invoice);
        
//      // fire event
      applicationContext.publishEvent(new InvoiceApprovedEvent(invoice));
  
      
      AcAccount account = invoice.getAccount();
      AccountPayload payload = new AccountPayload();
      payload.setCode(account.getCode());
      payload.setMatricNo(account.getActor().getIdentityNo());
      
     BigDecimal accountBalance = accountService.sumBalanceAmount(account); 

     if( accountBalance.compareTo(BigDecimal.ZERO) > 0 ){
    	 
    	  payload.setOutstanding(true);
      }else{
    	  payload.setOutstanding(false);
      }
      payload.setBalance(accountService.sumBalanceAmount(account));
      
      AccountRevisedEvent event = new AccountRevisedEvent(payload);
      applicationContext.publishEvent(event);

        // todo(uda): post acocunt transaction
    }
}
