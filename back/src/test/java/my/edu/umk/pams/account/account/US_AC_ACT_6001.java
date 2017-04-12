package my.edu.umk.pams.account.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenCompoundDetailsAreRecorded;

import my.edu.umk.pams.account.account.stage.WhenRegisterStudentCompoundBill;
import my.edu.umk.pams.account.config.TestAppConfiguration;

import my.edu.umk.pams.bdd.stage.GivenIAmSecurity;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;

@Issue("PAMSU-52")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Account")
@As("As a Security, I want to register student compound bill so that the compound details are recorded")
public class US_AC_ACT_6001
		extends SpringScenarioTest<GivenIAmSecurity, WhenRegisterStudentCompoundBill, ThenCompoundDetailsAreRecorded> {
	private static final String MATRIC_NO = "A17P001";

	@Test
	@Rollback
	public void scenario1() {
		given().I_am_security_$(MATRIC_NO);
		when().I_want_to_register_student_compound_bill();
		then().the_compound_details_are_record();
	}
}
