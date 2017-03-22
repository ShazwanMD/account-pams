package my.edu.umk.pams.account.financialaid.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.JGivenStage;

@JGivenStage
public class ThenICanStartSettlementProcess extends Stage<ThenICanStartSettlementProcess>{

	@As("I start check settlement process")
	public ThenICanStartSettlementProcess I_start_check_settlement_process() {

		return self();
	}
}
