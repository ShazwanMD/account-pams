package my.edu.umk.pams.account.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenCanViewStudentCharges;
import my.edu.umk.pams.account.account.stage.WhenListStudentChargesOfTypeCompoundByAccount;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
/*
 * As a Bursary, 
 * I want to list student charges of type compound by account,  
 * so that I can view student's compound charges 
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_2005 extends SpringScenarioTest<GivenIAmBursary, WhenListStudentChargesOfTypeCompoundByAccount, ThenCanViewStudentCharges>{
	
	private static final String MATRIC_NO = "A17P001";
	private static final String CODE = "TMGSEB-MBA-00-H79321";
	
	@Test
    @Rollback(false)
    public void scenario1() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_want_to_list_student_charges_of_type_compound_by_account_$(MATRIC_NO, CODE);
		then().I_can_view_student_compound_charges_$(CODE);
    }
}
