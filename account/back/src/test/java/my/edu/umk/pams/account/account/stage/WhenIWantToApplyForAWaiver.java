package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcStudent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;


@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class WhenIWantToApplyForAWaiver extends Stage<WhenIWantToApplyForAWaiver> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenIWantToApplyForAWaiver.class);

    @Autowired
    private AccountService accountService;

    @ExpectedScenarioState
    private AcAcademicSession academicSession;

    @ExpectedScenarioState
    private AcStudent student;

    @ExpectedScenarioState
    private AcAccount account;

    @ProvidedScenarioState
    private AcAccountCharge accountCharge;

    public WhenIWantToApplyForAWaiver I_want_to_apply_for_a_waiver() {
        academicSession = accountService.findCurrentAcademicSession();

        // accountCharge
        //todo: mana table waiver application???
        
        
        return self();
    }


}
