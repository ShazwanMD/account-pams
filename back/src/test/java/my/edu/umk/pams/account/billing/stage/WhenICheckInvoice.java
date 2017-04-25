package my.edu.umk.pams.account.billing.stage;

import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.identity.service.IdentityService;

import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;

public class WhenICheckInvoice extends Stage<WhenICheckInvoice> {
	@Autowired
	private AccountService accountService;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private BillingService billingService;

	public WhenICheckInvoice I_make_invoice_given_charge_code(String matricNumber) {
		// TODO Auto-generated method stub
		return self();
		
	}

}
