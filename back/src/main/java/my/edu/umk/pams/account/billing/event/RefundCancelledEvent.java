package my.edu.umk.pams.account.billing.event;

import my.edu.umk.pams.account.billing.model.AcRefundPayment;

public class RefundCancelledEvent extends RefundEvent {

    public RefundCancelledEvent(AcRefundPayment refundPayment) {
        super(refundPayment);
    }
}
