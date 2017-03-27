package my.edu.umk.pams.account.billing.stage;

import com.tngtech.jgiven.Stage;

public class ThenDebitUnderChargeInvoice extends Stage<ThenDebitUnderChargeInvoice> {
	public ThenDebitUnderChargeInvoice Debit_under_charge_invoice(){
		return self();
	}
}
