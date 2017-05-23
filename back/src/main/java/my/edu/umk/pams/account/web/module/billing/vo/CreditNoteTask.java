package my.edu.umk.pams.account.web.module.billing.vo;

import my.edu.umk.pams.account.web.module.core.vo.Task;

public class CreditNoteTask extends Task {

    private CreditNote creditNote;

    public CreditNote getCreditNote() {
        return creditNote;
    }

    public void setCreditNote(CreditNote creditNote) {
        this.creditNote = creditNote;
    }
}

