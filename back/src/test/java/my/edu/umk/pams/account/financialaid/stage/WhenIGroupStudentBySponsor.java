package my.edu.umk.pams.account.financialaid.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;


@JGivenStage
public class WhenIGroupStudentBySponsor extends Stage<WhenIGroupStudentBySponsor> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIGroupStudentBySponsor.class);

	@Autowired
	private IdentityService identityService;

	@ProvidedScenarioState
	AcSponsor sponsor;
	
	@ExpectedScenarioState
	private AcStudent student;

	@ExpectedScenarioState
	private List<AcSponsorship> sponsorship;

	@As("I group student by sponsor")
	public WhenIGroupStudentBySponsor I_group_student_by_$_sponsor(String sponsorNo) {

		sponsor = identityService.findSponsorBySponsorNo(sponsorNo);

		sponsorship = identityService.findSponsorships(sponsor);
		
		for (AcSponsorship sponsorship : sponsorship) {
			
		      LOG.debug("Student ID :"+sponsorship.getStudent().getMatricNo());
		    }
		return self();
	}
}
