package my.edu.umk.pams.account.billing.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;

@JGivenStage
public class ThenICanViewIndividualInvoice extends Stage <ThenICanViewIndividualInvoice> {
	
	private static final Logger LOG = LoggerFactory.getLogger(ThenICanViewIndividualInvoice.class);
	
	@ProvidedScenarioState
	private AcSettlement settlement;

	@ExpectedScenarioState
	private AcInvoice invoice;
	
	@As("I can view individual invoice")
	public ThenICanViewIndividualInvoice I_can_view_individual_invoice(){
		
		Assert.notNull(invoice, "invoice was null");
		Assert.notNull(settlement, "settlement was null");
		return self();
	}

}
