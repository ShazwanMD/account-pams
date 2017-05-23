package my.edu.umk.pams.account.web.module.billing.vo;

import my.edu.umk.pams.account.web.module.core.vo.Task;

public class DebitNoteTask extends Task {

    private DebitNote debitNote;

    public DebitNote getDebitNote() {
        return debitNote;
    }

    public void setDebitNote(DebitNote debitNote) {
        this.debitNote = debitNote;
    }
}
