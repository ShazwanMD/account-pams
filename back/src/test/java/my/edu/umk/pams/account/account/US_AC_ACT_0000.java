package my.edu.umk.pams.account.account;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmAUser;
import my.edu.umk.pams.bdd.stage.ThenTheStudentIsAdded;
import my.edu.umk.pams.bdd.stage.WhenIAddAStudent;
import org.junit.After;
import org.junit.Before;
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
public class US_AC_ACT_0000 extends SpringScenarioTest<GivenIAmAUser, WhenIAddAStudent, ThenTheStudentIsAdded>{

    private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_0000.class);

    @Before
    public void before() {
    }

    @After
    public void after(){
    }

    @Test
    @Rollback(false)
    public void testAddStudentByCurrentUser() {
        // Given
        given().I_am_an_admin_user();
        // When
        when().I_add_a_student_user();
        // Then
        then().the_student_user_is_added();
    }
}