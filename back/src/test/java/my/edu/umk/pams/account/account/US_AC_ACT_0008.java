package my.edu.umk.pams.account.account;
/*
 * As a Bursary, 
 * I want to list student charges of type compound by account,  
 * so that I can view student's compound charges 
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenAccountIsCharged;
import my.edu.umk.pams.account.account.stage.ThenICanViewStudentCompoundCharges;
import my.edu.umk.pams.account.account.stage.WhenIAddAccountCharge;
import my.edu.umk.pams.account.account.stage.WhenIListStudentChargesOfTypeCompound;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_0008 extends SpringScenarioTest<GivenIAmBursary, WhenIListStudentChargesOfTypeCompound, ThenICanViewStudentCompoundCharges>{
	private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_0008.class);

    @Before
    public void before() {
    }

    @After
    public void after() {
    }
    
    @Issue("PAMSU-3")
    private static final String MATRIC_NO = "19990201-07-1234";
    
    
    @Test
    public void testScenario0() {
        given().I_am_a_bursary_in_current_academic_session();
        when().I_list_student_charges_of_type_compound_$(MATRIC_NO);
        then().I_can_view_student_compound_charge();
    }

}
