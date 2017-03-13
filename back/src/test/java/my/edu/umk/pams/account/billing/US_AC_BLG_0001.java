package my.edu.umk.pams.account.billing;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.account.billing.stage.ThenAccountIsInvoiced;
import my.edu.umk.pams.account.billing.stage.WhenIssueInvoice;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.tags.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

/**
 * @author PAMS
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_BLG_0001 extends SpringScenarioTest<GivenIAmBursary, WhenIssueInvoice, ThenAccountIsInvoiced> {

    private static final Logger LOG = LoggerFactory.getLogger(US_AC_BLG_0001.class);

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
        when().I_create_student_account_and_add_academic_charge()
                .and().I_issue_invoice_on_student_account();
        then().student_account_is_invoiced();
    }

    @Test
    @Rollback
    @Issue("JIRA-0005")
    public  void defectiveScenario()
    {
        Assert.isTrue(1 != 1, "Defective method");
    }

}
