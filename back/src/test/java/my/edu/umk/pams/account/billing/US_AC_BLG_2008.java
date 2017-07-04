package my.edu.umk.pams.account.billing;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.billing.stage.ThenProcessThePayment;
import my.edu.umk.pams.account.billing.stage.WhenUpdateStudentPayment;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
@Issue("PAMSU-8")

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Billing")
@As("As a Bursary, i want to update student payment so that I can process the payment")
public class US_AC_BLG_2008 extends SpringScenarioTest<GivenIAmBursary, WhenUpdateStudentPayment, ThenProcessThePayment> {


    @Test
    @Rollback
    public void testScenario1() {
        given().I_am_a_bursary_in_current_academic_session();
        when().Update_student_payment();
        then().Process_the_payment();
    }
}
