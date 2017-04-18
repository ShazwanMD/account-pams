package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.JGivenStage;

@JGivenStage
public class ThenCanMakePayment extends Stage<ThenCanMakePayment>{

	@As("I can make payment")
	public ThenCanMakePayment I_can_make_payment() {
		
		return self();	
	}

}
