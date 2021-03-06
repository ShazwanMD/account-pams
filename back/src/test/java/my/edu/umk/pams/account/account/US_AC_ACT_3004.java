package my.edu.umk.pams.account.account;
/*
 * As a Students,
 * I want to view my slip of payment  // I_want_to_view_my_payment_slip
 * so that I can check my payment status // I_can_check_my_payment_status
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenICanCheckMyPaymentStatus;
import my.edu.umk.pams.account.account.stage.WhenIWantToViewMyPaymentSlip;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmStudent;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;
@Issue("PAMSU-43")
@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Account")
@As("As a student, I want to view my slip of payment so that I can check my payment status")
public class US_AC_ACT_3004 extends SpringScenarioTest<GivenIAmStudent, WhenIWantToViewMyPaymentSlip, ThenICanCheckMyPaymentStatus> {

	@Test
	@Rollback
	public void testScenario0() {
		given().I_am_a_student_in_current_academic_session() ;
		when().I_want_to_view_my_payment_slip();
		then().I_can_check_my_payment_status();
	}
}
