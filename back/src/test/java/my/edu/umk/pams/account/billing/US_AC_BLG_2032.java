package my.edu.umk.pams.account.billing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.billing.stage.ThenICanGetAdvancePaymentDetails;
import my.edu.umk.pams.account.billing.stage.WhenISearchAdvancePaymentById;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Submodule;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Billing")
@As("As a Bursary, I want to search advance payment by id so that i can get payment details")
public class US_AC_BLG_2032 extends SpringScenarioTest<GivenIAmBursary, WhenISearchAdvancePaymentById, ThenICanGetAdvancePaymentDetails>{

	private static final Logger LOG = LoggerFactory.getLogger(US_AC_BLG_2032.class);
	private static final Long ID = (long) 1;
	
    @Test
    @Rollback
    public void scenario1() {
		given().I_am_a_bursary_in_current_academic_session();
        when().I_search_advance_payment_by_id(ID);
        then().I_can_get_payment_details();
    }
}
