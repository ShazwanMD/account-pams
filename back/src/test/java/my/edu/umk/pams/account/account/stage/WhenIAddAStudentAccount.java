package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcStudentImpl;
import my.edu.umk.pams.account.identity.service.IdentityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

/**
 * Created by PAMS on 3/8/2017.
 */
@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class WhenIAddAStudentAccount extends Stage<WhenIAddAStudentAccount> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenIAddAStudentAccount.class);

    @ExpectedScenarioState
    private AcStudent student;

    @Autowired
    @ProvidedScenarioState
    private AccountService accountService;

    @ProvidedScenarioState
    AcAccount account;

    @ProvidedScenarioState
    Long accountId;

    public WhenIAddAStudentAccount I_add_a_student_account() {

        // create account
        account = new AcAccountImpl();
        account.setCode(student.getMatricNo());
        account.setDescription(student.getMatricNo() + ";" + student.getEmail());
        account.setActor(student);
        accountService.saveAccount(account);

        accountId = account.getId();
        final String entityName = account.getClass().getSimpleName();
        Assert.notNull(accountId, entityName + " must have Id");

        return self();
    }

}
