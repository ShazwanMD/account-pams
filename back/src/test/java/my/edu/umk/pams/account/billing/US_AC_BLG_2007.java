package my.edu.umk.pams.account.billing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.billing.stage.ThenICanGiveValidInvoice;
import my.edu.umk.pams.account.billing.stage.WhenIWantPrintStudentInvoice;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;
@Issue("PAMSU-7")

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Billing")
@As("As a Bursary, I want to print student invoice after approve so that I can give valid invoice to student")
public class US_AC_BLG_2007 extends SpringScenarioTest<GivenIAmBursary, WhenIWantPrintStudentInvoice, ThenICanGiveValidInvoice>{
	
    @Test
    @Rollback
    public void scenario1() {
		given().I_am_a_bursary_in_current_academic_session();
        when().I_want_to_print_student_invoice();
        then().I_can_give_valid_invoice();
    }
}
