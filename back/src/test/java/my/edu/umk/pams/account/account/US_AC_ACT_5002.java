package my.edu.umk.pams.account.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenViewStudentCompoundCharges;
import my.edu.umk.pams.account.account.stage.WhenListAllCompoundStudent;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmStudentAffair;
import my.edu.umk.pams.bdd.tags.Issue;
@Issue("PAMSU-50")
/*
 * As a Student Affair,
 * I want to list all compound student 
 * so that I can view student's compound charges
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_5002 extends SpringScenarioTest<GivenIAmStudentAffair, WhenListAllCompoundStudent, ThenViewStudentCompoundCharges>{
	private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_5002.class);
	
	@Test
	@Rollback
	public void testScenario0() {
		given().I_am_student_affair();
		when().I_want_to_list_all_compound_student();
		then().I_can_view_student_compound_charges();
	}
}
