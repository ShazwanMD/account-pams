package my.edu.umk.pams.account.marketing;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.billing.stage.ThenUpdatePaymentAmount;
import my.edu.umk.pams.account.billing.stage.WhenReduceStudentPaymentBasedOnWaiver;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
@Issue("PAMSU-9")
@As("As a Bursary, I want to reduce student payment based on waiver promo code so that I can update the payment amount")
public class US_AC_MKG_4001 extends SpringScenarioTest<GivenIAmBursary, WhenReduceStudentPaymentBasedOnWaiver, ThenUpdatePaymentAmount> {

    @Test
    @Rollback
    public void testScenario1() {
        given().I_am_a_bursary_in_current_academic_session();
        when().Reduce_student_payment_based_on_waiver();
        then().Update_payment_amount();
    }
}
