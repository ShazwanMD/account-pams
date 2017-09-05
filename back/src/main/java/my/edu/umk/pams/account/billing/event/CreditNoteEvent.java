package my.edu.umk.pams.account.billing.event;

import org.springframework.context.ApplicationEvent;

import my.edu.umk.pams.account.billing.model.AcCreditNote;
import my.edu.umk.pams.account.billing.model.AcDebitNote;

public abstract class CreditNoteEvent extends ApplicationEvent {

	private AcCreditNote creditNote;
	
	public CreditNoteEvent(AcCreditNote creditNote) {
		super(creditNote);
		this.creditNote = creditNote;
	}

    public AcCreditNote getCreditNote() {
        return creditNote;
    }

    public void setReceipt(AcCreditNote creditNote) {
        this.creditNote = creditNote;
    }
}
