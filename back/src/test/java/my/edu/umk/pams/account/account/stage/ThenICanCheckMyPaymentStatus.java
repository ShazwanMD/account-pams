package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;

@JGivenStage
public class ThenICanCheckMyPaymentStatus extends Stage<ThenICanCheckMyPaymentStatus> {


	@ExpectedScenarioState
	AcAcademicSession academicSession;

	@ExpectedScenarioState
	AcAccount account;

	@ExpectedScenarioState
	AcAccountCharge accountCharge;

	@Pending
	public ThenICanCheckMyPaymentStatus I_can_check_my_payment_status() {

		// todo: check the status, make sure payment is cleared
		return self();
	}

}
