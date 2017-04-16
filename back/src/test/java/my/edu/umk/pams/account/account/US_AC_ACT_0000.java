package my.edu.umk.pams.account.account;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.account.stage.ThenTheStudentIsAdded;
import my.edu.umk.pams.account.account.stage.WhenIAddAStudent;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBusinessAdminUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@As("US_AC_ACT_0000 is a sample test class. For naming convention explanation, see <project>/README.md")
public class US_AC_ACT_0000 extends
        SpringScenarioTest<GivenIAmBusinessAdminUser, WhenIAddAStudent, ThenTheStudentIsAdded>{

    public static final String MATRIC_NO = "A17P001";

    @Test
    @Rollback
    @Pending
    public void scenario001() {
        given().I_am_a_business_admin_user_in_current_academic_session();
        when().I_pick_a_student_$(MATRIC_NO);
        then().the_student_user_is_added();
    }
}