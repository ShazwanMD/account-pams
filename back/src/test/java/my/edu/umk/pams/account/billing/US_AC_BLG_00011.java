package my.edu.umk.pams.account.billing;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.bdd.stage.GivenIAmStudent;
import my.edu.umk.pams.account.billing.stage.ThenAccountIsCleared;
import my.edu.umk.pams.account.billing.stage.WhenIPayCompound;
import my.edu.umk.pams.account.config.TestAppConfiguration;
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
public class US_AC_BLG_00011 extends SpringScenarioTest<GivenIAmStudent, WhenIPayCompound, ThenAccountIsCleared> {

    private static final Logger LOG = LoggerFactory.getLogger(US_AC_BLG_00011.class);

    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    @Rollback(true)
    public void testScenario1() {
		given().I_am_a_student_in_$_academic_session("2");
        when().I_pay_compound();    
        then().my_account_is_cleared();
    }
}
