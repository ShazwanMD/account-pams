package my.edu.umk.pams.account.billing.event;

import my.edu.umk.pams.account.billing.model.AcKnockoff;

public class KnockoffCancelledEvent extends KnockoffEvent {

    public KnockoffCancelledEvent(AcKnockoff knockoff) {
        super(knockoff);
    }

}
