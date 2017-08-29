package my.edu.umk.pams.account.account.model;

import java.math.BigDecimal;
import java.util.Date;


public class AcAccountActivityHolder {

	private String sourceNo;
    private AcAccountTransactionCode transactionCode;
    private BigDecimal totalAmount;
    private Integer transactionCodeOrdinal;
    private Date postedDate;

    public Integer getTransactionCodeOrdinal() {
    	return transactionCodeOrdinal;
    }
    
    public void setTransactionCodeOrdinal(Integer ordinal){
    	this.transactionCodeOrdinal = ordinal;
    }
    
    public String getSourceNo() {
        return sourceNo;
    }

    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }

    public AcAccountTransactionCode getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(AcAccountTransactionCode transactionCode) {
        this.transactionCode = transactionCode;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
    
}
