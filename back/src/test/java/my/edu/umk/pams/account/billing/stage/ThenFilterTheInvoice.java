package my.edu.umk.pams.account.billing.stage;

import io.jsonwebtoken.lang.Assert;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.service.BillingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

@JGivenStage
public class ThenFilterTheInvoice extends Stage<ThenFilterTheInvoice> {
	private static final Logger LOG = LoggerFactory.getLogger(ThenFilterTheInvoice.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private BillingService billingService;

	@ExpectedScenarioState
	private AcAccount account;

	@ExpectedScenarioState
	private AcChargeCode chargeCode;

	@As("The invoice has a correct charge code will listed")
	public ThenFilterTheInvoice then_the_invoice_has_a_correct_charge_code() {
		Assert.notNull(chargeCode, "chargeCode  can not be null");
		LOG.debug("source number {}", chargeCode.getCode());
		LOG.debug("source number {}", chargeCode.getDescription());

		// dapat

		return self();
	}

}
