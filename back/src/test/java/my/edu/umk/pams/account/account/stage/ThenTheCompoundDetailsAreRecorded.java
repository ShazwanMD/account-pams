package my.edu.umk.pams.account.account.stage;

import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import my.edu.umk.pams.account.account.model.AcAccountCharge;

public class ThenTheCompoundDetailsAreRecorded extends Stage<ThenTheCompoundDetailsAreRecorded> {

	@ExpectedScenarioState
	private AcAccountCharge charge; 
	
	@As("the compound details are recorded")
	public ThenTheCompoundDetailsAreRecorded the_compound_details_are_recorded() {

		Assert.notNull(charge, "There is charges");

		return self();
	}

}
