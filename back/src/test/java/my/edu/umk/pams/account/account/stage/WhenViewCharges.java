package my.edu.umk.pams.account.account.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.JGivenStage;

@JGivenStage
public class WhenViewCharges extends Stage<WhenViewCharges> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenViewCharges.class);
	
	@As("I want to view charges")
	public WhenViewCharges I_want_to_view_charges() {
		return self();
	}

}
