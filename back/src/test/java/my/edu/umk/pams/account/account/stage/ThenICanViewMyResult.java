package my.edu.umk.pams.account.account.stage;

import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;

@JGivenStage
public class ThenICanViewMyResult extends Stage<ThenICanViewMyResult> {

	@ExpectedScenarioState
	AcAcademicSession academicSession;

	@ExpectedScenarioState
	AcAccount account;

	@ExpectedScenarioState
	AcAccountCharge accountCharge;

	@Pending
	public ThenICanViewMyResult I_can_view_my_result() {

		Assert.notNull(academicSession, "academic session is a prerequisite");
		Assert.notNull(accountCharge, "account charge is a prerequisite");
		return self();
	}

}
