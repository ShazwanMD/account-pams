package my.edu.umk.pams.account.account;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.account.stage.ThenTheStudentIsAdded;
import my.edu.umk.pams.account.account.stage.WhenIAddAStudent;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBusinessAdminUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author PAMS
 *
 * US_AC_ACT_0000 class is an example test class
 * For naming explanation of US_AC_ACT_0000,
 * see <project>/README.md
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_0000 extends SpringScenarioTest<GivenIAmBusinessAdminUser, WhenIAddAStudent, ThenTheStudentIsAdded>{

    private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_0000.class);
    public static final String MATRIC_NO = "A17P001";

    @Test
    @Rollback
    public void scenario001() {
        given().I_am_a_business_admin_user_in_current_academic_session();
        when().I_pick_a_student_$(MATRIC_NO);
        then().the_student_user_is_added();
    }
}