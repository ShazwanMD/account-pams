package my.edu.umk.pams.account.account.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import my.edu.umk.pams.account.account.model.AcAccountCharge;

public class ThenTheCompoundDetailsAreRecorded extends Stage<ThenTheCompoundDetailsAreRecorded> {

	private static final Logger LOG = LoggerFactory.getLogger(ThenTheCompoundDetailsAreRecorded.class);

	@ExpectedScenarioState
	private AcAccountCharge charge; 
	
	public ThenTheCompoundDetailsAreRecorded the_compound_details_are_recorded() {

		Assert.notNull(charge, "There is charges");

		return self();
	}

}
