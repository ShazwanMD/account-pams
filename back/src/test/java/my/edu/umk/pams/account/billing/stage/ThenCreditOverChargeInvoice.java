package my.edu.umk.pams.account.billing.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;

public class ThenCreditOverChargeInvoice extends Stage<ThenCreditOverChargeInvoice> {
	
	@As("Credit over charge will be invoiced")
	public ThenCreditOverChargeInvoice Credit_over_charge_invoice(){
		return self();
	}
}
