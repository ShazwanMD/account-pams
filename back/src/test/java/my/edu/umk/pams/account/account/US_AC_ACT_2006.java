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
import my.edu.umk.pams.account.account.stage.WhenIFillInStudentCompound;
import my.edu.umk.pams.account.account.stage.WhenListStudentCharges;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;


@Issue("PAMSU-24")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Account")
@As("As a Bursary, I want to list student charges of type academic by account, so that I can view student's academic charges")
public class US_AC_ACT_2006 extends SpringScenarioTest<GivenIAmBursary, WhenIFillInStudentCompound, ThenICanViewStudentCharges> {

	private static final String MATRIC_NO = "A17P001";
	private static final String CODE = "TMGSEB-MBA-00-H79322";

	@Test
	@Rollback
	public void testScenario1() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_fill_in_student_compound_$(MATRIC_NO, CODE);
	    addStage( WhenListStudentCharges.class).and().I_want_to_list_student_charges_of_type_academic_by_account_$(MATRIC_NO, CODE);
		then().I_can_view_student_academic_charges();
	}
	
	@Test
	@Rollback
	public void testScenario2() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_fill_in_student_compound_$(MATRIC_NO, CODE);
	    addStage( WhenListStudentCharges.class).and().I_want_to_list_student_charges_of_type_security_by_account_$(MATRIC_NO, CODE);
		then().I_can_view_student_security_charges();
	}
	
	@Test
	@Rollback
	public void testScenario3() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_fill_in_student_compound_$(MATRIC_NO, CODE);
	    addStage( WhenListStudentCharges.class).and().I_want_to_list_student_charges_of_type_student_affair_by_account_$(MATRIC_NO, CODE);
		then().I_can_view_student_student_affair_charges();
	}
}
