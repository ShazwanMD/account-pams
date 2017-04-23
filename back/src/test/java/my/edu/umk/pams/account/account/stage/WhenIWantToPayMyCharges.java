package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.identity.model.AcStudent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JGivenStage
public class WhenIWantToPayMyCharges extends Stage<WhenIWantToPayMyCharges> {
	private static final Logger LOG = LoggerFactory.getLogger(WhenIWantToPayMyCharges.class);

	@ExpectedScenarioState
	private AcStudent student;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ProvidedScenarioState
	private AcAccount account;

	public WhenIWantToPayMyCharges I_want_to_pay_my_charges() {

		// todo:the process of paying charge - how?

		return self();

	}

}
