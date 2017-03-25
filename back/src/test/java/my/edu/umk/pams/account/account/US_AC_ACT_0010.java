package my.edu.umk.pams.account.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenIKnowTheCompoundAmount;
import my.edu.umk.pams.account.account.stage.WhenIWantViewCompoundBill;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmStudent;

/*
 * As a Student, 
 *  I want view compound bill
 * so that I know the compound amount.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_0010 extends SpringScenarioTest<GivenIAmStudent, WhenIWantViewCompoundBill, ThenIKnowTheCompoundAmount> {

	private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_0010.class);

	@Test
	@Rollback(true)
	public void scenario1() {
		given().I_am_a_student_in_current_academic_session();
		when().I_want_view_my_compound_bill();
		then().I_know_the_compound_amount();
	}
}
