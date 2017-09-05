package my.edu.umk.pams.account.billing.event;

import org.springframework.context.ApplicationEvent;

import my.edu.umk.pams.account.billing.model.AcDebitNote;

public abstract class DebitNoteEvent extends ApplicationEvent {

	private AcDebitNote debitNote;

    public DebitNoteEvent(AcDebitNote debitNote) {
        super(debitNote);
        this.debitNote = debitNote;
    }

    public AcDebitNote getDebitNote() {
        return debitNote;
    }

    public void setReceipt(AcDebitNote debitNote) {
        this.debitNote = debitNote;
    }
}
