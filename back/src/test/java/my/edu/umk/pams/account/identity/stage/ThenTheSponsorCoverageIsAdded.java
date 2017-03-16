package my.edu.umk.pams.account.identity.stage;

import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.identity.model.AcCoverage;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class ThenTheSponsorCoverageIsAdded extends Stage<ThenTheSponsorCoverageIsAdded> {

	@ExpectedScenarioState
	private IdentityService identityService;
	
    @ExpectedScenarioState
    private Long id;

	@As("the sponsor coverage is added")
	public ThenTheSponsorCoverageIsAdded the_sponsor_coverage_is_added() {

//		AcCoverage coverage = identityService.findCoverageById(id);

       // final String entityName = coverage.getClass().getSimpleName();
       // Assert.notNull(coverage.getId(), "No " + entityName + " found with id " + coverage.getId());
//        Assert.notNull(id, entityName + " must have Id");
		return self();

	}
}
