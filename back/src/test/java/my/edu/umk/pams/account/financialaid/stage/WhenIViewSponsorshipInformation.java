package my.edu.umk.pams.account.financialaid.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.identity.model.AcCoverage;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class WhenIViewSponsorshipInformation extends Stage<WhenIViewSponsorshipInformation> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIViewSponsorshipInformation.class);

	@Autowired
	private IdentityService identityService;

	@ExpectedScenarioState
	private AcStudent student;

	@ExpectedScenarioState
	private AcAccount account;

	@ExpectedScenarioState
	private List<AcSponsorship> sponsorship;

	@ExpectedScenarioState
	private List<AcCoverage> coverage;

	@As("I want to view my sponsorship information")
	public WhenIViewSponsorshipInformation I_want_to_view_sponsorship_information_$(String matricNo) {

		student = identityService.findStudentByMatricNo(matricNo);
		LOG.debug("Student ID :" + student.getIdentityNo());

		sponsorship = identityService.findSponsorships(student);
		
		boolean hasSponsorship = identityService.hasSponsorship(student);
		Assert.isTrue(hasSponsorship, "student should have sponsorship");
		
		for (AcSponsorship sponsorships : sponsorship) {

			LOG.debug("Sponsor ID :" + sponsorships.getSponsor().getIdentityNo());

			coverage = identityService.findCoverages(sponsorships.getSponsor());

			for (AcCoverage coverage : coverage) {

				LOG.debug("coverage charge code: (" + coverage.getChargeCode().getCode() + ") "
						+ coverage.getChargeCode().getDescription());

			}

		}
		return self();
	}
}
