package my.edu.umk.pams.account.account;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenCompoundDetailsRecord;
import my.edu.umk.pams.account.account.stage.WhenWantToRegisterStudentCompoundBill;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmStudentAffair;
import my.edu.umk.pams.bdd.tags.Submodule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Account")
@As("As a Student Affair, I want to register student compound bill so that the compound details are recorded")
public class US_AC_ACT_5001 extends SpringScenarioTest<GivenIAmStudentAffair, WhenWantToRegisterStudentCompoundBill, ThenCompoundDetailsRecord> {


	@Test
	@Rollback
	public void testScenario0() {
		String MATRIC_NO = "A17P001";
		given().I_am_student_affair();
		when().I_want_to_register_student_compound_bill_by_account_$(MATRIC_NO);
		then().the_compound_details_are_record();
	}
}
