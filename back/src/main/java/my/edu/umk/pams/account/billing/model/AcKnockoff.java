package my.edu.umk.pams.account.billing.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import my.edu.umk.pams.account.core.AcMetaObject;
import my.edu.umk.pams.account.core.model.AcDocument;

public interface AcKnockoff extends AcMetaObject {

	String getReferenceNo();

	void setReferenceNo(String referenceNo);

	String getDescription();

	void setDescription(String description);

	BigDecimal getAmount();

	void setAmount(BigDecimal amount);

	Date getIssuedDate();

	void setIssuedDate(Date issuedDate);

	AcInvoice getInvoice();

	void setInvoice(AcInvoice invoice);

	String getSourceNo();

	void setSourceNo(String sourceNo);

	String getAuditNo();

	void setAuditNo(String auditNo);

	List<AcAdvancePayment> getPayments();

	void setPayments(List<AcAdvancePayment> payments);
}
