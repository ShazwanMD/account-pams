package my.edu.umk.pams.account.financialaid;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.financialaid.stage.ThenAddSponsorAndCheckFees;
import my.edu.umk.pams.account.financialaid.stage.WhenIAddASponsorAndSettlementDetails;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;

/**
 * As bursary
 * 	I want to add sponsor coverage, 
 * 		so that sponsor coverage is added
 * 
 * As bursary
 * 	I want to add settlement process for sponsor,
 * 		so that I can check my fees status
 * 
 * @author PAMS
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_FNA_1000 extends SpringScenarioTest<GivenIAmBursary, WhenIAddASponsorAndSettlementDetails, ThenAddSponsorAndCheckFees>{
	
	private static final Logger LOG = LoggerFactory.getLogger(US_AC_FNA_1000.class);

	private static final String SPONSOR_NO = "HLP";
	private static final String ACADEMIC_SESSION_CODE = "201720181";

	@Before
	public void before() {
	}

	@After
	public void after() {
	}


	@Test
	@Rollback(false)
	public void scenario1() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_add_sponsor_$_with_coverage(SPONSOR_NO)
				.and().I_want_to_add_settlement_process_for_sponsor$(SPONSOR_NO, ACADEMIC_SESSION_CODE);
		then().the_sponsor_coverage_is_added()
				.and().I_can_check_my_fees_status();
	}
}
