package my.edu.umk.pams.account.billing.event;

import my.edu.umk.pams.account.billing.model.AcDebitNote;

public class DebitNoteCancelledEvent extends DebitNoteEvent{

	public DebitNoteCancelledEvent(AcDebitNote debitNote) {
		super(debitNote);
	}

}
