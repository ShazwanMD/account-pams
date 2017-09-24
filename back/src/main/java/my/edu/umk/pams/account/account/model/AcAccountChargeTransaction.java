package my.edu.umk.pams.account.account.model;

import java.math.BigDecimal;
import java.util.Date;

import my.edu.umk.pams.account.core.AcMetaObject;

public interface AcAccountChargeTransaction extends AcMetaObject {

	String getSourceNo();

	void setSourceNo(String sourceNo);

	BigDecimal getAmount();

	void setAmount(BigDecimal amount);

	String getDescription();

	void setDescription(String description);

	Date getPostedDate();

	void setPostedDate(Date postedDate);

	AcAccountChargeType getTransactionCode();

	void setTransactionCode(AcAccountChargeType transactionCode);

	AcAccount getAccount();

	void setAccount(AcAccount account);

	AcAccountCharge getChargeCode();

	void setChargeCode(AcAccountCharge chargeCode);

	AcAcademicSession getSession();

	void setSession(AcAcademicSession academicSession);

}
