package my.edu.umk.pams.account.marketing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.marketing.stage.ThenICanReduceStudentPayment;
import my.edu.umk.pams.account.marketing.stage.WhenIWantToValidateWaiverPromoCode;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;


@Issue("")
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(classes = TestAppConfiguration.class)
@As("As a Bursary, I want to list student charges of type compound by account so that I can view student's compound charges")
public class US_AC_ACT_7002 extends SpringScenarioTest<GivenIAmBursary, WhenIWantToValidateWaiverPromoCode, ThenICanReduceStudentPayment>{
	
	private static final String MATRIC_NO = "A17P001";
	private static final String CODE = "TMGSEB-MBA-00-H79322";
	
	@Test
    @Rollback
    public void scenario1() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_want_to_validate_waiver_promo_code_$(CODE);
		then().I_can_reduce_payment_for_student_$(MATRIC_NO);
    }
}