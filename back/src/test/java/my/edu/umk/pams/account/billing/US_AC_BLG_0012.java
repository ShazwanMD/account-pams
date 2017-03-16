package my.edu.umk.pams.account.billing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.billing.stage.ThenAReceiptIsGenerated;
import my.edu.umk.pams.account.billing.stage.WhenIPayCompound;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmStudent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_BLG_0012 extends	SpringScenarioTest<GivenIAmStudent, WhenIPayCompound, ThenAReceiptIsGenerated>{
//As a Student, I want to print receipt compound so that it can be used as proof of payment.

    @Before
    public void before() {
    }

    @After
    public void after() {
    }
    
    @Test
    @Rollback(true)
    public void scenarioPayment(){
    	given().I_am_a_student_in_current_academic_session();
    	when().I_pay_the_compound();
    	then().Payment_receipt_generated();
    }
    

}
