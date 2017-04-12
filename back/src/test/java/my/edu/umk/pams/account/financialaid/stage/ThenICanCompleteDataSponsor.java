package my.edu.umk.pams.account.financialaid.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.JGivenStage;

@JGivenStage
public class ThenICanCompleteDataSponsor extends Stage<ThenICanCompleteDataSponsor>{

	@As("I can complete data sponsor")
	public ThenICanCompleteDataSponsor complete_data_sponsor() {

		return self();
	}
}
