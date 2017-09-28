package my.edu.umk.pams.account.billing.event;

import org.springframework.context.ApplicationEvent;
import my.edu.umk.pams.account.billing.model.AcWaiverFinanceApplication;

public abstract class WaiverEvent extends ApplicationEvent {

	private AcWaiverFinanceApplication waiver;

    public WaiverEvent(AcWaiverFinanceApplication waiver) {
        super(waiver);
        this.waiver = waiver;
    }

    public AcWaiverFinanceApplication getWaiverFinanceApplication() {
        return waiver;
    }

    public void setWaiverFinanceApplication(AcWaiverFinanceApplication waiver) {
        this.waiver = waiver;
    }
}
