package my.edu.umk.pams.account.marketing;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import my.edu.umk.pams.account.config.TestAppConfiguration;

import my.edu.umk.pams.account.marketing.stage.ThenDiscountCanAppliedToReceipt;
import my.edu.umk.pams.account.marketing.stage.WhenIWantGiveCompoundsDiscountToStudent;
import my.edu.umk.pams.bdd.stage.GivenIAmSecurity;
import my.edu.umk.pams.bdd.tags.Issue;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

@Issue("PAMSU-99")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@As("As a Security, I want to give compound's discount to student so that discount can applied to receipt")
public class US_AC_MKG_2001 extends
		SpringScenarioTest<GivenIAmSecurity, WhenIWantGiveCompoundsDiscountToStudent, ThenDiscountCanAppliedToReceipt> {

	private static final String MATRIC_NO = "A17P001";

	@Test
	@Rollback
	public void testScenario0() {
		given().I_am_security();
		when().I_want_give_compounds_discount_to_student_$(MATRIC_NO);
		then().discount_can_applied_to_receipt();
	}
}
