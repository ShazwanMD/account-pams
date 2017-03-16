package my.edu.umk.pams.account.account;
/*
 * As a Student Affair,  
 * I want to register student compound bill 
 * so that the compound details are record. 
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

import my.edu.umk.pams.account.account.stage.ThenStudentAffairCompoundDetailsAreRecord;
import my.edu.umk.pams.account.account.stage.WhenIWantToRegisterStudentCompoundBill;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
//import my.edu.umk.pams.bdd.stage.GivenIAmStudentAffair;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_3000 extends
		SpringScenarioTest<GivenIAmBursary, WhenIWantToRegisterStudentCompoundBill, ThenStudentAffairCompoundDetailsAreRecord> {
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
		given().I_am_a_bursary_in_current_academic_session() ;
		// When
		when().I_Register_Compound_Bill();
		// Then
		then().student_charge_details_is_record();
	}
}
