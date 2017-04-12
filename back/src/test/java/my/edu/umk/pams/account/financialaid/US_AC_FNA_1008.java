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
import my.edu.umk.pams.account.financialaid.stage.WhenIWantToListStudentStatus;
import my.edu.umk.pams.bdd.stage.GivenIAmMGSEBAdministrator;
import my.edu.umk.pams.bdd.tags.Submodule;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Financial Aid")
@As("As a Bursary, I want to receive active student list from Academic so that student list can be submit to KPT for KADS1M purposes.")
public class US_AC_FNA_1008 extends SpringScenarioTest<GivenIAmMGSEBAdministrator, WhenIWantToListStudentStatus, ThenICanMakeSureFeesPayment>{

	@Test
	@Rollback(false)
	public void scenario1() {

		given().I_am_a_MGSEB_administrator_in_current_academic_session();
		when().list_student_status();
		then().I_want_to_make_sure_fess_payment();
	}
}
