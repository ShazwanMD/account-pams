package my.edu.umk.pams.account.identity;

import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.billing.stage.ThenStudentAccountIsCharged;
import my.edu.umk.pams.account.billing.stage.WhenIssueInvoice;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
@As("TODO User Story goes here US_AC_IDN_0001")
public class US_AC_IDN_0001 extends SpringScenarioTest<GivenIAmBursary, WhenIssueInvoice, ThenStudentAccountIsCharged> {


    @Test
    @Rollback
    @Pending
    public void testScenario1() {
    }
}
