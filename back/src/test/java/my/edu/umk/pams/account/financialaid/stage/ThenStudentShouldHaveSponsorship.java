package my.edu.umk.pams.account.financialaid.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

@JGivenStage
public class ThenStudentShouldHaveSponsorship extends Stage<ThenStudentShouldHaveSponsorship>{
	
	@Autowired
	private IdentityService identityService;
	
	@ExpectedScenarioState
	AcStudent student;
	
	@As("the student has sponsorship")
	public ThenStudentShouldHaveSponsorship the_student_has_sponsorship() {
		boolean hasSponsorship = identityService.hasSponsorship(student);
		Assert.isTrue(hasSponsorship, "student should have sponsorship");
		return self();
	}
}
