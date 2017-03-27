package my.edu.umk.pams.account.identity;

import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.stage.ThenTheSponsorIsAdded;
import my.edu.umk.pams.account.identity.stage.WhenIAddASponsor;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * As bursary
 * 	I want to add sponsor user account, 
 * 		so that sponsor user is added
 * 
 * As bursary
 * 	I want to add sponsor account, 
 * 		so that sponsor account is added
 * 
 * @author PAMS
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_2002 extends SpringScenarioTest<GivenIAmBursary, WhenIAddASponsor, ThenTheSponsorIsAdded> {

	private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_2002.class);

	@Test
	@Rollback
	@Pending
	public void scenario1() {
		given().I_am_a_bursary_in_current_academic_session();
//		when().I_add_a_sponsor_user_and_account();
		then().the_sponsor_user_is_added()
			.and().the_sponsor_account_is_added();
	}
}
