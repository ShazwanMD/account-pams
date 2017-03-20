package my.edu.umk.pams.account.account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenCanGiveStudentInformationToBursary;
import my.edu.umk.pams.account.account.stage.WhenIWantUpdateStudentInformation;
import my.edu.umk.pams.bdd.stage.GivenIAmPPSAdministrator;

public class US_AC_ACT_7004 extends
		SpringScenarioTest<GivenIAmPPSAdministrator, WhenIWantUpdateStudentInformation, ThenCanGiveStudentInformationToBursary> {
	private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_7004.class);

	@Before
	public void before() {
	}

	@After
	public void after() {
	}

	@Test
	@Rollback
	public void testScenario0() {
		// Given
		given().I_am_a_PPS_administrator_in_current_academic_session();
		// When
		when().I_want_update_student_information();
		// Then
		then().can_give_student_information_to_Bursary();

	}

}
