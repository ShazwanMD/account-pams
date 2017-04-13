package my.edu.umk.pams.account.financialaid.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

@JGivenStage
public class ThenICanMakeSureFeesPayment extends Stage<ThenICanMakeSureFeesPayment>{

	
	@ExpectedScenarioState
	private String code;
	
	@As("I want to make sure fess payment")
	public ThenICanMakeSureFeesPayment I_want_to_make_sure_fess_payment() {
		
		//AcStudent student = identityService.findStudentByMatricNo(code);
		//Assert.notNull(student,"Student not null");
		
		return self();
	}
}
