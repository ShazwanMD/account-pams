package my.edu.umk.pams.account.identity.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
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

    @ProvidedScenarioState
    private String sponsorNo;

    @ProvidedScenarioState
    private String sponsorName;

    @ProvidedScenarioState
    private Long sponsorId;

    @ExpectedScenarioState
    private AcUser currentUser;

    @Autowired
    private IdentityService identityService;
    
    @ProvidedScenarioState
    AcSponsor sponsor;

    @As("I add sponsor user account")
    public WhenIAddASponsor I_add_a_sponsor_user() {
        AcSponsor sponsor = new AcSponsorImpl();
        sponsor.setIdentityNo("SPNSR-" + System.currentTimeMillis());
        sponsor.setName("PTPTN");
        sponsor.setSponsorType(AcSponsorType.DIRECT);
        identityService.saveSponsor(sponsor);
        
        return self();
    }

}
