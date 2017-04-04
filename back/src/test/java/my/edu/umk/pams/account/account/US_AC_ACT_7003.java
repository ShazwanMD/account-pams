package my.edu.umk.pams.account.account;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenDiscountCanAppliedToReceipt;
import my.edu.umk.pams.account.account.stage.WhenIWantGiveCompoundsDiscountToStudent;
import my.edu.umk.pams.bdd.stage.GivenIAmStudentAffair;

/*
 * As Academic (MGSEB/PPS), 
 * I want to give compound's discount to student 
 * so that discount can applied to receipt
 */
public class US_AC_ACT_7003 extends
SpringScenarioTest<GivenIAmStudentAffair, WhenIWantGiveCompoundsDiscountToStudent, ThenDiscountCanAppliedToReceipt> {

private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_7003.class);


private static final String MATRIC_NO = "A17P001";

@Test
@Rollback
public void testScenario0() {
given().I_am_student_affair();
when().I_want_give_compounds_discount_to_student_$(MATRIC_NO);
then().discount_can_applied_to_receipt();
}
}
