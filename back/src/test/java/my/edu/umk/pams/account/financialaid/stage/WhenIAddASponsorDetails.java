package my.edu.umk.pams.account.financialaid.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.identity.model.AcCoverage;
import my.edu.umk.pams.account.identity.model.AcCoverageImpl;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.service.IdentityService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;


@JGivenStage
public class WhenIAddASponsorDetails extends Stage<WhenIAddASponsorDetails>{
	
	private static final Logger LOG = LoggerFactory.getLogger(WhenIAddASponsorDetails.class);
	
    @Autowired
    private IdentityService identityService;
    
    @Autowired
    private AccountService accountService;
    
    @ProvidedScenarioState
	private AcSettlement settlement;
    
	@ProvidedScenarioState
	private AcCoverage coverage;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ExpectedScenarioState
	private AcSponsor sponsor;
	
	public WhenIAddASponsorDetails() {
		coverage = new AcCoverageImpl();
	}

	@As("I add sponsor coverage")
	public WhenIAddASponsorDetails I_add_sponsor_with_coverages() {
		
		coverage = new AcCoverageImpl();
		coverage.setSponsor(sponsor);
    	coverage.setChargeCode(accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79321"));
    	
    	AcCoverage coverage2 = new AcCoverageImpl();
    	coverage2.setSponsor(sponsor);
    	coverage2.setChargeCode(accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79331"));
    	identityService.addCoverage(sponsor, coverage2);

		boolean hasCoverage = identityService.hasCoverage(sponsor);
		Assert.isTrue(hasCoverage, "Sponsor has coverage");
    	
    	return self();
	}
	
	

}
