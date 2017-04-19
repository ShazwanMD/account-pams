package my.edu.umk.pams.account.billing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import my.edu.umk.pams.account.billing.stage.THEN;
import my.edu.umk.pams.account.billing.stage.WHEN;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
@Issue("PAMSU-18")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Billing")
@As("As a bursary i should be able to use surplus so that i can knockoff any upcoming student fee")
public class US_AC_BLG_2018 extends SpringScenarioTest<GivenIAmBursary, WHEN, THEN> {
	  private static final Logger LOG = LoggerFactory.getLogger(US_AC_BLG_2018.class);
	   @Test
	    @Rollback
	    public void testScenario1() {
	    }
}
