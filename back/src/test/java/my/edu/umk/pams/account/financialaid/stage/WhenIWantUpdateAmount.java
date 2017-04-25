package my.edu.umk.pams.account.financialaid.stage;

import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.identity.model.AcStudent;

@JGivenStage
public class WhenIWantUpdateAmount extends Stage<WhenIWantUpdateAmount>{

	@ProvidedScenarioState
	private AcStudent student;
	
	@As("I want to update student outstanding amount")
	public WhenIWantUpdateAmount update_student_outstanding_amount() {

		Assert.notNull(student, "student was null");
		
		return self();
	}
	
	@As("I want to update short term loan (STL) amount")
	public WhenIWantUpdateAmount update_short_term_loan_amount() {

		Assert.notNull(student, "student was null");
		return self();
	}
}
