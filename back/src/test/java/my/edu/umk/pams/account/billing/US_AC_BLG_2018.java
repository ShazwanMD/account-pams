package my.edu.umk.pams.account.billing;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.billing.stage.ThenICanSeeOutstandingBalance;
import my.edu.umk.pams.account.billing.stage.WhenICheckInvoice;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
/*
 * As a bursary i should be able to use surplus so that i can knockoff any upcoming student fee
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_BLG_2018	extends	SpringScenarioTest<GivenIAmBursary, WhenICheckInvoice, ThenICanSeeOutstandingBalance> {

	private static final String MATRIC_NUMBER = "A17P002";
	//private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(US_AC_BLG_2018.class);

	@Test
	@Rollback
	public void scenario1() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_make_invoice_given_charge_code(MATRIC_NUMBER);
		then().then_list_outstanding_balance();

	}
}