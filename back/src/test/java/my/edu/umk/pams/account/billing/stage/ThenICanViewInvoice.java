package my.edu.umk.pams.account.billing.stage;

import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;


@JGivenStage
public class ThenICanViewInvoice extends Stage<ThenICanViewInvoice>{

	@ProvidedScenarioState
	private AcSettlement settlement;
	
	@ProvidedScenarioState
	private AcInvoice invoice;
	
	@As("I can view invoice")
	public ThenICanViewInvoice I_can_view_invoice() {

		Assert.notNull(invoice, "invoice was null");
		Assert.notNull(settlement, "settlement was null");
		return self();
		
	}
}
