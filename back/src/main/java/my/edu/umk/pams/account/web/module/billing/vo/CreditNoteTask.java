package my.edu.umk.pams.account.web.module.billing.vo;

import java.math.BigDecimal;

import my.edu.umk.pams.account.web.module.core.vo.Task;

public class CreditNoteTask extends Task {

    private CreditNote creditNote;
    
    private String accountCode;
    
    private BigDecimal totalAmount = new BigDecimal(0.00);
    

    public CreditNote getCreditNote() {
        return creditNote;
    }

    public void setCreditNote(CreditNote creditNote) {
        this.creditNote = creditNote;
    }
    
    public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
    
}

