package my.edu.umk.pams.account.account;
/*
 * As a Student Affair,  
 * I want to register student compound bill 
 * so that the compound details are record. 
 */

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

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_3000 extends SpringScenarioTest<GivenIAmBursary, WhenIWantToRegisterStudentCompoundBill, ThenStudentAffairCompoundDetailsAreRecord> {
	@Test
	@Rollback
	public void testScenario0() {
		given().I_am_a_bursary_in_current_academic_session() ;
		when().I_Register_Compound_Bill();
		then().student_charge_details_is_record();
	}
}
