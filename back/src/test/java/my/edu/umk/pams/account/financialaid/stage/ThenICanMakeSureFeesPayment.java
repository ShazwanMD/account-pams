package my.edu.umk.pams.account.financialaid.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class ThenICanMakeSureFeesPayment extends Stage<ThenICanMakeSureFeesPayment>{

	@Autowired
	private IdentityService identityService;
	
	@ExpectedScenarioState
	private String code;
	
	@As("I want to make sure fess payment")
	public ThenICanMakeSureFeesPayment I_want_to_make_sure_fess_payment() {
		
		//AcStudent student = identityService.findStudentByMatricNo(code);
		//Assert.notNull(student,"Student not null");
		
		return self();
	}
}
