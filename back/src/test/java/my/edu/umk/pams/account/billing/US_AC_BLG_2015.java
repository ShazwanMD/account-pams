package my.edu.umk.pams.account.billing;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.billing.stage.ThenDebitUnderChargeInvoice;
import my.edu.umk.pams.account.billing.stage.WhenCreateDebitNote;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
@Issue("PAMSU-15")
@As("As a Bursary, i should be able to create debit note so that I can debit any under charge invoice")
public class US_AC_BLG_2015 extends SpringScenarioTest<GivenIAmBursary, WhenCreateDebitNote, ThenDebitUnderChargeInvoice> {

    private static final Logger LOG = LoggerFactory.getLogger(US_AC_BLG_2015.class);

    @Test
    @Rollback
    public void testScenario1() {
        given().I_am_a_bursary_in_current_academic_session();
        when().Create_debit_note();
        then().Debit_under_charge_invoice();
    }
}
