package my.edu.umk.pams.account.financialaid.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.JGivenStage;

@JGivenStage
public class ThenICanCheckMyFeesStatus extends Stage<ThenICanCheckMyFeesStatus>{
	
	@As("I can check my fees status")
	public ThenICanCheckMyFeesStatus I_can_check_my_fees_status() {

		return self();
	}
}
