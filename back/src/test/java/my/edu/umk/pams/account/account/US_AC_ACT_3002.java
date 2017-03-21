package my.edu.umk.pams.account.account;
/*
 * As a Students, 
 * I want to apply for a waiver // I_want_to apply_for_a_waiver
 * so that I can reduce my charges and fees amount // I_can_reduce_my_charges
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

import my.edu.umk.pams.account.account.stage.ThenICanReduceMyCharges;
import my.edu.umk.pams.account.account.stage.WhenIWantToApplyForAWaiver;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmStudent;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_3002 extends
		SpringScenarioTest<GivenIAmStudent, WhenIWantToApplyForAWaiver, ThenICanReduceMyCharges> {
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
		given().I_am_a_student_in_current_academic_session() ;
		// When
		when().I_want_to_apply_for_a_waiver();
		// Then
		then().I_can_reduce_my_charges();
	}
}
