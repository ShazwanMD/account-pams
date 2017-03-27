package my.edu.umk.pams.account.billing.workflow.task;

import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.security.service.SecurityService;
import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

import static my.edu.umk.pams.account.AccountConstants.INVOICE_ID;

@Component("invoice_verify_ST")
public class InvoiceVerifyTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InvoiceVerifyTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long invoiceId = (Long) execution.getVariable(INVOICE_ID);
        AcInvoice invoice = billingService.findInvoiceById(invoiceId);

        LOG.debug("verifying invoice {}", invoice.getReferenceNo());

        invoice.getFlowdata().setState(AcFlowState.VERIFIED);
        invoice.getFlowdata().setVerifiedDate(new Timestamp(System.currentTimeMillis()));
        invoice.getFlowdata().setVerifierId(securityService.getCurrentUser().getId());
        billingService.updateInvoice(invoice);

        // TODO: generate transaction
        // bapManager.generateInvoiceTransactions(invoice);
    }
}
