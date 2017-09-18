package my.edu.umk.pams.account.billing.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import my.edu.umk.pams.account.core.AcMetaObject;
import my.edu.umk.pams.account.core.model.AcDocument;

public interface AcKnockoff extends AcDocument {

	String getReferenceNo();

	void setReferenceNo(String referenceNo);

	String getDescription();

	void setDescription(String description);

	BigDecimal getAmount();

	void setAmount(BigDecimal amount);

	Date getIssuedDate();

	void setIssuedDate(Date issuedDate);

	String getSourceNo();

	void setSourceNo(String sourceNo);

	String getAuditNo();

	void setAuditNo(String auditNo);

	AcAdvancePayment getPayments();

	void setPayments(AcAdvancePayment payments);
	
	BigDecimal getTotalAmount();

	void setTotalAmount(BigDecimal totalAmount);
	
	BigDecimal getBalanceAmount();

	void setBalanceAmount(BigDecimal balanceAmount);
}
