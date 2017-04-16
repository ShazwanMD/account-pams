package my.edu.umk.pams.account.financialaid.stage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class ThenICanStartSettlementProcess extends Stage<ThenICanStartSettlementProcess> {

	@Autowired
	private IdentityService identityService;

	@ProvidedScenarioState
	AcSponsor sponsor;

	@ExpectedScenarioState
	private List<AcSponsorship> sponsorship;

	@As("I start check settlement process")
	public ThenICanStartSettlementProcess I_start_check_settlement_process() {

		boolean hasSponsorship = identityService.hasSponsorship(sponsor);
		Assert.isTrue(hasSponsorship, "sponsor should have sponsorship");

		return self();
	}
}
