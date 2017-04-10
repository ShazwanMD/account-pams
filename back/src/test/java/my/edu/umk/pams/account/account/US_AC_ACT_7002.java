package my.edu.umk.pams.account.account;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.account.stage.ThenCanViewStudentsCompoundCharges;
import my.edu.umk.pams.account.account.stage.WhenIListAllCompoundStudent;
import my.edu.umk.pams.account.account.stage.WhenIaddstudentcompound;
import my.edu.umk.pams.account.account.stage.WhenListStudentCharges;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmCPSAdministrator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@As("As Academic (MGSEB/CPS), I want to list all compound student so that I can view student's compound charges")
  public class US_AC_ACT_7002 extends SpringScenarioTest<GivenIAmCPSAdministrator, WhenIaddstudentcompound, ThenCanViewStudentsCompoundCharges> {

	
	private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_7002.class);

	private static final String MATRIC_NO = "A17P001";
	
	@Test
	@Rollback(false)
	public void testScenario1() {
		given().I_am_a_CPS_administrator_in_current_academic_session();
		when().I_add_student_compound_$(MATRIC_NO);
		addStage( WhenIListAllCompoundStudent.class).and().I_want_to_list_all_compound_student_of_type_academic_$(MATRIC_NO);
		then().can_view_students_compound_charges_of_type_academic_$(MATRIC_NO);
	}
	
	@Test
	@Rollback(false)
	public void testScenario2() {
		given().I_am_a_CPS_administrator_in_current_academic_session();
		when().I_add_student_compound_$(MATRIC_NO);
		addStage( WhenIListAllCompoundStudent.class).and().I_want_to_list_all_compound_student_of_type_security_$(MATRIC_NO);
		then().can_view_students_compound_charges_of_type_security_$(MATRIC_NO);
	}
	
	@Test
	@Rollback(false)
	public void testScenario3() {
		given().I_am_a_CPS_administrator_in_current_academic_session();
		when().I_add_student_compound_$(MATRIC_NO);
		addStage( WhenIListAllCompoundStudent.class).and().I_want_to_list_all_compound_student_of_type_student_affair_$(MATRIC_NO);
		then().can_view_students_compound_charges_of_type_student_affair_$(MATRIC_NO);
	}
	
}
