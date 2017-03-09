package my.edu.umk.pams.account.account;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.account.stage.GivenIAmBursary;
import my.edu.umk.pams.account.account.stage.ThenAccountIsCharged;
import my.edu.umk.pams.account.account.stage.WhenIAddAccountCharge;
import my.edu.umk.pams.account.config.TestAppConfiguration;
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
 * As a Bursary, I want to list student charges by account,  so that I can view student's charges
 *
 * @author PAMS
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_0004 extends SpringScenarioTest<GivenIAmBursary, WhenIAddAccountCharge, ThenAccountIsCharged> {

    private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_0004.class);

    @Before
    public void before() {
    }

    @After
    public void after() {
    }


    /**
     * As a Bursary in current academic session
     *  I want to list student charges by account,
     *      so that I can view student's charges
     *
     * As a Bursary in current academic session
     *  I want to add charges to student
     *      so that student is charged
     */
    @Test
    @Rollback(true)
    public void testUserStory() {
        given().I_am_a_bursary_in_current_academic_session();
        when().I_create_student_account_and_add_account_charge();
        then().student_account_is_charged();
    }
}

