/*
 * As a bursary, I want to invoice per criteria, so that I can bulk invoice
 */
package my.edu.umk.pams.account.billing;

import org.junit.After;
import org.junit.Before;
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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
@As("As a bursary, I want to invoice per criteria, so that I can bulk invoice")
public class US_AC_BLG_2002
		 extends SpringScenarioTest<GivenIAmBursary, WhenIssueInvoicePerCriteria, ThenBulkInvoiceAreListed> {

	@Test
	@Rollback
	public void showInvoiceCodePerCriteria() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_generate_invoice_per_matric_no("A17P001");
		then().I_can_list_invoice_by_matric_no();
	}

}
