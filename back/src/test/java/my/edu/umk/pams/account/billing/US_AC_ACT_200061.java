package my.edu.umk.pams.account.billing;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.billing.stage.ThenTheAcademicChargesAreListed;
import my.edu.umk.pams.account.billing.stage.WhenIListAcademicChargesByAccount;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmStudent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_200061 extends SpringScenarioTest<GivenIAmStudent, WhenIListAcademicChargesByAccount, ThenTheAcademicChargesAreListed>{

    @Test
    @Rollback
    public void scenarioPayment(){
    	given().I_am_a_student_in_current_academic_session();
    	when().I_list_charges_by_acount();
    	then().the_charges_are_listed();
    }
    

}
