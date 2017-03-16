package my.edu.umk.pams.account.identity.stage;

import static org.junit.Assert.assertEquals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.identity.model.AcCoverage;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class ThenTheSponsorCoverageIsAdded extends Stage<ThenTheSponsorCoverageIsAdded> {
	
	@ExpectedScenarioState
	private IdentityService identityService;
	
	@ExpectedScenarioState
	private AcCoverage coverage;
	
	@ExpectedScenarioState
    AcSponsor sponsor;
	
    @ExpectedScenarioState
    Long coverageId;

	@As("the sponsor coverage is added")
	public ThenTheSponsorCoverageIsAdded the_sponsor_coverage_is_added() {

		//TO-DO: my coverage id return null
		AcCoverage coverage = identityService.findCoverageById(coverageId);
		
        final String entityName = coverage.getClass().getSimpleName();
        Assert.notNull(coverage.getId(), "No " + entityName + " found with id " + coverage.getId());
		return self();

	}
}
