package my.edu.umk.pams.account.billing.event;

import my.edu.umk.pams.account.billing.model.AcInvoice;
import org.springframework.context.ApplicationEvent;

/**
 * @author PAMS
 */
public abstract class InvoiceEvent extends ApplicationEvent {

    private AcInvoice invoice;

    public InvoiceEvent(AcInvoice invoice) {
        super(invoice);
        this.invoice = invoice;
    }

    public AcInvoice getInvoice() {
        return invoice;
    }

    public void setInvoice(AcInvoice invoice) {
        this.invoice = invoice;
    }
}
