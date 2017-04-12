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
import my.edu.umk.pams.account.financialaid.stage.ThenICanGenerateInvoiceForSponsor;
import my.edu.umk.pams.account.financialaid.stage.WhenIAddSettlementDetails;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Submodule;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Financial Aid")
@As("As a Bursary, I want to add settlement process ,so that I can generate sponsor Invoice")
public class US_AC_FNA_1009 extends SpringScenarioTest<GivenIAmBursary, WhenIAddSettlementDetails, ThenICanGenerateInvoiceForSponsor>{

	private static final String SPONSOR_NO = "HLP";

	@Test
	@Rollback(false)
	public void scenario1() {
		given().I_am_a_bursary_in_current_academic_session()
				.and().I_pick_a_sponsor_with_sponsor_no_$(SPONSOR_NO);
		when().I_want_to_start_settlement_process_for_sponsor$();
		then().I_can_generate_invoice();
	}
}
