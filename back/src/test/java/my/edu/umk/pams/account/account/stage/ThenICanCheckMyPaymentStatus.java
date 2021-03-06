package my.edu.umk.pams.account.account.stage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.service.AccountService;

@JGivenStage
public class ThenICanCheckMyPaymentStatus extends Stage<ThenICanCheckMyPaymentStatus> {

	@Autowired
	private AccountService accountService;

	@ExpectedScenarioState
	AcAcademicSession academicSession;

	@ExpectedScenarioState
	AcAccount account;

	@ExpectedScenarioState
	List<AcAccountCharge> charges;

	@As("I can check my payment status")
	public ThenICanCheckMyPaymentStatus I_can_check_my_payment_status() {

		List<AcAccountCharge> charges = accountService.findAccountCharges(academicSession, account);
		Assert.isTrue(!charges.isEmpty());
		return self();

	}

}
