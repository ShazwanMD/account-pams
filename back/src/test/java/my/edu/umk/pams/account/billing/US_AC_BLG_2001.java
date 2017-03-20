package my.edu.umk.pams.account.billing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.billing.stage.ThenFilterTheInvoice;
import my.edu.umk.pams.account.billing.stage.WhenIssueInvoiceWithGivenChargeCode;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;

/*
 * As a bursary, 
 * I want to invoice with given charge code, 
 * so that I can filter what I can invoice
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_BLG_2001 extends SpringScenarioTest<GivenIAmBursary, WhenIssueInvoiceWithGivenChargeCode, ThenFilterTheInvoice>{
	private static final Logger LOG = LoggerFactory.getLogger(US_AC_BLG_2001.class);
	
	@Before
    public void before() {
    }

    @After
    public void after() {
    }
    
    @Test
    @Rollback(true)
    public void scenario1() {
		given().I_am_a_bursary_in_current_academic_session();
        when().I_want_to_invoice_with_given_charge_code();
        then().I_can_filter_what_I_can_invoice();
    }
}
