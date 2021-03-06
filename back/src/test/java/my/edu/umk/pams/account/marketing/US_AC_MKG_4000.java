package my.edu.umk.pams.account.marketing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.marketing.stage.ThenICanReduceStudentPayment;
import my.edu.umk.pams.account.marketing.stage.WhenIWantToValidateWaiverPromoCode;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;


@Issue("")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Marketing")
@As("As a Bursary, I want to validate waiver promo code so that I can reduce payment for student")
public class US_AC_MKG_4000 extends SpringScenarioTest<GivenIAmBursary, WhenIWantToValidateWaiverPromoCode, ThenICanReduceStudentPayment>{
	
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
