package my.edu.umk.pams.account.billing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.billing.stage.WhenIGenerateInvoice;
import my.edu.umk.pams.account.billing.stage.ThenICanViewInvoice;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@As("As a Bursary, I want to generate student invoice")
public class US_AC_BLG_2003 extends SpringScenarioTest<GivenIAmBursary, WhenIGenerateInvoice, ThenICanViewInvoice>{
	
	private static final String FACULTY_CODE = "HLP";
	private static final String MATRIC_NO = "A17P001";
	
	@Test
	@Rollback(false)
	public void scenario1() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_generate_invoice_by_faculty$(FACULTY_CODE);
		then().I_can_view_invoice();
	}
	
	@Test
	@Rollback(false)
	public void scenario2() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_generate_invoice_by_individually$(MATRIC_NO);
		then().I_can_view_invoice();
	}

}
