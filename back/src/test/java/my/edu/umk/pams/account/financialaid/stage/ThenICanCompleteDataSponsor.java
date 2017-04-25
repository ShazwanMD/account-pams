package my.edu.umk.pams.account.financialaid.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class ThenICanCompleteDataSponsor extends Stage<ThenICanCompleteDataSponsor>{

	@Autowired
	private IdentityService identityService;
	
	@ExpectedScenarioState
    AcSponsor sponsor;
	
	@As("I can complete data sponsor")
	public ThenICanCompleteDataSponsor complete_data_sponsor() {

		boolean hasCoverage = identityService.hasCoverage(sponsor);
		Assert.isTrue(hasCoverage, "Sponsor has coverage");
		
		return self();
	}
}
