package my.edu.umk.pams.account.billing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.billing.stage.ThenICanViewIndividualInvoice;
import my.edu.umk.pams.account.billing.stage.ThenICanViewInvoice;
import my.edu.umk.pams.account.billing.stage.WhenIGenerateInvoice;
import my.edu.umk.pams.account.billing.stage.WhenIGenerateInvoiceIndividual;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;

/*
 * As a Bursary 
 * I want to generate invoice by Individual
 * So that I can view individual invoice
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_BLG_2027 extends SpringScenarioTest<GivenIAmBursary, WhenIGenerateInvoiceIndividual, ThenICanViewIndividualInvoice> {

	private static final Logger LOG = LoggerFactory.getLogger(US_AC_BLG_2027.class);
	
	private static final String MATRIC_NO = "A17P002";
	
	@Test
	@Rollback(false)
	public void scenario1() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_generate_invoice_by_individual$(MATRIC_NO);
		then().I_can_view_individual_invoice();
	}
}
