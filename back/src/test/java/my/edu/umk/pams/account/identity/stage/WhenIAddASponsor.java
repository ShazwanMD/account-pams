package my.edu.umk.pams.account.identity.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.identity.model.AcActorType;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorImpl;
import my.edu.umk.pams.account.identity.model.AcSponsorType;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.identity.service.IdentityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@JGivenStage
public class WhenIAddASponsor extends Stage<WhenIAddASponsor>{


    private static final Logger LOG = LoggerFactory.getLogger(WhenIAddASponsor.class);

    @ExpectedScenarioState
    private AcUser currentUser;

    @Autowired
    private IdentityService identityService;
    
    @ProvidedScenarioState
    AcSponsor sponsor;
    
    public WhenIAddASponsor(){
    	sponsor = new AcSponsorImpl();
    }

    @As("I add sponsor user account")
    public WhenIAddASponsor I_add_a_sponsor_user() {
        
        sponsor.setIdentityNo("SPNSR-" + System.currentTimeMillis());
        sponsor.setName("PTPTN");
        sponsor.setActorType(AcActorType.SPONSOR);
        sponsor.setPhone("037443355");
        sponsor.setFax("097445566");
        sponsor.setEmail("ptptn@ptptn.gov.my");
        sponsor.setCode("FA10000");
       
        identityService.saveSponsor(sponsor);
        
        return self();
    }
    
    @As("I add sponsor account")
    public WhenIAddASponsor I_add_a_sponsor_account() {

        sponsor.setCode("FA10000");
        sponsor.setAcSponsorType(AcSponsorType.DIRECT);
       
        identityService.saveSponsor(sponsor);
        
        return self();
    }

}
