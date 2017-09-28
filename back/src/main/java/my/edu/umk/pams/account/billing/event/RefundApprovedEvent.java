package my.edu.umk.pams.account.billing.event;

import my.edu.umk.pams.account.billing.model.AcRefundPayment;

public class RefundApprovedEvent extends RefundEvent {

    public RefundApprovedEvent(AcRefundPayment refundPayment) {
        super(refundPayment);
    }
}
