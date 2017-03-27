package my.edu.umk.pams.account.financialaid;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.financialaid.stage.ThenICanStartSettlementProcess;
import my.edu.umk.pams.account.financialaid.stage.WhenIGroupStudentBySponsor;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

//import my.edu.umk.pams.account.financialaid.stage.ThenICanStartSettlementProcess;

/**
 * As bursary
 * 	I want to group student by sponsor, 
 * 		so that I can start settlement processed;
 * 
 * @author PAMS
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_FNA_1002 extends SpringScenarioTest<GivenIAmBursary, WhenIGroupStudentBySponsor, ThenICanStartSettlementProcess>{

	private static final String SPONSOR_NO = "HLP";
	
	@Test
	@Rollback(false)
	public void testGroupSponsorByStudent() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_group_student_by_$_sponsor(SPONSOR_NO);
		then().I_start_check_settlement_process();
	}
}
