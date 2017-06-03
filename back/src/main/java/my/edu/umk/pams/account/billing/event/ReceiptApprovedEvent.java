package my.edu.umk.pams.account.billing.event;


import my.edu.umk.pams.account.billing.model.AcReceipt;

/**
 * @author PAMS
 */
public class ReceiptApprovedEvent extends ReceiptEvent {

    public ReceiptApprovedEvent(AcReceipt receipt) {
        super(receipt);
    }
}
