package my.edu.umk.pams.account.billing.event;

import my.edu.umk.pams.account.billing.model.AcCreditNote;

public class CreditNoteCancelledEvent extends CreditNoteEvent {

	public CreditNoteCancelledEvent(AcCreditNote creditNote) {
		super(creditNote);
	}

}
