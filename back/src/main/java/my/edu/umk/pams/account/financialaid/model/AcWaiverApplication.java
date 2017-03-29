package my.edu.umk.pams.account.financialaid.model;

import java.math.BigDecimal;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.core.model.AcDocument;

/*
 * scenario 
 * student balance = 3000
 * waived amount = 2800
 * graced amount = 200
 * Effective balance = balance - (waived amount + graced amount)
 *
 * 
 * */

public interface AcWaiverApplication extends AcDocument {
	
	AcAccount getAccount();

    void setAccount(AcAccount account);
	
	BigDecimal getBalance();
	
	void setBalance(BigDecimal balance);
	
	BigDecimal getEffectiveBalance();
	
	void setEffectiveBalance(BigDecimal effectiveBalance);
	
	BigDecimal getWaivedAmount();
	
	void setWaivedAmount(BigDecimal waivedAmount);
	
	BigDecimal getGracedAmount();
	
	void setGracedAmount(BigDecimal gracedAmount);
	
	String getReason();
	
	void setReason(String reason);
	
	String getMemo();
	
	void setMemo(String memo);
	

}
