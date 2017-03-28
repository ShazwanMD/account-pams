package my.edu.umk.pams.account.financialaid;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.financialaid.stage.ThenICanStartSettlementProcess;
import my.edu.umk.pams.account.financialaid.stage.WhenIGenerateInvoice;
import my.edu.umk.pams.account.financialaid.stage.WhenIGroupStudentBySponsor;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;

/*
 * As a Bursary, 
 * 	I want to generate invoice by faculty 
 * 		
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_FNA_1004 extends SpringScenarioTest<GivenIAmBursary, WhenIGenerateInvoice, ThenICanStartSettlementProcess>{

	private static final String FACULTY_CODE = "FKP";
	
	@Before
	public void before() {
	}

	@After
	public void after() {
	}

	@Test
	@Rollback(false)
	public void scenario1() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_generate_invoice_by_faculty$(FACULTY_CODE);
		//then().I_start_check_settlement_process();
	}
}
