package my.edu.umk.pams.account.billing;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.billing.stage.ThenStudentAccountIsCharged;
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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
@As("TODO User Story goes here US_AC_BLG_0000")
public class US_AC_BLG_0000 extends SpringScenarioTest<GivenIAmBursary, WhenIssueInvoice, ThenStudentAccountIsCharged> {

    private static final Logger LOG = LoggerFactory.getLogger(US_AC_BLG_0000.class);
    public static final String MATRIC_NO = "A17P001";

    @Test
    @Rollback
    public void testScenario1() {
        given().I_am_a_bursary_in_current_academic_session()
                .and().I_pick_a_student_with_matric_no_$(MATRIC_NO);
        when().I_charge_student_with_academic_fees();
        then().student_account_is_charged();
    }
}
