package my.edu.umk.pams.account.billing.model;

import java.math.BigDecimal;

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

	AcKnockoff getKnockoff();

	void setKnockoff(AcKnockoff knockoff);

	Boolean getStatus();

	void setStatus(Boolean status);

	AcAccount getAccount();

	void setAccount(AcAccount account);
}
