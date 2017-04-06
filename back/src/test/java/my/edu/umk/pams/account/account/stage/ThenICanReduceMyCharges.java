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


@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class ThenICanReduceMyCharges extends Stage<ThenICanReduceMyCharges> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenIAddAStudent.class);

    @ExpectedScenarioState
    private AccountService accountService;

    @ExpectedScenarioState
    private Long accountId;

    public ThenICanReduceMyCharges I_can_reduce_my_charges(){

        AcAccount account = accountService.findAccountById(accountId);

        final String entityName = account.getClass().getSimpleName();
        Assert.notNull(account.getCode(), "No " + entityName + " found with code " + account.getCode());

        return self();
    }
}
