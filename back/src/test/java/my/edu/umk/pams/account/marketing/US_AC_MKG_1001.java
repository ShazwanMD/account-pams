package my.edu.umk.pams.account.marketing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.marketing.stage.ThenDiscountCanAppliedToReceipt;
import my.edu.umk.pams.account.marketing.stage.WhenIWantGiveCompoundsDiscountToStudent;
import my.edu.umk.pams.bdd.stage.GivenIAmStudentAffair;
import my.edu.umk.pams.bdd.tags.Issue;


@Issue("PAMSU-98")
@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@As("As a Student Affair, I want to give compound's discount to student so that discount can apply to receipt\n")
public class US_AC_MKG_1001 extends
		SpringScenarioTest<GivenIAmStudentAffair, WhenIWantGiveCompoundsDiscountToStudent, ThenDiscountCanAppliedToReceipt> {

	private static final String MATRIC_NO = "A17P001";

	@Test
	@Rollback
	public void testScenario0() {
		given().I_am_student_affair();
		when().I_want_give_compounds_discount_to_student_$(MATRIC_NO);
		then().discount_can_applied_to_receipt();
	}
}
