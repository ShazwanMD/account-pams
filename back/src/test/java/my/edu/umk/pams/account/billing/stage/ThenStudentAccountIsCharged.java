package my.edu.umk.pams.account.billing.stage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.identity.model.AcStudent;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;

import java.util.List;

@JGivenStage
public class ThenStudentAccountIsCharged extends Stage<ThenStudentAccountIsCharged> {

	@Autowired
	private AccountService accountService;

	@ExpectedScenarioState
	private AcStudent student;

	@ExpectedScenarioState
	private AcAccount account;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@As("student account is charged")
	public ThenStudentAccountIsCharged student_account_is_charged() {
		List<AcAccountCharge> charges = accountService.findAccountCharges(academicSession, account);
		Assert.isTrue(!charges.isEmpty());
		return self();
	}
}
