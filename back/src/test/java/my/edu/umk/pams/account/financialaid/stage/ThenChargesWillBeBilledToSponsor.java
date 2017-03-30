package my.edu.umk.pams.account.financialaid.stage;

import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.identity.model.AcStudent;

@JGivenStage
public class ThenChargesWillBeBilledToSponsor extends Stage<ThenChargesWillBeBilledToSponsor> {

	@ProvidedScenarioState
	private AcStudent student;
	
	@As("Charges will be billed to sponsor")
	public ThenChargesWillBeBilledToSponsor Charges_will_be_billed_to_sponsor() {

		Assert.notNull(student, "student was null");
	
		return self();
	}
}
