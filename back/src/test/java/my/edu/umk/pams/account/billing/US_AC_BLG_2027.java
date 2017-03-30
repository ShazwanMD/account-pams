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

import my.edu.umk.pams.account.billing.stage.ThenICanPrintReport;
import my.edu.umk.pams.account.billing.stage.WhenIWantToGetListOfInvoiceByStudent;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;

/*
 * As a Bursary,  
 * I want to get list of invoice by student 
 * so that I can print report
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_BLG_2027 extends SpringScenarioTest<GivenIAmBursary, WhenIWantToGetListOfInvoiceByStudent, ThenICanPrintReport> {

	private static final Logger LOG = LoggerFactory.getLogger(US_AC_BLG_2027.class);
	
	private static final String MATRIC_NO = "A17P002";
	
	@Test
	@Rollback(false)
	public void scenario1() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_want_to_get_list_of_invoice_by_student$(MATRIC_NO);
		then().I_can_print_report();
	}
}
