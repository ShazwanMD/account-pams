package my.edu.umk.pams.account.identity;

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
import my.edu.umk.pams.account.identity.stage.ThenStudentSponsorshipIsAdded;
import my.edu.umk.pams.account.identity.stage.ThenTheSponsorCoverageIsAdded;
import my.edu.umk.pams.account.identity.stage.WhenIAddASponsorDetails;
import my.edu.umk.pams.account.identity.stage.WhenIAddStudentSponsorship;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;

/**
 * As bursary
 * 	I want to add sponsorship for student, 
 * 		so that I know which student have a sponsorship
 * 
 * @author PAMS
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_FNA_1001 extends SpringScenarioTest<GivenIAmBursary, WhenIAddStudentSponsorship, ThenStudentSponsorshipIsAdded>{

	@Before
	public void before() {
	}

	@After
	public void after() {
	}
	
	private static final Long ID = (long) 13;

	@Test
	@Rollback(false)
	public void testGroupStudentBySponsor() {
		
		given().I_am_a_bursary_in_current_academic_session();

		when().I_add_student_by_sponsorship$(ID);

		then().the_student_sponsorship_is_added();
	}

}
