package my.edu.umk.pams.account.billing;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.billing.stage.ThenStudentAccountIsCharged;
import my.edu.umk.pams.account.billing.stage.WhenSecurityChargeMeCompound;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmStudent;
import org.junit.After;
import org.junit.Before;
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
public class US_AC_BLG_00011 extends SpringScenarioTest<GivenIAmStudent, WhenSecurityChargeMeCompound, ThenStudentAccountIsCharged> {

    private static final Logger LOG = LoggerFactory.getLogger(US_AC_BLG_00011.class);

    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    @Rollback(true)
    public void scenario1() {
		given().I_am_a_student_in_current_academic_session();
        when().security_issues_me_compound_charges();
        then().student_account_is_charged();
    }
}
