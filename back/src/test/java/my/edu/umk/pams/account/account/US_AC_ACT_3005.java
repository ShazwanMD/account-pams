package my.edu.umk.pams.account.account;
/*
 * As a Students,
 * As a Students, I want to print slip after payment  // I_want_to_print_my_payment_slip
 * so that I can keep the slip as a proove // I_can_keep_my_payment_details
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenICanKeepMyPaymentDetails;
import my.edu.umk.pams.account.account.stage.WhenIWantToPrintMyPaymentSlip;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmStudent;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;
@Issue("PAMSU-44")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Account")
@As("As a Students, I want to print slip after payment so that I can keep the slip as a proof")
public class US_AC_ACT_3005 extends SpringScenarioTest<GivenIAmStudent, WhenIWantToPrintMyPaymentSlip, ThenICanKeepMyPaymentDetails> {

	@Test
	@Rollback
	public void testScenario0() {
		given().I_am_a_student_in_current_academic_session() ;
		when().I_want_to_print_my_payment_slip();
		then().I_can_keep_my_payment_details();
	}
}
