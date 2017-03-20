package my.edu.umk.pams.account.identity.stage;

import static org.junit.Assert.assertTrue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @ExpectedScenarioState
    private IdentityService identityService;

    @ExpectedScenarioState
    private String sponsorName;
    
    @ProvidedScenarioState
    AcSponsor sponsor;

	@As("the sponsor is added")
    public ThenTheSponsorIsAdded the_sponsor_user_is_added(){


        return self();
    
    }
	
	@As("the sponsor account is added")
    public ThenTheSponsorIsAdded the_sponsor_account_is_added(){


        return self();
      
   
    }
}
