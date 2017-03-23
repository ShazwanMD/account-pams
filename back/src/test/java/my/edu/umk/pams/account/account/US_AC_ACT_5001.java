package my.edu.umk.pams.account.account;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.account.stage.ThenTheCompoundDetailsAreRecorded;
import my.edu.umk.pams.account.account.stage.WhenWantToRegisterStudentCompoundBill;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmStudentAffair;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * As a Student Affair I want to register student compound bill so that the
 * compound details are recorded.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_5001 extends
		SpringScenarioTest<GivenIAmStudentAffair, WhenWantToRegisterStudentCompoundBill, ThenTheCompoundDetailsAreRecorded> {

	private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_5001.class);
	

	@Before
	public void before() {
	}

	@After
	public void after() {
	}

	@Test
	@Rollback
	public void testScenario0() {
		String MATRIC_NO = "A17P001";
		given().I_am_student_affair();
		when().I_want_to_register_student_compound_bill_by_account_$(MATRIC_NO);
		then().the_compound_details_are_recorded();
	}
}
