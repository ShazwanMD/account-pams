package my.edu.umk.pams.account.web.module.billing.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import my.edu.umk.pams.account.web.module.account.vo.ChargeCode;
import my.edu.umk.pams.account.web.module.core.vo.Task;

public class DebitNoteTask extends Task {

    private DebitNote debitNote;

    // denormalize
    private String accountCode;
    private String accountName;
    private ChargeCode sodoCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date debitNoteDate;
    
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

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
    
    public Date getDebitNoteDate() {
		return debitNoteDate;
	}
	
	public void setDebitNoteDate(Date debitNoteDate) {
		this.debitNoteDate = debitNoteDate;
	}
	
	public ChargeCode getChargeCode() {
        return sodoCode;
    }

    public void setChargeCode(ChargeCode sodoCode) {
        this.sodoCode = sodoCode;
    }
}
