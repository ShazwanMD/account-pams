package my.edu.umk.pams.account.billing.event;

import my.edu.umk.pams.account.billing.model.AcCreditNote;

public class CreditNoteApprovedEvent extends CreditNoteEvent {

	public CreditNoteApprovedEvent(AcCreditNote creditNote) {
		super(creditNote);
	}

}
