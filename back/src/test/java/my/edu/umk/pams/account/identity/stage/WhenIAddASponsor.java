package my.edu.umk.pams.account.identity.stage;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.account.model.AcAcademicCharge;
import my.edu.umk.pams.account.account.model.AcAcademicChargeImpl;
import my.edu.umk.pams.account.identity.dao.AcSponsorDao;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorImpl;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.security.service.SecurityService;

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

    @As("I add sponsor user PTPTN")
    public WhenIAddASponsor I_add_a_sponsor_user() {

        AcSponsor sponsor = new AcSponsorImpl();
        sponsor.setIdentityNo("123456");
        sponsor.setName("PTPTN");
        identityService.saveSponsor(sponsor);
        
        return self();
    }

}
