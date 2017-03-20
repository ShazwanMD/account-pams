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
import my.edu.umk.pams.account.financialaid.stage.ThenTheSponsorCoverageIsAdded;
import my.edu.umk.pams.account.financialaid.stage.WhenIAddASponsorDetails;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;

/**
 * 
 *
 * As bursary
 * 	I want to add sponsor coverage, 
 * 		so that sponsor coverage is added
 * 
 * @author PAMS
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_FNA_1000 extends SpringScenarioTest<GivenIAmBursary, WhenIAddASponsorDetails, ThenTheSponsorCoverageIsAdded>{
	
	private static final Logger LOG = LoggerFactory.getLogger(US_AC_FNA_1000.class);

	private static final String SPONSOR_NO = "SPNSR-1489971827891";

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
		when().I_add_sponsor_$_with_coverage(SPONSOR_NO);
		then().the_sponsor_coverage_is_added();
	}
}
