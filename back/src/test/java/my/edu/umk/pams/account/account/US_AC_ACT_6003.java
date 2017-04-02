package my.edu.umk.pams.account.account;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;

import my.edu.umk.pams.account.account.stage.ThenDiscountAppliedReceipt;
import my.edu.umk.pams.account.account.stage.WhenIWantGiveCompoundsDiscountStudent;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.stage.GivenIAmSecurity;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

/*
 * As a Security, 
 * I want to give compound's discount to student 
 * so that discount can applied to receipt
 */
public class US_AC_ACT_6003 extends SpringScenarioTest<GivenIAmSecurity, WhenIWantGiveCompoundsDiscountStudent, ThenDiscountAppliedReceipt> {

	private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_6003.class);

	private static final String MATRIC_NO = "AP17002";

	@Test
	@Rollback
	public void testScenario0() {
		given().I_am_security_$(MATRIC_NO);
		//when().I_want_to_give_compounds_discount_to_student_$(MATRIC_NO);
		//then().discount_can_applied_to_receipt();
	}
}
