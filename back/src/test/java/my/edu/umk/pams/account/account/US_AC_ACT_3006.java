package my.edu.umk.pams.account.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenCanMakePayment;
import my.edu.umk.pams.account.account.stage.WhenViewCharges;
import my.edu.umk.pams.account.account.stage.WhenViewFeesStatement;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmStudent;
import my.edu.umk.pams.bdd.tags.Issue;

@Issue("PAMSU-40")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_3006 extends SpringScenarioTest<GivenIAmStudent, WhenViewFeesStatement, ThenCanMakePayment> {

	@Issue("PAMSU-40")
	@Test
	@Rollback
	public void testScenario1() {
		given().I_am_a_student_in_current_academic_session();
		when().I_want_to_view_fees_statement();
		addStage(WhenViewCharges.class).and().I_want_to_view_charges();
		then().I_can_make_payment();
	}
}
