package my.edu.umk.pams.account.account;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.account.account.stage.ThenCanViewStudentsCompoundCharges;
import my.edu.umk.pams.account.account.stage.WhenIListAllCompoundStudent;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmPPSAdministrator;
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
 *  As a Academic (MGSEB/PPS),
 *  I want to list all compound student,
 *  so that I can view student's compound charges
 */

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_7002 extends
		SpringScenarioTest<GivenIAmPPSAdministrator, WhenIListAllCompoundStudent, ThenCanViewStudentsCompoundCharges> {

	private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_7002.class);

	private static final String MATRIC_NO = "A17P002";

	@Before
	public void before() {
	}

	@After
	public void after() {
	}

	@Test
	@Rollback
	public void testScenario0() {
	
		given().I_am_a_PPS_administrator_in_current_academic_session();
		when().list_all_compound_student_$(MATRIC_NO);
		then().can_view_students_compound_charges_$(MATRIC_NO);
	}
}
