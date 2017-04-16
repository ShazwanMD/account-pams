package my.edu.umk.pams.account.billing.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;

public class ThenDebitUnderChargeInvoice extends Stage<ThenDebitUnderChargeInvoice> {
	
	@As("Debit under charge will be invoice")
	public ThenDebitUnderChargeInvoice Debit_under_charge_invoice(){
		return self();
	}
}
