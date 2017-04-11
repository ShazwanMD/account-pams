package my.edu.umk.pams.account.billing.stage;

import io.jsonwebtoken.lang.Assert;



import java.util.List;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.financialaid.stage.ThenChargesWillBeBilledToSponsor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

@JGivenStage
public class ThenFilterTheInvoice extends Stage<ThenFilterTheInvoice> {
	private static final Logger LOG = LoggerFactory
			.getLogger(ThenFilterTheInvoice.class);
	


	@Autowired
	private AccountService accountService;

	@Autowired
	private BillingService billingService;

	@ExpectedScenarioState
	private AcAccount account;
	
	@ExpectedScenarioState
	private AcAccountCharge charge;


	public ThenFilterTheInvoice I_can_show_invoice_filter_by_charge_code() {
		Assert.notNull(charge,"Charge can not be null");
		LOG.debug("source number {}", charge.getChargeType());
		
		//List<AcAccount> charges = charge.setAccount(account);
		//accountService.addAccountCharge(acAccount, charge);
		
/*		String sourceNo  = charge.getSourceNo();
		LOG.debug("source number {}", sourceNo);
		List<AcInvoice> invoices = 	billingService.findInvoicesBySourceNo(sourceNo);
		Assert.notEmpty(invoices,"invoices can not be empty");*/
			
	return self();
	}

}
