package my.edu.umk.pams.account.billing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import my.edu.umk.pams.account.billing.stage.THEN;
import my.edu.umk.pams.account.billing.stage.WHEN;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;
import my.edu.umk.pams.bdd.tags.Issue;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
@Issue("PAMSU-18")

public class US_AC_BLG_2018 extends SpringScenarioTest<GivenIAmBursary, WHEN, THEN> {
	  private static final Logger LOG = LoggerFactory.getLogger(US_AC_BLG_2018.class);
}
