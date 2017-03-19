package my.edu.umk.pams.account.identity;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.stage.ThenStudentShouldHaveSponsorship;
import my.edu.umk.pams.account.identity.stage.WhenIGrantSponsorshipToStudent;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * todo(hajar): refactor masuk financialaid
 * As bursary
 * I want to grant sponsorship for student,
 * so that student has sponsorship  todo(hajar): please update user story
 *
 * @author PAMS
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_FNA_1001 extends SpringScenarioTest<GivenIAmBursary, WhenIGrantSponsorshipToStudent, ThenStudentShouldHaveSponsorship> {

    private static final String SPONSOR_NO = "JPA"; // identity no
    private static final String MATRIC_NO = "A17P001";

    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    @Rollback(false)
    public void scenario1() {
        given().I_am_a_bursary_in_current_academic_session()
                .and().I_pick_a_student_with_matric_no_$(MATRIC_NO);
        when().I_grant_sponsorship_of_$_to_the_student(SPONSOR_NO);
        then().the_student_has_sponsorship();
    }
}
