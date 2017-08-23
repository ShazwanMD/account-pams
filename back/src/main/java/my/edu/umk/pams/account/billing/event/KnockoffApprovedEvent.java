package my.edu.umk.pams.account.billing.event;

import my.edu.umk.pams.account.billing.model.AcKnockoff;

public class KnockoffApprovedEvent extends KnockoffEvent {

    public KnockoffApprovedEvent(AcKnockoff knockoff) {
        super(knockoff);
    }
}
