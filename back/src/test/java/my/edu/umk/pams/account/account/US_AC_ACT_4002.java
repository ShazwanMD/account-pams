package my.edu.umk.pams.account.account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenMyChildCanRegisterNewSubject;
import my.edu.umk.pams.account.account.stage.WhenIWantToPayFeesAndChargesForAStudent;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmParent;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_4002 extends SpringScenarioTest<GivenIAmParent, WhenIWantToPayFeesAndChargesForAStudent, ThenMyChildCanRegisterNewSubject> {
	@Before
	public void before() {
	}

	@After
	public void after() {
	}

	@Test
	@Rollback
	public void testScenario0() {
		given().I_am_a_parent_for_a_student_in_current_academic_session() ;
		when().I_want_to_pay_fees_and_charges_for_a_student();
		then().My_child_can_register_new_subject();
	}
}
