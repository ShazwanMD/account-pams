package my.edu.umk.pams.account.financialaid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenICanCheckMyPaymentStatus;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.financialaid.stage.WhenIViewSponsorshipInformation;
import my.edu.umk.pams.bdd.stage.GivenIAmStudent;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@As("As a student, I want to view information on my sponsorship so that I can check my fee status")
public class US_AC_FNA_3000 extends SpringScenarioTest<GivenIAmStudent, WhenIViewSponsorshipInformation, ThenICanCheckMyPaymentStatus>{


	private static final String MATRIC_NO = "A17P002";
	
	@Test
	@Rollback(false)
	public void testViewSponsorInformation() {

		given().I_am_a_student_in_current_academic_session();
		when().I_want_to_view_sponsorship_information_$(MATRIC_NO);
		then().I_can_check_my_payment_status();
	}
}
