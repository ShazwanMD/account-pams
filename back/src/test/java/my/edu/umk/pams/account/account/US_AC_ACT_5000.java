package my.edu.umk.pams.account.account;

import org.junit.Test;

/** As a Academic (MGSEB/CPS)
 * I want to register student compound bill
 * so that the compound details are record
**/

import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.account.stage.ThenCompoundDetailsAreRecord;
import my.edu.umk.pams.account.account.stage.WhenIRegisterStudentCompoundBill;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmCPSAdministrator;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Account")
@As("Academic (MGSEB/PPS), I want to register student compound bill so that the compound details are record. ")
public class US_AC_ACT_5000 extends SpringScenarioTest<GivenIAmCPSAdministrator, WhenIRegisterStudentCompoundBill, ThenCompoundDetailsAreRecord> {

	public static final String MATRIC_NO = "A17P001";

	@Test
	@Rollback
	public void scenario1() {
		given().I_am_a_CPS_administrator_in_current_academic_session()
				.and().I_pick_a_student_$(MATRIC_NO);
		when().register_student_compound_bill();
		then().compound_details_are_record();
	}
}
