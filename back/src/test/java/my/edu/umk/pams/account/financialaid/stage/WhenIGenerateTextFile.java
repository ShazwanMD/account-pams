package my.edu.umk.pams.account.financialaid.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class WhenIGenerateTextFile extends Stage<WhenIGenerateTextFile>{

    @Autowired
    private IdentityService identityService;
    
    @ProvidedScenarioState
    AcSponsor sponsor;
    
    @ExpectedScenarioState
    AcStudent student;

    @ExpectedScenarioState
    private String matricNo;
    
    @ExpectedScenarioState
    private String sponsorNo;

    @As("I generate text file to bank")
    public WhenIGenerateTextFile I_generate_text_file_to_bank(String sponsorNo){
    	
    	sponsor = identityService.findSponsorBySponsorNo(sponsorNo);
    	
    	

    	return self();
    }
}
