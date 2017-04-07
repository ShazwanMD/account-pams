package my.edu.umk.pams.account.account;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.account.account.stage.ThenAccountIsCharged;
import my.edu.umk.pams.account.account.stage.WhenIAddAccountCharge;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.tags.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
@Issue(value = {"PAMSU-6"})
@As("As a Bursary in current academic session, I want to add charges to student so that student is charged")
public class US_AC_ACT_0004 extends
        SpringScenarioTest<GivenIAmBursary, WhenIAddAccountCharge, ThenAccountIsCharged> {

    private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_0004.class);

    private static final String MATRIC_NO = "A17P001";
    private static final String SESSION_CODE = "201720181";
    private static final double CHARGE_AMOUNT = 200.00;

    @Test
    @Rollback
    public void scenario1() {
        given().I_am_a_bursary_in_current_academic_session();
        when().I_pick_student_account_and_add_account_charge_of_$(MATRIC_NO, CHARGE_AMOUNT);
        then().student_account_is_charged_$(CHARGE_AMOUNT);
    }

    @Test
    @Rollback
    public void scenario2() {
        given().I_am_a_bursary_in_$_academic_session(SESSION_CODE);
        when().I_pick_student_account_and_add_account_charge_of_$(MATRIC_NO, CHARGE_AMOUNT);
        then().student_account_is_charged_$(CHARGE_AMOUNT);
    }

    @Test
    @Rollback
    public void scenario3() {
        given().I_am_a_bursary_in_$_academic_session(SESSION_CODE);
        when().I_pick_student_account_and_add_academic_charge(MATRIC_NO);
        then().student_account_is_charged_$(CHARGE_AMOUNT);
    }

    @Test
    @Ignore
    @Rollback
    public void flawedScenario() {
        given().I_am_a_bursary_in_$_academic_session(SESSION_CODE);
        when().I_pick_student_account_and_add_academic_charge(MATRIC_NO);
        then().the_charges_are_misplaced("INTENTIONALLY_BROKEN_METHOD");
    }
}

