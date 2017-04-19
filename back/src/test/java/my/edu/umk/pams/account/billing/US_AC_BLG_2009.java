package my.edu.umk.pams.account.billing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.billing.stage.ThenICanPrintReport;
import my.edu.umk.pams.account.billing.stage.WhenIGenerateReport;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;
@Issue("PAMSU-9")

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Billing")
@As("As a Bursary, I want to generate report so that I can print report")
public class US_AC_BLG_2009 extends SpringScenarioTest<GivenIAmBursary, WhenIGenerateReport, ThenICanPrintReport> {

	private static final String PROGRAM_CODE = "FIAT/PHD/0001";
	
	@Test
	@Rollback(false)
	public void scenario1() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_generate_report_by_program$(PROGRAM_CODE);
		then().I_can_print_report();
	}
}
