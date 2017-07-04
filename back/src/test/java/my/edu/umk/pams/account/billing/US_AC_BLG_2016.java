package my.edu.umk.pams.account.billing;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.billing.stage.ThenCreditOverChargeInvoice;
import my.edu.umk.pams.account.billing.stage.WhenCreateCreditNote;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
@Issue("PAMSU-16")

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Billing")
@As("As a Bursary, I should be able to create credit note to credit so that I can credit any over charge")
public class US_AC_BLG_2016 extends SpringScenarioTest<GivenIAmBursary, WhenCreateCreditNote, ThenCreditOverChargeInvoice> {

    private static final Logger LOG = LoggerFactory.getLogger(US_AC_BLG_2016.class);

    @Test
    @Rollback
    public void testScenario1() {
        given().I_am_a_bursary_in_current_academic_session();
        when().Create_credit_note();
        then().Credit_over_charge_invoice();
    }
}
