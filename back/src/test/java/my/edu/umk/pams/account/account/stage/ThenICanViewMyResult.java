package my.edu.umk.pams.account.account.stage;

<<<<<<< HEAD
=======
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
>>>>>>> branch 'master' of http://119.110.101.9/pams/account.git
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;

@JGivenStage
public class ThenICanViewMyResult extends Stage<ThenICanViewMyResult> {
<<<<<<< HEAD
=======

	private static final Logger LOG = LoggerFactory.getLogger(ThenICanViewMyResult.class);
>>>>>>> branch 'master' of http://119.110.101.9/pams/account.git

	@ExpectedScenarioState
	AcAcademicSession academicSession;

	@ExpectedScenarioState
	AcAccount account;

	@ExpectedScenarioState
	AcAccountCharge accountCharge;

	@Pending
	public void I_can_view_my_result() {
		// todo: ni dah melibatkan academic module - how?

	}

}
