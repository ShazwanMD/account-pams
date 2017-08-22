package my.edu.umk.pams.account.financialaid.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcSponsorshipImpl;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class WhenIWantConfigureStudentSponsor extends Stage<WhenIWantConfigureStudentSponsor> {

	@Autowired
	private IdentityService identityService;

	@ExpectedScenarioState
	private AcStudent student;

	@ExpectedScenarioState
	private List<AcSponsorship> sponsorship;

	@ExpectedScenarioState
	private List<AcSponsor> sponsor;

	@As("I want to configure student sponsor")
	public WhenIWantConfigureStudentSponsor I_want_to_configure_student_sponsor(String matricNo) {

		student = identityService.findStudentByMatricNo(matricNo);
		boolean hasSponsorship = identityService.hasSponsorship(student);
		Assert.isTrue(hasSponsorship, "student should have sponsorship");

		sponsorship = identityService.findSponsorships(student);

		for (AcSponsorship sponsorship : sponsorship) {

			AcSponsor sponsor = identityService.findSponsorBySponsorNo(sponsorship.getSponsor().getIdentityNo());
			boolean hasSponsor = identityService.hasSponsorship(sponsor);
			Assert.isTrue(hasSponsor, "sponsor should have sponsorship");

			identityService.removeSponsorship(sponsor, sponsorship);

			AcSponsor sponsorNew = identityService.findSponsorBySponsorNo("SLAB");
			AcSponsorship sphp = new AcSponsorshipImpl();
			sphp.setSponsor(sponsorNew);
			//sphp.setStudent(student);
			identityService.addSponsorship(sponsorNew, sphp);

		}
		return self();
	}
}
