package my.edu.umk.pams.account.billing.event;


import my.edu.umk.pams.account.billing.model.AcInvoice;

/**
 * @author PAMS
 */
public class InvoiceApprovedEvent extends InvoiceEvent {

    public InvoiceApprovedEvent(AcInvoice invoice) {
        super(invoice);
    }
}
