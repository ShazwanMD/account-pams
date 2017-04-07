package my.edu.umk.pams.account.billing;

import my.edu.umk.pams.account.billing.stage.ThenTheAcademicChargesAreListed;
import my.edu.umk.pams.account.billing.stage.WhenIListAcademicChargesByAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
@As("As a Bursary, I want to list student charges of type academic by account, so that I can view student's academic charges")
public class US_AC_ACT_20006 extends SpringScenarioTest<GivenIAmBursary, WhenIListAcademicChargesByAccount, ThenTheAcademicChargesAreListed>{

    @Test
    @Rollback
    public void scenarioPayment(){
    	given().I_am_a_bursary_in_current_academic_session();
    	when().I_list_charges_by_acount();
    	then().the_charges_are_listed();
    }
    

}
