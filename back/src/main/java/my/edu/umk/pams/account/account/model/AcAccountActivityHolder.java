package my.edu.umk.pams.account.account.model;

import java.math.BigDecimal;


public class AcAccountActivityHolder {

	private String sourceNo;
    private AcAccountTransactionCode transactionCode;
    private BigDecimal totalAmount;

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
}
