package my.edu.umk.pams.account.identity.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.service.IdentityService;


@JGivenStage
public class ThenTheSponsorIsAdded extends Stage<ThenTheSponsorIsAdded> {

    private static final Logger LOG = LoggerFactory.getLogger(ThenTheSponsorIsAdded.class);

    @Autowired
    private IdentityService identityService;

    @ExpectedScenarioState
    private String sponsorName;
    
    @ProvidedScenarioState
    AcSponsor sponsor;

	@As("the sponsor is added")
    public ThenTheSponsorIsAdded the_sponsor_user_is_added(){

		boolean hasSponsor = identityService.hasUser(sponsor);
		Assert.isTrue(hasSponsor, "User sponsor not available");

        return self();
    }
	
	@As("the sponsor account is added")
    public ThenTheSponsorIsAdded the_sponsor_account_is_added(){

		boolean hasSponsor = identityService.hasUser(sponsor);
		Assert.isTrue(hasSponsor, "User sponsor not available");
		
        return self();
      
   
    }
}
