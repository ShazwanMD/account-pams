package my.edu.umk.pams.account.account.stage;

import java.util.List;
import org.springframework.test.context.ContextConfiguration;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.config.TestAppConfiguration;

@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class ThenICanMakePayment extends Stage<ThenICanMakePayment> {

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ExpectedScenarioState
	List<AcAccountCharge> charges;

	@As("I can make my payment")
	public ThenICanMakePayment I_can_make_payment() {
		Assert.notEmpty(charges, "charges cannot be empty");
		return self();

	}

}
