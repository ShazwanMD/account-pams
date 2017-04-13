package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class ThenMyChargeIsReduced extends Stage<ThenMyChargeIsReduced> {

	private static final Logger LOG = LoggerFactory.getLogger(ThenMyChargeIsReduced.class);

	@Autowired
	private AccountService accountService;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ExpectedScenarioState
	private AcAccount account;

	@ExpectedScenarioState
	private AcWaiverApplication waiverApplication;

	@ExpectedScenarioState
	private BigDecimal balanceBefore;

	public ThenMyChargeIsReduced my_charge_is_reduced() {

		Assert.notNull(academicSession, "academic session is a prerequisite");

		BigDecimal balanceAfter = accountService.sumBalanceAmount(account);
		LOG.debug("balanceBefore: " + balanceBefore);
		LOG.debug("balanceAfter: " + balanceAfter);
		return self();
	}
}
