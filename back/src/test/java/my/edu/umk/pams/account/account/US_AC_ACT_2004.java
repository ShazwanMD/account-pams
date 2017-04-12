package my.edu.umk.pams.account.account;
/*
 * As a Bursary, 
 * I want to update student compound payment 
 * so that payment can be process .
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenPaymentCanProcess;
import my.edu.umk.pams.account.account.stage.WhenUpdateStudentCompound;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;


@Issue("PAMSU-37")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Account")
@As("As a Bursary, I want to update student compound payment so that payment can be process.")
public class US_AC_ACT_2004 extends SpringScenarioTest<GivenIAmBursary, WhenUpdateStudentCompound, ThenPaymentCanProcess> {
	
	@Test
	@Rollback
	public void testScenario1() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_want_to_update_student_compound_payment();
		then().Payment_can_be_process();

	}
}
