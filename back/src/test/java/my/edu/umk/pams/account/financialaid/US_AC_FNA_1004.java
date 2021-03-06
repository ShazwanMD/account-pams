package my.edu.umk.pams.account.financialaid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.financialaid.stage.ThenICanCompleteDataSponsor;
import my.edu.umk.pams.account.financialaid.stage.WhenIWantUpdateAmount;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;

@Issue("PAMSU-64")
@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Financial Aid")
@As("As a Bursary, I want to update student outstanding amount and short term loan amount (STL) so that I can complete Sponsor data")
public class US_AC_FNA_1004 extends SpringScenarioTest<GivenIAmBursary, WhenIWantUpdateAmount, ThenICanCompleteDataSponsor>{

	@Test
	@Rollback(false)
	public void scenario1() {

		given().I_am_a_bursary_in_current_academic_session();
		when().update_student_outstanding_amount();
		then().complete_data_sponsor();
	}
	
	@Test
	@Rollback(false)
	public void scenario2() {

		given().I_am_a_bursary_in_current_academic_session();
		when().update_short_term_loan_amount();
		then().complete_data_sponsor();
	}
}
