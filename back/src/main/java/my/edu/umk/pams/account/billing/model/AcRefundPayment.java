package my.edu.umk.pams.account.billing.model;

import java.math.BigDecimal;
import java.util.Date;

import my.edu.umk.pams.account.core.model.AcDocument;

public interface AcRefundPayment extends AcDocument {

    String getDescription();

    void setDescription(String description);
    
    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    Date getIssuedDate();

    void setIssuedDate(Date issuedDate);

	AcAdvancePayment getPayments();

	void setPayments(AcAdvancePayment payments);
	
	String getVoucherNo();

	void setVoucherNo(String voucherNo);
	
	Long getState();
	
    void setState(Long state);
}
