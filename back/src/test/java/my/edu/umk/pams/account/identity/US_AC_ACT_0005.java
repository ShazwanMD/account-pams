package my.edu.umk.pams.account.identity;


import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.annotation.As;
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


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@As("As bursary, I want to add sponsor account so that sponsor is added")
public class US_AC_ACT_0005 extends SpringScenarioTest<GivenIAmBursary, WhenIAddASponsor, ThenTheSponsorIsAdded> {

	private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_0005.class);

	@Test
	@Rollback(false)
	@Pending
	public void testAddSponsorByCurrentUser() {
		given().I_am_a_bursary_in_current_academic_session();
//		when().I_add_a_sponsor_user_and_account();
		then().the_sponsor_user_is_added();
	}
}
