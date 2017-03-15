package my.edu.umk.pams.account.identity.stage;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.identity.dao.AcSponsorDao;
import my.edu.umk.pams.account.identity.dao.AcStudentDao;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcStudent;
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
}
