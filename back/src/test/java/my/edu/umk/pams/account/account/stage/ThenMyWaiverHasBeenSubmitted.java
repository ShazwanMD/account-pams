package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;


@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class ThenMyWaiverHasBeenSubmitted extends Stage<ThenMyWaiverHasBeenSubmitted> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenIAddAStudent.class);

    @ExpectedScenarioState
    private AcWaiverApplication waiverApplication;

    public ThenMyWaiverHasBeenSubmitted I_my_waiver_has_been_submitted(){


        return self();
    }
}
