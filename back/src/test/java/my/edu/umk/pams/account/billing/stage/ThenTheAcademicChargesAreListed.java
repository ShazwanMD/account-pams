package my.edu.umk.pams.account.billing.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeType;
import my.edu.umk.pams.account.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

@JGivenStage
public class ThenTheAcademicChargesAreListed extends Stage<ThenTheAcademicChargesAreListed> {
	@ExpectedScenarioState
	private AcAccount account;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@Autowired
	private AccountService accountService;

	@ExpectedScenarioState
	private AcAccountChargeType chargeType;

	public ThenTheAcademicChargesAreListed the_charges_are_listed() {

		List<AcAccountCharge> accountCharges = accountService.findAccountCharges(account, chargeType);
		Assert.notEmpty(accountCharges, "Account cannot be empty");

		return self();
	}

}
