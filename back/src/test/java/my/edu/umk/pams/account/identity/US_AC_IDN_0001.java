package my.edu.umk.pams.account.identity;

import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.billing.stage.ThenStudentAccountIsCharged;
import my.edu.umk.pams.account.billing.stage.WhenIssueInvoice;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_IDN_0001 extends SpringScenarioTest<GivenIAmBursary, WhenIssueInvoice, ThenStudentAccountIsCharged> {

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
