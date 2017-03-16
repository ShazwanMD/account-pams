package my.edu.umk.pams.account.billing.workflow.task;

import my.edu.umk.pams.account.billing.model.AcInvoice;
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
import static my.edu.umk.pams.account.AccountConstants.INVOICE_ID;
import static my.edu.umk.pams.account.core.AcFlowState.DRAFTED;
import static org.slf4j.LoggerFactory.getLogger;

@Component("invoice_draft_ST")
public class InvoiceDraftTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    private static Logger LOG = getLogger(InvoiceDraftTask.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long invoiceId = (Long) execution.getVariable(INVOICE_ID);
        AcInvoice invoice = billingService.findInvoiceById(invoiceId);

        LOG.debug("drafting invoice {}", invoice.getReferenceNo());

        // append charge to invoice
        // appending will increase total/balance amount
        // appending will add invoice item
        billingService.attach(invoice, invoice.getAccount());

        invoice.getFlowdata().setState(DRAFTED);
        invoice.getFlowdata().setDraftedDate(new Timestamp(currentTimeMillis()));
        invoice.getFlowdata().setDrafterId(securityService.getCurrentUser().getId());
        billingService.updateInvoice(invoice);
    }
}
