package my.edu.umk.pams.account.billing.event;

import org.springframework.context.ApplicationEvent;
import my.edu.umk.pams.account.billing.model.AcRefundPayment;

public abstract class RefundEvent extends ApplicationEvent {

    private AcRefundPayment refundPayment;

    public RefundEvent(AcRefundPayment refundPayment) {
        super(refundPayment);
        this.refundPayment = refundPayment;
    }

    public AcRefundPayment getRefundPayment() {
        return refundPayment;
    }

    public void setRefundPayment(AcRefundPayment refundPayment) {
        this.refundPayment = refundPayment;
    }
}
