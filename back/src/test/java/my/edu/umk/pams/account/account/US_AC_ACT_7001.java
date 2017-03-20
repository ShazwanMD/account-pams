package my.edu.umk.pams.account.account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenCompoundDetailsRecord;
import my.edu.umk.pams.account.account.stage.WhenRegisterStudentCompound;
import my.edu.umk.pams.account.config.TestAppConfiguration;
/*
 * As Academic (MGSEB/PPS), 
 * I want to register student compound bill 
 * so that the compound details are record. 
 */
import my.edu.umk.pams.bdd.stage.GivenIAmStudent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_7001 extends SpringScenarioTest<GivenIAmStudent, WhenRegisterStudentCompound, ThenCompoundDetailsRecord>{
    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    @Rollback(false)
    public void scenario1() {
		given().I_am_a_student_in_current_academic_session();
        when().I_want_to_register_student_compound_bill();
        then().the_compound_details_are_record();
    }
}
