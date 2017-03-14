package my.edu.umk.pams.account.account;

import com.tngtech.jgiven.annotation.ScenarioStage;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.account.stage.*;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBusinessAdminUser;
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
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_0002 extends SpringScenarioTest<GivenIAmBusinessAdminUser, WhenIAddAStudent, ThenTheStudentIsAdded>{

    private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_0002.class);
    @ScenarioStage
    private WhenIAddAStudentAccount additionalWhen;

    @ScenarioStage
    private ThenTheAccountIsAdded additionalThen;

    private static final double CHARGE_AMOUNT = 200.00;


    @Before
    public void before() {

    }

    @After
    public void after() {

    }

    @Test
    @Rollback
    public void scenario1() {
        // Given
        given().I_am_a_business_admin_user();

        // When
        when().I_add_a_student_user();
        additionalWhen.and().I_add_a_student_account();
        addStage(WhenIAddAnAccountCharge.class).and().I_add_an_account_charge(CHARGE_AMOUNT);

        // Then
        then().the_student_user_is_added();
        additionalThen.and().the_student_account_is_added();
        addStage(ThenAccountIsCharged.class).and().student_account_is_charged_$(CHARGE_AMOUNT);
    }
}