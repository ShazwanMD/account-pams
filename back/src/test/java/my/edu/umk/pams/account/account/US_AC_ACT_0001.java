package my.edu.umk.pams.account.account;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.account.stage.ThenAccountIsCharged;
import my.edu.umk.pams.account.account.stage.WhenIAddAnAccountCharge;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBusinessAdminUser;
import my.edu.umk.pams.bdd.tags.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author PAMS
 */
@Submodule("Account")
@CoreNext
@FeatureNext("Charge")
@Story("ACT_0001")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_0001 extends SpringScenarioTest<GivenIAmBusinessAdminUser, WhenIAddAnAccountCharge, ThenAccountIsCharged>{

    private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_0001.class);

    /**
     * TODO: As a Business admin user
     * TODO: I want to list student charges by account,
     * TODO: so that I can view student's charges
     */
    @Test
    @Issue("JIRA-0001")
    public void listChargesByAccountForBusinessUser() {
        given().I_am_a_business_admin_user();
        when().I_show_charges_by_account_for_accountNo_$("0-000-0");
        then().the_charges_for_the_student_are_listed();
    }

}