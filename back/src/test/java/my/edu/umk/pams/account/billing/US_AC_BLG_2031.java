package my.edu.umk.pams.account.billing;

import my.edu.umk.pams.account.billing.stage.ThenICanPrintReportByFaculty;
import my.edu.umk.pams.account.billing.stage.WhenGenerateReportByFaculty;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
@Issue("PAMSU-31")
/*
 *As a Bursary, 
 *I want to generate report by faculty 
 *so that I can print report
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_BLG_2031
		extends
		SpringScenarioTest<GivenIAmBursary, WhenGenerateReportByFaculty, ThenICanPrintReportByFaculty> {

	private static final Logger LOG = LoggerFactory
			.getLogger(US_AC_BLG_2031.class);
	private static final String MATRIC_NO = "A17P001";
	@Issue("PAMSU-31")
	@Test
	@Rollback
	public void testScenario1() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_want_to_generate_report_by_faculty_$(MATRIC_NO);
		then().I_can_print_report();
	}
}
