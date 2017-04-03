package my.edu.umk.pams.account.billing.stage;

import io.jsonwebtoken.lang.Assert;

import java.util.List;

import my.edu.umk.pams.account.account.model.AcAccount;
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
	private AcAccount invoices;
	
	

	public ThenFilterTheInvoice I_can_show_invoice_filter_by_charge_code() {

		List<AcInvoice> invoices = billingService.findInvoices(account, 0, 100);
		LOG.debug("test");
		Assert.isTrue(!invoices.isEmpty());

		for (AcInvoice acInvoice : invoices) {
			LOG.debug(acInvoice.getReferenceNo());
			LOG.debug(acInvoice.getDescription());
		}

		// Assert.notNull(account, "account was null");

		return self();
	}

}
