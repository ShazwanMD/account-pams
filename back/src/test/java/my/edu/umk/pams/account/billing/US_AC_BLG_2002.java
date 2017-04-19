package my.edu.umk.pams.account.billing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.billing.stage.ThenBulkInvoiceAreListed;
import my.edu.umk.pams.account.billing.stage.WhenIssueInvoicePerCriteria;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;
@Issue("PAMSU-2")

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Billing")
@As("As a bursary, I want to invoice per criteria, so that I can bulk invoice")
public class US_AC_BLG_2002
		 extends SpringScenarioTest<GivenIAmBursary, WhenIssueInvoicePerCriteria, ThenBulkInvoiceAreListed> {

	@Test
	@Rollback(false)
	public void showInvoiceCodePerCriteria() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_generate_invoice_per_matric_no("A17P001");
		then().I_can_list_invoice_by_matric_no();
	}

}
