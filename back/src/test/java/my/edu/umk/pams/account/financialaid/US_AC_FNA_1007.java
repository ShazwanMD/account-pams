package my.edu.umk.pams.account.financialaid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.financialaid.stage.ThenChargesWillBeBilledToSponsor;
import my.edu.umk.pams.account.financialaid.stage.WhenIWantConfigureStudentSponsor;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Submodule;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Financial Aid")
@As("As a Bursary, I want to configure student sponsor so that charges will be billed to sponsor")
public class US_AC_FNA_1007 extends SpringScenarioTest<GivenIAmBursary, WhenIWantConfigureStudentSponsor, ThenChargesWillBeBilledToSponsor>{

	private static final String MATRIC_NO = "A17P002";

	@Test
	@Rollback(false)
	public void scenario1() {
		given().I_am_a_bursary_in_current_academic_session();
		when().I_want_to_configure_student_sponsor(MATRIC_NO);
		then().Charges_will_be_billed_to_sponsor();
	}
}
