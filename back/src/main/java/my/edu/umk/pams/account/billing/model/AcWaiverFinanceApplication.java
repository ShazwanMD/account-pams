package my.edu.umk.pams.account.billing.model;

import java.math.BigDecimal;
import java.util.List;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountWaiver;
import my.edu.umk.pams.account.core.model.AcDocument;
import my.edu.umk.pams.account.financialaid.model.AcGraduateCenterType;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplicationType;

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
	
	AcWaiverApplicationType getWaiverType();
	
	void setWaiverType(AcWaiverApplicationType waiverType);
	
	AcGraduateCenterType getGraduateCenterType();
	
	void setGraduateCenterType(AcGraduateCenterType waiverType);
	
	List<AcInvoice> getInvoices();
	
	List<AcAccountCharge> getAccountCharges();
	
	List<AcDebitNote> getDebitNotes();
	
	AcAccountWaiver getAccWaiver(); 
	
	void setAccWaiver(AcAccountWaiver accWaiver);
}
