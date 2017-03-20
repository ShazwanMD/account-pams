package my.edu.umk.pams.account.identity.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcCoverage;
import my.edu.umk.pams.account.identity.model.AcCoverageImpl;
import my.edu.umk.pams.account.identity.model.AcSponsor;
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
    
    public WhenIAddASponsorDetails(){
    }
    
    @As("I add sponsor coverage")
    public WhenIAddASponsorDetails I_add_sponsor_$_with_coverage(String sponsorNo){
    	sponsor = identityService.findSponsorBySponsorNo(sponsorNo);

        AcCoverage coverage = new AcCoverageImpl();
    	coverage.setSponsor(sponsor);
    	coverage.setChargeCode(accountService.findChargeCodeById((long)7));
    	identityService.addCoverage(sponsor, coverage);
    	
    	return self();
    }
}
