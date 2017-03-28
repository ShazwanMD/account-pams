package my.edu.umk.pams.account.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenViewStudentAcademicCharges;
import my.edu.umk.pams.account.account.stage.WhenListStudentCharges;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;

/*
 * As a Bursary, 
 * I want to list student charges of type academic by account, 
 * so that I can view student's academic charges
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_2006
		extends SpringScenarioTest<GivenIAmBursary, WhenListStudentCharges, ThenViewStudentAcademicCharges> {
	private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_2006.class);

	private static final String MATRIC_NO = "A17P001";

	@Test
	@Rollback
	public void testScenario0() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_want_to_list_student_charges_of_type_academic_by_account_$(MATRIC_NO);
		then().I_can_view_student_academic_charges_$(MATRIC_NO);
	}
}
