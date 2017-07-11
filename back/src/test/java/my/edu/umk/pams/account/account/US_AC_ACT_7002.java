package my.edu.umk.pams.account.account;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenICanViewStudentCharges;
import my.edu.umk.pams.account.account.stage.WhenIListAllCompoundStudent;
import my.edu.umk.pams.account.account.stage.WhenAddStudentCompound;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmCPSAdministrator;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
@Issue("PAMSU-94")
@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Account")
@As("As Academic (MGSEB/CPS), I want to list all compound student so that I can view student's compound charges")
public class US_AC_ACT_7002
		extends SpringScenarioTest<GivenIAmCPSAdministrator, WhenAddStudentCompound, ThenICanViewStudentCharges> {

	private static final String MATRIC_NO = "A17P001";

	@Test
	@Rollback(false)
	public void testScenario1() {
		given().I_am_a_CPS_administrator_in_current_academic_session();
		when().I_add_student_compound_$(MATRIC_NO);
		addStage(WhenIListAllCompoundStudent.class).and()
				.I_want_to_list_all_compound_student_of_type_academic_$(MATRIC_NO);
		then().can_view_students_compound_charges_of_type_academic_$(MATRIC_NO);
	}

}
