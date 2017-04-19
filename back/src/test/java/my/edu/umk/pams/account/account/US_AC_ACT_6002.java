package my.edu.umk.pams.account.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenICanViewStudentCharges;
import my.edu.umk.pams.account.account.stage.WhenWantToListAllCompoundStudent;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmSecurity;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;
@Issue("PAMSU-92")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Account")
@As("As a Security, I want to list all compound student so that I can view student's compound charges")
public class US_AC_ACT_6002 extends SpringScenarioTest<GivenIAmSecurity, WhenWantToListAllCompoundStudent, ThenICanViewStudentCharges> {

	
	private static final String MATRIC_NO = "A17P001";

	@Test
	@Rollback(false)
	public void scenario0() {
		given().I_am_security();
		when().I_want_to_list_all_compound_student_$(MATRIC_NO);
		then().I_can_view_student_academic_charges();
	}

}


	 
