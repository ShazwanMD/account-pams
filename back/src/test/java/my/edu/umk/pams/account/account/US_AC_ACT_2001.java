package my.edu.umk.pams.account.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.account.stage.ThenICanViewStudentCharges;
import my.edu.umk.pams.account.account.stage.WhenListStudentChargesByAccount;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;


@Issue("PAMSU-20")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Account")
@As("As a Bursary, I want to list student charges by account so that I can view student's charges")
public class US_AC_ACT_2001 extends
        SpringScenarioTest<GivenIAmBursary, WhenListStudentChargesByAccount, ThenICanViewStudentCharges>{

	private static final String MATRIC_NO = "A17P001";	
    @Test
    @Rollback
    public void scenario1() {
		given().I_am_a_bursary_in_current_academic_session();
        when().I_want_to_list_student_charges_by_account_$(MATRIC_NO);
        then().I_can_view_student_charges();
    }
}
