package my.edu.umk.pams.account.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenViewStudentCompoundCharges;
import my.edu.umk.pams.account.account.stage.WhenListAllCompoundStudent;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmStudentAffair;
import my.edu.umk.pams.bdd.tags.Issue;


@Issue("PAMSU-50")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@As("As a Student Affair, I want to list all compound student so that I can view student's compound charges")
public class US_AC_ACT_5002 extends SpringScenarioTest<GivenIAmStudentAffair, WhenListAllCompoundStudent, ThenViewStudentCompoundCharges>{
	
	@Test
	@Rollback
	public void testScenario0() {
		given().I_am_student_affair();
		when().I_want_to_list_all_compound_student();
		then().I_can_view_student_compound_charges();
	}
}
