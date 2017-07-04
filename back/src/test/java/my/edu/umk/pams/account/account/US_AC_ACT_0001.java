package my.edu.umk.pams.account.account;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.account.stage.ThenAccountIsCharged;
import my.edu.umk.pams.account.account.stage.WhenIAddAnAccountCharge;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBusinessAdminUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@As("As a Business admin user, I want to list student charges by account so that I can view student's charges")
public class US_AC_ACT_0001
		extends SpringScenarioTest<GivenIAmBusinessAdminUser, WhenIAddAnAccountCharge, ThenAccountIsCharged> {

	@Test
	public void listChargesByAccountForBusinessUser() {
		given().I_am_a_business_admin_user_in_current_academic_session();
		when().I_show_charges_by_account_for_accountNo_$("0-000-0");
		then().the_charges_for_the_student_are_listed();
	}
}