package my.edu.umk.pams.account.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenCanViewStudentCharges;
import my.edu.umk.pams.account.account.stage.WhenListStudentChargesByAccount;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;

/*
 * As a Bursary, 
 * I want to list student charges by account, 
 * so that I can view student's charges
 */
@Issue("PAMSU-34")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_2001 extends SpringScenarioTest<GivenIAmBursary, WhenListStudentChargesByAccount, ThenCanViewStudentCharges>{
	private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_2001.class);
	private static final String MATRIC_NO = "A17P001";	
    @Test
    @Rollback
    public void scenario1() {
		given().I_am_a_bursary_in_current_academic_session();
        when().I_want_to_list_student_charges_by_account_$(MATRIC_NO);
        then().I_can_view_student_charges();
    }
}
