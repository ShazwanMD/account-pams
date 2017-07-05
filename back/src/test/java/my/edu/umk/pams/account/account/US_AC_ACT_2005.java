package my.edu.umk.pams.account.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenICanViewStudentCharges;
import my.edu.umk.pams.account.account.stage.WhenIFillInCompoundStudent;
import my.edu.umk.pams.account.account.stage.WhenListStudentChargesOfTypeCompoundByAccount;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;

@Issue("PAMSU-23")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Account")
@As("As a Bursary, I want to list student charges of type compound by account so that I can view student's compound charges")
public class US_AC_ACT_2005 extends SpringScenarioTest<GivenIAmBursary, WhenIFillInCompoundStudent, ThenICanViewStudentCharges> {

	private static final String MATRIC_NO = "A17P001";
	private static final String CODE = "TMGSEB-MBA-00-H79322";

	@Test
	@Rollback
	public void scenario1() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_fill_in_student_compound_$(MATRIC_NO, CODE);
		addStage(WhenListStudentChargesOfTypeCompoundByAccount.class).and().I_want_to_list_student_charges_of_type_compound_by_account_$(MATRIC_NO, CODE);
		then().I_can_view_student_academic_compound_charges_$(CODE);
	}
	
	@Test
	@Rollback
	public void scenario2() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_fill_in_student_compound_$(MATRIC_NO, CODE);
		addStage(WhenListStudentChargesOfTypeCompoundByAccount.class).and().I_want_to_list_student_charges_of_type_compound_by_account_$(MATRIC_NO, CODE);
		then().I_can_view_student_security_compound_charges_$(CODE);
	}
	
	@Test
	@Rollback
	public void scenario3() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_fill_in_student_compound_$(MATRIC_NO, CODE);
		addStage(WhenListStudentChargesOfTypeCompoundByAccount.class).and().I_want_to_list_student_charges_of_type_compound_by_account_$(MATRIC_NO, CODE);
		then().I_can_view_student_student_affair_compound_charges_$(CODE);
	}
	
}
