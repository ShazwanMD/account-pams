package my.edu.umk.pams.account.financialaid.stage;

import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.identity.model.AcStudent;

@JGivenStage
public class ThenICanMakeSureFeesPayment extends Stage<ThenICanMakeSureFeesPayment> {

	@ProvidedScenarioState
	private AcStudent student;

	@As("I want to make sure fess payment")
	public ThenICanMakeSureFeesPayment I_want_to_make_sure_fess_payment() {

		Assert.notNull(student, "student was null");

		return self();
	}
}
