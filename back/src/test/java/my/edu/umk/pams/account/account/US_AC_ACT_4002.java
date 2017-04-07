package my.edu.umk.pams.account.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenMyChildCanRegisterNewSubject;
import my.edu.umk.pams.account.account.stage.WhenIWantToPayFeesAndChargesForAStudent;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmParent;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Pending
@As("As Parents, I want to pay student fees and charges so that I can allow my son or daughter to register new subject")
public class US_AC_ACT_4002 extends SpringScenarioTest<GivenIAmParent, WhenIWantToPayFeesAndChargesForAStudent, ThenMyChildCanRegisterNewSubject> {

	@Test
	@Rollback
	public void testScenario0() {
		given().I_am_a_parent_for_a_student_in_current_academic_session() ;
		when().I_want_to_pay_fees_and_charges_for_a_student();
		then().My_child_can_register_new_subject();
	}
}
