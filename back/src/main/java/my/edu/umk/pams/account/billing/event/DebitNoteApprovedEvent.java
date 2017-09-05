package my.edu.umk.pams.account.billing.event;

import my.edu.umk.pams.account.billing.model.AcDebitNote;

public class DebitNoteApprovedEvent extends DebitNoteEvent{

	public DebitNoteApprovedEvent(AcDebitNote debitNote) {
		super(debitNote);
	}
}
