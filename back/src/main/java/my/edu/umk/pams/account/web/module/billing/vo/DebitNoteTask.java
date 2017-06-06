package my.edu.umk.pams.account.web.module.billing.vo;

import my.edu.umk.pams.account.web.module.core.vo.Task;

public class DebitNoteTask extends Task {

    private DebitNote debitNote;

    // denormalize
    private String accountCode;

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public DebitNote getDebitNote() {
        return debitNote;
    }

    public void setDebitNote(DebitNote debitNote) {
        this.debitNote = debitNote;
    }
}
