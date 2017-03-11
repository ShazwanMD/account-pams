package my.edu.umk.pams.account.identity;

import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.billing.stage.ThenAccountIsInvoiced;
import my.edu.umk.pams.account.billing.stage.WhenIssueInvoice;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.CoreNext;
import my.edu.umk.pams.bdd.tags.FeatureNext;
import my.edu.umk.pams.bdd.tags.Submodule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author PAMS
 */
@Submodule("Identity")
@CoreNext
@FeatureNext("Verification")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_IDN_0001 extends SpringScenarioTest<GivenIAmBursary, WhenIssueInvoice, ThenAccountIsInvoiced> {

    private static final Logger LOG = LoggerFactory.getLogger(US_AC_IDN_0001.class);

    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    @Rollback(true)
    @Pending
    public void testScenario1() {
    }
}
