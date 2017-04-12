package my.edu.umk.pams.account.billing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.billing.stage.ThenICanViewInvoice;
import my.edu.umk.pams.account.billing.stage.WhenIGenerateInvoice;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Submodule;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Billing")
@As("As a Bursary, I want to generate invoice by faculty so that I can view invoice")
public class US_AC_BLG_2025 extends SpringScenarioTest<GivenIAmBursary, WhenIGenerateInvoice, ThenICanViewInvoice>{

	private static final String FACULTY_CODE = "FKP";
	
	@Test
	@Rollback
	public void scenario1() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_generate_invoice_by_faculty$(FACULTY_CODE);
		then().I_can_view_invoice();
	}
}
