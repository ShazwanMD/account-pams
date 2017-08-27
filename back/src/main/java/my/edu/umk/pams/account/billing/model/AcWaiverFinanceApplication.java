package my.edu.umk.pams.account.billing.model;

import java.math.BigDecimal;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.core.model.AcDocument;

public interface AcWaiverFinanceApplication extends AcDocument {

	String getReason();

	void setReason(String reason);

	String getMemo();

	void setMemo(String memo);

	BigDecimal getBalance();
	
	void setBalance(BigDecimal balance);
	
	BigDecimal getEffectiveBalance();
	
	void setEffectiveBalance(BigDecimal effectiveBalance);
	
	BigDecimal getWaivedAmount();
	
	void setWaivedAmount(BigDecimal waivedAmount);
	
	BigDecimal getGracedAmount();
	
	void setGracedAmount(BigDecimal gracedAmount);

	AcAccount getAccount();

	void setAccount(AcAccount account);

	AcAcademicSession getSession();

	void setSession(AcAcademicSession session);
}
