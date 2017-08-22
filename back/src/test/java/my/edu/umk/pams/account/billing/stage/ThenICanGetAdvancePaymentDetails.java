package my.edu.umk.pams.account.billing.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;

public class ThenICanGetAdvancePaymentDetails extends Stage<ThenICanGetAdvancePaymentDetails>{

	@As("I can get payment details")
	public ThenICanGetAdvancePaymentDetails I_can_get_payment_details() {

		return self();
		
	}
}
