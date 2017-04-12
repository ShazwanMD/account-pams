package my.edu.umk.pams.account.billing;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.billing.stage.WhenCreateAdvancedPayment;
import my.edu.umk.pams.account.billing.stage.ThenAddSurplus;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
@Issue("PAMSU-17")
@Submodule("Billing")
@As("As a bursary i should able to create any advance payment so that I can recognize any surplus")
public class US_AC_BLG_2017 extends SpringScenarioTest<GivenIAmBursary, WhenCreateAdvancedPayment, ThenAddSurplus> {

    private static final Logger LOG = LoggerFactory.getLogger(US_AC_BLG_2017.class);
    
    private static final String MATRIC_NO = "A17P001";

    @Test
    @Rollback
    public void testScenario1() {
        given().I_am_a_bursary_in_current_academic_session();
        when().Create_advanced_payment_for_student_$(MATRIC_NO);
        then().Add_surplus_for_student_$(MATRIC_NO);
    }
}
