package my.edu.umk.pams.account.financialaid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.financialaid.stage.ThenICanMakeSureFeesPayment;
import my.edu.umk.pams.account.financialaid.stage.WhenIUpdateStudentStatus;
import my.edu.umk.pams.bdd.stage.GivenIAmMGSEBAdministrator;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@As("As an Academic, I want to update student status, so that I can make sure student fee payment")
public class US_AC_FNA_4000 extends SpringScenarioTest<GivenIAmMGSEBAdministrator, WhenIUpdateStudentStatus, ThenICanMakeSureFeesPayment>{


	private static final String MATRIC_NO = "A17P002";
	
	@Test
	@Rollback(false)
	public void scenario1() {

		given().I_am_a_MGSEB_administrator_in_current_academic_session();
		when().I_want_to_update_student_status_$(MATRIC_NO);
		then().I_want_to_make_sure_fess_payment();
	}
}