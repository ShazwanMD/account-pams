package my.edu.umk.pams.account.billing.event;

import my.edu.umk.pams.account.billing.model.AcWaiverFinanceApplication;

public class WaiverApprovedEvent extends WaiverEvent {
	
    public WaiverApprovedEvent(AcWaiverFinanceApplication waiver) {
        super(waiver);
    }
}
