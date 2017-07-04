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
import my.edu.umk.pams.account.financialaid.stage.ThenICanUseAsAProof;
import my.edu.umk.pams.account.financialaid.stage.WhenIGenerateSponsorshipReceipt;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;


@Issue("PAMSU-69")
@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Financial Aid")
@As("As a Bursary, I want to generate sponsorship receipt to Sponsor so that it can be use as a proof of sponsorship payment")
public class US_AC_FNA_1006 extends SpringScenarioTest<GivenIAmBursary, WhenIGenerateSponsorshipReceipt, ThenICanUseAsAProof>{

	private static final String SPONSOR_NO = "HLP";
	
	@Test
	@Rollback(false)
	public void scenario1() {
		given().I_am_a_bursary_in_current_academic_session()
				.and().I_pick_a_sponsor_with_sponsor_no_$(SPONSOR_NO);
		when().I_want_to_generate_sponsorship_receipt_to_sponsor$();
		then().I_can_be_use_as_a_proof();
	}
}
