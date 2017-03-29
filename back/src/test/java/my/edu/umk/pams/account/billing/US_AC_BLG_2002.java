/*
 * As a bursary, I want to invoice per criteria, so that I can bulk invoice
 */
package my.edu.umk.pams.account.billing;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.billing.stage.BulkInvoiceAreListed;
import my.edu.umk.pams.account.billing.stage.WhenIssueInvoicePerCriteria;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_BLG_2002 extends SpringScenarioTest<GivenIAmBursary, WhenIssueInvoicePerCriteria, BulkInvoiceAreListed>{
	
	public void showInvoiceCodePerCriteria(){
		given().I_am_a_bursary_in_current_academic_session();
		when().I_set_invoice_perCriteria();
    	then().bulkInvoiceListed();
	}

}
