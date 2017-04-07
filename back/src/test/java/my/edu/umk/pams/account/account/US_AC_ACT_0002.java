package my.edu.umk.pams.account.account;

import com.tngtech.jgiven.annotation.ScenarioStage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.account.stage.*;
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


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@As("US_AC_ACT_0002 Sample User Story")
public class US_AC_ACT_0002 extends SpringScenarioTest<GivenIAmBusinessAdminUser, WhenIAddAStudent, ThenTheStudentIsAdded>{

    private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_0002.class);
    public static final String MATRIC_NO = "A17P001";

    @ScenarioStage
    private WhenIPickAStudentAccount additionalWhen;

    @ScenarioStage
    private ThenTheAccountIsAdded additionalThen;

    private static final double CHARGE_AMOUNT = 200.00;

    @Test
    @Rollback
    public void scenario1() {
        given().I_am_a_business_admin_user_in_current_academic_session();

        when().I_pick_a_student_$(MATRIC_NO);
        additionalWhen.and().I_pick_student_account();
        addStage(WhenIAddAnAccountCharge.class).and().I_add_an_account_charge(CHARGE_AMOUNT);

        then().the_student_user_is_added();
        additionalThen.and().the_student_account_is_added();
        addStage(ThenAccountIsCharged.class).and().student_account_is_charged_$(CHARGE_AMOUNT);
    }
}