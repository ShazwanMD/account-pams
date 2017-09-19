package my.edu.umk.pams.account.billing.model;

import java.math.BigDecimal;
import java.util.List;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.core.AcMetaObject;

public interface AcAdvancePayment extends AcMetaObject {

	String getReferenceNo();

	void setReferenceNo(String referenceNo);

	String getDescription();

	void setDescription(String description);

	BigDecimal getAmount();

	void setAmount(BigDecimal amount);

	BigDecimal getBalanceAmount();

	void setBalanceAmount(BigDecimal balanceAmount);

	AcReceipt getReceipt();

	void setReceipt(AcReceipt receipt);

	List<AcKnockoff> getKnockoff();

	void setKnockoff(List<AcKnockoff> knockoff);

	Boolean getStatus();

	void setStatus(Boolean status);

	AcAccount getAccount();

	void setAccount(AcAccount account);
	
	List<AcRefundPayment> getRefundPayment();

	void setRefundPayment(List<AcRefundPayment> refundPayment);
	
    AcAcademicSession getSession();

    void setSession(AcAcademicSession session);
}
