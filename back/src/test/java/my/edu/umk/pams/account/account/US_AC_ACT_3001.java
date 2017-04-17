package my.edu.umk.pams.account.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenICanMakePayment;
import my.edu.umk.pams.account.account.stage.WhenIWantToViewFeesStatementAndCharges;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmStudent;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;

@Issue("PAMSU-40")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Account")
@As("As a Students, I want to view fees statement and charges so that I can make payment")
public class US_AC_ACT_3001 extends SpringScenarioTest<GivenIAmStudent, WhenIWantToViewFeesStatementAndCharges , ThenICanMakePayment> {

	@Test
	@Rollback
	public void testScenario0() {
		given().I_am_a_student_in_current_academic_session() ;
		when().I_want_to_view_fees_statement_and_charges();
		then().I_can_make_payment();
	}
}
