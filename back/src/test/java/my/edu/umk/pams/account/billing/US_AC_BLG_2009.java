package my.edu.umk.pams.account.billing;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.billing.stage.ThenUpdatePaymentAmount;
import my.edu.umk.pams.account.billing.stage.WhenReduceStudentPaymentBasedOnWaiver;
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
public class US_AC_BLG_2009 extends SpringScenarioTest<GivenIAmBursary, WhenReduceStudentPaymentBasedOnWaiver, ThenUpdatePaymentAmount> {

    private static final Logger LOG = LoggerFactory.getLogger(US_AC_BLG_2009.class);

    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    @Rollback(true)
    public void testScenario1() {
        given().I_am_a_bursary_in_current_academic_session();
        when().Reduce_student_payment_based_on_waiver();
        then().Update_payment_amount();
    }
}
