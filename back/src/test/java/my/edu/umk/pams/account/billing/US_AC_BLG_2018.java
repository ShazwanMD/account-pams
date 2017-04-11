package my.edu.umk.pams.account.billing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import my.edu.umk.pams.account.billing.stage.THEN;
import my.edu.umk.pams.account.billing.stage.WHEN;
import my.edu.umk.pams.bdd.stage.GivenIAmBursary;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

public class US_AC_BLG_2018 extends SpringScenarioTest<GivenIAmBursary, WHEN, THEN> {
	  private static final Logger LOG = LoggerFactory.getLogger(US_AC_BLG_2018.class);
}
