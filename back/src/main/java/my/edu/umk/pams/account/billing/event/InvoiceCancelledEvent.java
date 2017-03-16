package my.edu.umk.pams.account.billing.event;


import my.edu.umk.pams.account.billing.model.AcInvoice;

/**
 * @author PAMS
 */
public class InvoiceCancelledEvent extends InvoiceEvent {

    public InvoiceCancelledEvent(AcInvoice invoice) {
        super(invoice);
    }
}
