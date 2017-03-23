package my.edu.umk.pams.account.account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenCanViewPaidStudentCharges;
import my.edu.umk.pams.account.account.stage.WhenListPaidStudentChargesByAccount;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;

/*
 * As a Bursary, 
 * I want to list student charges by account, 
 * so that I can view student's charges
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_2003 extends SpringScenarioTest<GivenIAmBursary, WhenListPaidStudentChargesByAccount, ThenCanViewPaidStudentCharges>{
    
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
		given().I_am_a_bursary_in_current_academic_session();
        when().I_want_to_list_paid_student_charges_by_account_$(MATRIC_NO);
        then().I_can_view_paid_student_charges();
    }
}
