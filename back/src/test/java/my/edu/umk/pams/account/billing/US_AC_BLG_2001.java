package my.edu.umk.pams.account.billing;

import my.edu.umk.pams.account.billing.stage.ThenFilterTheInvoice;
import my.edu.umk.pams.account.billing.stage.WhenIssueInvoice;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
@As("As a bursary, I want to invoice with given charge code, so that I can filter what I can invoice")
public class US_AC_BLG_2001 extends SpringScenarioTest<GivenIAmBursary, WhenIssueInvoice, ThenFilterTheInvoice>{

	private static final Logger LOG = LoggerFactory.getLogger(US_AC_BLG_2001.class);
	
    @Test
    @Rollback
    public void scenario1() {
    	//create student @ find student 
    	//create invoice @ charge code
    
		given().I_am_a_bursary_in_current_academic_session();
        when().I_create_security_charge_to_student("A17P002");
        then().I_can_show_invoice_filter_by_charge_code();
    }
}
