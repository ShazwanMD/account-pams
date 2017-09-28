package my.edu.umk.pams.account.billing.event;

import my.edu.umk.pams.account.billing.model.AcWaiverFinanceApplication;

public class WaiverCancelledEvent extends WaiverEvent {
	
    public WaiverCancelledEvent(AcWaiverFinanceApplication waiver) {
        super(waiver);
    }

}
