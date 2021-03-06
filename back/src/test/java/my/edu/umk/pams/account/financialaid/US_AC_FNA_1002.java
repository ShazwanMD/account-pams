package my.edu.umk.pams.account.financialaid;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.financialaid.stage.ThenICanStartSettlementProcess;
import my.edu.umk.pams.account.financialaid.stage.WhenIGroupStudentBySponsor;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Issue("PAMSU-62")
@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Financial Aid")
@As("As a bursary, I want to group student by sponsor, so that I can start a settlement process")
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
