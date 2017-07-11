package my.edu.umk.pams.account.account;
/*
 * As a Students, 
 * I want to view fees statement and charges  // When_I_want_to_view_charges
 * so that I can make payment  //Then_I_can_make_payment
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenICanMakePayment;
import my.edu.umk.pams.account.account.stage.WhenIWantToViewFeesAndChargesForAStudent;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmParent;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;
@Issue("PAMSU-45")
@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Account")
@As("As Parents, I want to view student fees statement and charges so that I can make payment")
public class US_AC_ACT_4001 extends SpringScenarioTest<GivenIAmParent, WhenIWantToViewFeesAndChargesForAStudent, ThenICanMakePayment> {

	@Test
	@Rollback
	@Pending
	public void testScenario0() {
		given().I_am_a_parent_for_a_student_in_current_academic_session() ;
		when().I_want_to_view_fees_and_charges_for_a_student();
		then().I_can_make_payment();
	}
}
