package my.edu.umk.pams.account.billing.event;

import my.edu.umk.pams.account.billing.model.AcReceipt;
import org.springframework.context.ApplicationEvent;

/**
 * @author PAMS
 */
public abstract class ReceiptEvent extends ApplicationEvent {

    private AcReceipt receipt;

    public ReceiptEvent(AcReceipt receipt) {
        super(receipt);
        this.receipt = receipt;
    }

    public AcReceipt getReceipt() {
        return receipt;
    }

    public void setReceipt(AcReceipt receipt) {
        this.receipt = receipt;
    }
}
