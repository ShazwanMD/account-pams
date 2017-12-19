package my.edu.umk.pams.account.billing.event;


import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.connector.payload.AccountPayload;

/**
 * @author PAMS
 */
public class VoucherApprovedEvent extends InvoiceEvent {

    public VoucherApprovedEvent(AccountPayload payload) {
        super(payload);
    }
}
