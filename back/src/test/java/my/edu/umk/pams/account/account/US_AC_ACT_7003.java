package my.edu.umk.pams.account.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenAcademicDiscountCanAppliedToReceipt;
import my.edu.umk.pams.account.account.stage.WhenIWantGiveCompoundsAcademicDiscountToStudent;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmMGSEBAdministrator;

/*
 * As Academic (MGSEB/PPS), 
 * I want to give compound's discount to student 
 * so that discount can applied to receipt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_7003 extends
		SpringScenarioTest<GivenIAmMGSEBAdministrator, WhenIWantGiveCompoundsAcademicDiscountToStudent, ThenAcademicDiscountCanAppliedToReceipt> {

	private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_7003.class);

	private static final String MATRIC_NO = "A17P001";

	@Test
	@Rollback(false)
	public void testScenario0() {
		given().I_am_a_MGSEB_administrator_in_current_academic_session();
		when().I_want_give_compounds_discount_to_student_$(MATRIC_NO);
		then().discount_can_applied_to_receipt();
	}
}
