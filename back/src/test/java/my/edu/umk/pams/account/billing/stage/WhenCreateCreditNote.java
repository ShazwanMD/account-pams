package my.edu.umk.pams.account.billing.stage;

import my.edu.umk.pams.account.billing.service.BillingService;

import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.Pending;

@Pending
public class WhenCreateCreditNote extends Stage<WhenCreateCreditNote> {
	
	@Autowired
	BillingService billingService;
	
	/*
	 * credit note services not available yet
	 * */
	
	public WhenCreateCreditNote Create_credit_note(){
		return self();
	}
}
