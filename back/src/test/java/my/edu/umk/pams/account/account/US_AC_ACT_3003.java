package my.edu.umk.pams.account.account;
/*
 * As a Students,
 * I want to pay student fees and charges // I_want_to_pay_my_charges
 * so that I can register new subject and view my result  // I_can_view_my_result
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenICanViewMyResult;
import my.edu.umk.pams.account.account.stage.WhenIWantToPayMyCharges;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmStudent;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@As("As a Students, I want to pay student fees and charges so that I can register new subject and view my result")
public class US_AC_ACT_3003 extends SpringScenarioTest<GivenIAmStudent, WhenIWantToPayMyCharges, ThenICanViewMyResult> {

	@Test
	@Rollback
	public void testScenario0() {
		given().I_am_a_student_in_current_academic_session() ;
		when().I_want_to_pay_my_charges();
		then().I_can_view_my_result();
	}
}