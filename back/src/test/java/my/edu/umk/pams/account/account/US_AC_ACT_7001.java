package my.edu.umk.pams.account.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.account.account.stage.ThenCompoundDetailsRecord;
import my.edu.umk.pams.account.account.stage.WhenRegisterStudentCompound;
import my.edu.umk.pams.account.config.TestAppConfiguration;

import my.edu.umk.pams.bdd.stage.GivenIAmStudent;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;


@Issue("PAMSU-93")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Account")
@As("As Academic (MGSEB/PPS), I want to register student compound bill so that the compound details are record")
public class US_AC_ACT_7001 extends SpringScenarioTest<GivenIAmStudent, WhenRegisterStudentCompound, ThenCompoundDetailsRecord>{

    @Test
    @Rollback(false)
    public void scenario1() {
		given().I_am_a_student_in_current_academic_session();
        when().I_want_to_register_student_compound_bill();
        then().the_compound_details_are_record();
    }
}
