package my.edu.umk.pams.account.financialaid.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.identity.model.AcStudent;


@JGivenStage
public class ThenICanStartSettlementProcess extends Stage<ThenICanStartSettlementProcess>{
	
	@ExpectedScenarioState
	AcStudent student;
	
	@As("the settlement can be processed")
	public ThenICanStartSettlementProcess the_settlement_can_be_processed() {

		return self();
	}
}
