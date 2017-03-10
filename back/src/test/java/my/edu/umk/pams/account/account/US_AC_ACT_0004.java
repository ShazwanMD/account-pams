package my.edu.umk.pams.account.account;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
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

    private static final String STUDENT_NO = "ABC001";
    private static final String SESSION_CODE = "201720181";
    private static final double CHARGE_AMOUNT = 200.00;

    /**
     * TODO: As a Bursary in current academic session
     * I want to list student charges by account,
     * so that I can view student's charges
     */
    @Test
    public void testScenario0() {
        given().I_am_a_bursary_in_current_academic_session();
        when().I_show_charges_by_account_for_studentNo_$(STUDENT_NO);
        then().the_charges_for_the_student_are_listed();
    }

    /**
     * As a Bursary in current academic session,
     * I want to add charges to student
     * so that student is charged
     */
    @Test
    @Rollback(true)
    public void testScenario1() {
        given().I_am_a_bursary_in_current_academic_session();
        when().I_create_student_account_and_add_account_charge_of_$(CHARGE_AMOUNT);
        then().student_account_is_charged();
    }

    @Test
    @Rollback(true)
    public void testScenario2() {
        given().I_am_a_bursary_in_$_academic_session(SESSION_CODE);
        when().I_create_student_account_and_add_account_charge_of_$(CHARGE_AMOUNT);
        then().student_account_is_charged();
    }

    @Test
    @Rollback(true)
    public void testScenario3() {
        given().I_am_a_bursary_in_$_academic_session(SESSION_CODE);
        when().I_create_student_account_and_add_academic_charge();
        then().student_account_is_charged();
    }
}

