package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

/**
 * Created by PAMS on 3/8/2017.
 */
@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class ThenTheAccountIsAdded extends Stage<ThenTheAccountIsAdded> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenIAddAStudent.class);

    @ExpectedScenarioState
    private AccountService accountService;

    @ExpectedScenarioState
    private Long accountId;

    public ThenTheAccountIsAdded the_student_account_is_added(){

        AcAccount account = accountService.findAccountById(accountId);

        final String entityName = account.getClass().getSimpleName();
        Assert.notNull(account.getId(), "No " + entityName + " found with id " + account.getId());
        Assert.notNull(account.getCode(), "No " + entityName + " found with code " + account.getCode());

        return self();
    }
}
