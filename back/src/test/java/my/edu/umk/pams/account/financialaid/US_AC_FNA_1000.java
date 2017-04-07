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
import my.edu.umk.pams.account.financialaid.stage.ThenAddSponsorAndCheckFees;
import my.edu.umk.pams.account.financialaid.stage.WhenIAddASponsorDetails;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@As("As a Bursary, I want to add sponsor coverage so that sponsor coverage is added")
//@As("US_AC_FNA_1009 As bursary, I want to add settlement process for sponsor so that I can check my fees status")
public class US_AC_FNA_1000 extends SpringScenarioTest<GivenIAmBursary, WhenIAddASponsorDetails, ThenAddSponsorAndCheckFees>{

	private static final String SPONSOR_NO = "HLP";

	@Test
	@Rollback(false)
	public void scenario1() {
		given().I_am_a_bursary_in_current_academic_session()
				.and().I_pick_a_sponsor_with_sponsor_no_$(SPONSOR_NO);
		when().I_add_sponsor_with_coverages();
		then().the_sponsor_coverage_is_added();
	}
}
