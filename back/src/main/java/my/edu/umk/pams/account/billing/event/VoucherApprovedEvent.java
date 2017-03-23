package my.edu.umk.pams.account.billing.event;


import my.edu.umk.pams.account.billing.model.AcInvoice;

/**
 * @author PAMS
 */
public class VoucherApprovedEvent extends InvoiceEvent {

    public VoucherApprovedEvent(AcInvoice invoice) {
        super(invoice);
    }
}
