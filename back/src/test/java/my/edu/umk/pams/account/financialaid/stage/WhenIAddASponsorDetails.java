package my.edu.umk.pams.account.financialaid.stage;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcCoverage;
import my.edu.umk.pams.account.identity.model.AcCoverageImpl;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcSponsorshipImpl;
import my.edu.umk.pams.account.identity.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;

@JGivenStage
public class WhenIAddASponsorDetails extends Stage<WhenIAddASponsorDetails>{
	
    @Autowired
    private IdentityService identityService;
    
    @Autowired
    private AccountService accountService;
    
    @ProvidedScenarioState
    AcSponsor sponsor;
    
	private static final Logger LOG = LoggerFactory.getLogger(WhenIAddASponsorDetails.class);


	@Autowired
	private SessionFactory sessionFactory;


	@ProvidedScenarioState
	AcCoverage coverage;

	@ProvidedScenarioState
	Long coverageId;

	public WhenIAddASponsorDetails() {
		coverage = new AcCoverageImpl();
	}

	@As("I add sponsor coverage")
	public WhenIAddASponsorDetails I_add_sponsor_$_with_coverage(String sponsorNo) {

		sponsor = identityService.findSponsorBySponsorNo(sponsorNo);
		
		coverage.setSponsor(sponsor);
    	coverage.setChargeCode(accountService.findChargeCodeById(2L));
    	identityService.addCoverage(sponsor, coverage);

    	return self();
		
	}

}
