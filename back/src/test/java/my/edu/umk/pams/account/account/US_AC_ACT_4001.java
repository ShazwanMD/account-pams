package my.edu.umk.pams.account.account;
/*
 * As a Students, 
 * I want to view fees statement and charges  // When_I_want_to_view_charges
 * so that I can make payment  //Then_I_can_make_payment
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenICanMakePayment;
import my.edu.umk.pams.account.account.stage.WhenIWantToViewFeesAndChargesForAStudent;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmParent;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_4001 extends SpringScenarioTest<GivenIAmParent, WhenIWantToViewFeesAndChargesForAStudent, ThenICanMakePayment> {
	@Before
	public void before() {
	}

	@After
	public void after() {
	}

	@Test
	@Rollback
	public void testScenario0() {
		// Given
		given().I_am_a_parent_for_a_student_in_current_academic_session() ;
		// When
		when().I_want_to_view_fees_and_charges_for_a_student();
		// Then
		then().I_can_make_payment();
	}
}
