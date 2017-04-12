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
import my.edu.umk.pams.account.financialaid.stage.ThenICanGenerateTextFile;
import my.edu.umk.pams.account.financialaid.stage.ThenICanStartSettlementProcess;
import my.edu.umk.pams.account.financialaid.stage.WhenIGenerateSponsorshipReceipt;
import my.edu.umk.pams.account.financialaid.stage.WhenIGetListOfDeductions;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Issue("PAMSU-65")
@Submodule("Financial Aid")
@As("As a Bursary, I want to generate text file with total deduction to submit to BIMB so that I can complete payment data")
public class US_AC_FNA_1005 extends SpringScenarioTest<GivenIAmBursary, WhenIGetListOfDeductions, ThenICanGenerateTextFile>{

	private static final String SPONSOR_NO = "HLP";
	
	@Test
	@Rollback(false)
	public void testGroupSponsorByStudent() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_get_list_of_deductions_$(SPONSOR_NO);
		then().I_can_generate_text_file();
	}
}
