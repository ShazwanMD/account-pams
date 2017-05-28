package my.edu.umk.pams.account.billing.stage;

import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import my.edu.umk.pams.account.billing.model.AcDebitNote;
import my.edu.umk.pams.account.billing.model.AcInvoice;

public class ThenDebitUnderChargeInvoice extends Stage<ThenDebitUnderChargeInvoice> {
	

	@ProvidedScenarioState
	private AcDebitNote debitNote;
	
	@As("Debit under charge will be invoice")
	public ThenDebitUnderChargeInvoice Debit_under_charge_invoice(){
		
		Assert.isNull(debitNote, "debit note was null");

		return self();

	}
}
