package my.edu.umk.pams.account.billing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.billing.stage.InvoiceChargedListed;
import my.edu.umk.pams.account.billing.stage.ThenProcessThePayment;
import my.edu.umk.pams.account.billing.stage.WhenIMakeInvoiceWithChargeCode;

import my.edu.umk.pams.bdd.stage.GivenIAmBursary;

public class US_AC_BLG_2001 extends SpringScenarioTest<GivenIAmBursary, WhenIMakeInvoiceWithChargeCode, InvoiceChargedListed> {

    private static final Logger LOG = LoggerFactory.getLogger(US_AC_BLG_2001.class);

    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    @Rollback(true)
    public void testScenario1() {
        given().I_am_a_bursary_in_current_academic_session();
    //   As a bursary, I want to invoice with given charge code, so that I can filter what I can invoice
    	when().I_make_invoice_given_charge_code();
    	then().the_invoice_charges_are_listed();
    }
}
