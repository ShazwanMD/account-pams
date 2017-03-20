package my.edu.umk.pams.account.identity.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.identity.model.AcCoverage;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class ThenStudentSponsorshipIsAdded extends Stage<ThenStudentSponsorshipIsAdded>{
	
	@ExpectedScenarioState
	private IdentityService identityService;
	
	@ExpectedScenarioState
	private AcCoverage coverage;
	
	@ExpectedScenarioState
    AcSponsor sponsor;
	
    @ExpectedScenarioState
    private Long coverageId;

	@As("the student sponsorship is added")
	public ThenStudentSponsorshipIsAdded the_student_sponsorship_is_added() {

		return self();

	}
}
