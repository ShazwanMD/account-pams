package my.edu.umk.pams.account.identity.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.identity.model.AcCoverage;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

@JGivenStage
public class ThenTheSponsorCoverageIsAdded extends Stage<ThenTheSponsorCoverageIsAdded> {
	
	@Autowired
	private IdentityService identityService;
	
	@ExpectedScenarioState
    AcSponsor sponsor;
	
	@As("the sponsor coverage is added")
	public ThenTheSponsorCoverageIsAdded the_sponsor_coverage_is_added() {
		// find coverages by sponsor
		List<AcCoverage> coverages = identityService.findCoverages(sponsor);
        Assert.notEmpty(coverages, "Sponsor must have coverage");

        // or try other API
		boolean hasCoverage = identityService.hasCoverage(sponsor);
		Assert.isTrue(hasCoverage, "Sponsor has coverage");

		return self();
	}
}
