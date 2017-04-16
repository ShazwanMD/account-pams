package my.edu.umk.pams.account.financialaid.stage;

import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.billing.model.AcInvoice;

@JGivenStage
public class ThenICanUseAsAProof extends Stage<ThenICanUseAsAProof> {

	@ExpectedScenarioState
	AcInvoice invoice;

	@As("I can be use as a proof of sponsorship payment")
	public ThenICanUseAsAProof I_can_be_use_as_a_proof() {

		Assert.isNull(invoice, "Invoice is null");
		return self();
	}
}
