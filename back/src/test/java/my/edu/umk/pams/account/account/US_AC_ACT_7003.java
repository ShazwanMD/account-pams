package my.edu.umk.pams.account.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenCanGiveStudentInformationToBursary;
import my.edu.umk.pams.account.account.stage.WhenIWantUpdateStudentInformation;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmCPSAdministrator;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@As("As Academic (MGSEB/CPS), I want to update student information so that I can give student information to Bursary")
public class US_AC_ACT_7003 extends SpringScenarioTest<GivenIAmCPSAdministrator, WhenIWantUpdateStudentInformation, ThenCanGiveStudentInformationToBursary> {

	@Test
	@Rollback(false)
	public void testScenario1() {
		given().I_am_a_CPS_administrator_in_current_academic_session();
		when().I_want_update_student_information();
		then().can_give_student_information_to_Bursary();
	}

}
