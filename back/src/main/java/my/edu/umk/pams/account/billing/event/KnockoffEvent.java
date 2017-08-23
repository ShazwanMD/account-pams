package my.edu.umk.pams.account.billing.event;

import org.springframework.context.ApplicationEvent;

import my.edu.umk.pams.account.billing.model.AcKnockoff;

public abstract class KnockoffEvent extends ApplicationEvent {

	private AcKnockoff knockoff;

    public KnockoffEvent(AcKnockoff knockoff) {
        super(knockoff);
        this.knockoff = knockoff;
    }

    public AcKnockoff getKnockoff() {
        return knockoff;
    }

    public void setKnockoff(AcKnockoff knockoff) {
        this.knockoff = knockoff;
    }
}
