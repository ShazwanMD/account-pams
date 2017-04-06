package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;


@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class ThenMyChargeIsReduced extends Stage<ThenMyChargeIsReduced> {

    private static final Logger LOG = LoggerFactory.getLogger(ThenMyChargeIsReduced.class);

    @ExpectedScenarioState
    private AcWaiverApplication waiverApplication;

    public ThenMyChargeIsReduced my_charge_is_reduced(){


        return self();
    }
}
