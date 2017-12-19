package my.edu.umk.pams.account.billing.workflow.task;

import my.edu.umk.pams.account.account.event.AccountClosedEvent;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.connector.payload.AccountPayload;
import my.edu.umk.pams.connector.payload.StudentStatus;

import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static my.edu.umk.pams.account.AccountConstants.INVOICE_ID;

import java.math.BigDecimal;

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
        
        // Mintak TOLONG jangan buat apa-apa pada complete task, complete hanya 
        // untuk pergi next task. 
        // Task yang last baru send ke event (untuk buat calculation) iaitu approve task
        
    	AcAccount account = accountService.findAccountByCode(invoice.getAccount().getCode());
        
        LOG.info("Start Broadcast Account Payload");
        AccountPayload payload = new AccountPayload();
        payload.setMatricNo(account.getActor().getIdentityNo());
        payload.setCode(account.getCode());
       
        BigDecimal total = accountService.sumBalanceAmount(account);
        
        payload.setBalance(total);
        LOG.debug("Total:{}",total);
        if(total.signum() > 0){
        	payload.setOutstanding(true);
        	payload.setStudentStatus(StudentStatus.BARRED);
        }else{
        	payload.setOutstanding(false);
        	payload.setStudentStatus(StudentStatus.ACTIVE);
        }
        
        AccountClosedEvent event = new AccountClosedEvent(payload);
        applicationContext.publishEvent(event);
        LOG.info("Finish Broadcast Account Payload");

    }
}
