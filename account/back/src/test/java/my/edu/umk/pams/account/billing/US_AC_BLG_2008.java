package my.edu.umk.pams.account.billing;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.billing.stage.ThenProcessThePayment;
import my.edu.umk.pams.account.billing.stage.WhenUpdateStudentPayment;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author PAMS
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_BLG_2008 extends SpringScenarioTest<GivenIAmBursary, WhenUpdateStudentPayment, ThenProcessThePayment> {

    private static final Logger LOG = LoggerFactory.getLogger(US_AC_BLG_2008.class);

    @Test
    @Rollback
    public void testScenario1() {
        given().I_am_a_bursary_in_current_academic_session();
        when().Update_student_payment();
        then().Process_the_payment();
    }
}
