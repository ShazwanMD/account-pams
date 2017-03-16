package my.edu.umk.pams.account.identity.stage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcSponsorshipImpl;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class WhenIGroupStudentBySponsor extends Stage<WhenIGroupStudentBySponsor> {

	@ProvidedScenarioState
	private String sponsorNo;

	@ProvidedScenarioState
	private String sponsorName;

	@ProvidedScenarioState
	private Long sponsorId;

	@ExpectedScenarioState
	private AcUser currentUser;

	@Autowired
	private IdentityService identityService;

	@ProvidedScenarioState
	AcSponsorship sponsorship;

	@As("I group student by sponsor")
	public WhenIGroupStudentBySponsor I_group_student_by_sponsor() {

		return self();
	}
}
