package my.edu.umk.pams.account.financialaid.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.JGivenStage;

@JGivenStage
public class WhenIWantUpdateAmount extends Stage<WhenIWantUpdateAmount>{

	@As("I want to update student outstanding amount")
	public WhenIWantUpdateAmount update_student_outstanding_amount() {

		return self();
	}
}
