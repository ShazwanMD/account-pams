package my.edu.umk.pams.account.billing.stage;

import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;

import my.edu.umk.pams.account.account.model.AcAcademicSession;

public class ThenProcessThePayment extends Stage<ThenProcessThePayment> {
	
	@ExpectedScenarioState
	private AcAcademicSession academicSession;
	
	@As("process the payment")
	public ThenProcessThePayment Process_the_payment() {

		Assert.notNull(academicSession, "academic session is a prerequisite");
		return self();
	}
}
