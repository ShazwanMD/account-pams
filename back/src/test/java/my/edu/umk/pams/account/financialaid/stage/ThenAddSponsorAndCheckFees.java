package my.edu.umk.pams.account.financialaid.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class ThenAddSponsorAndCheckFees extends Stage<ThenAddSponsorAndCheckFees> {
	
	@Autowired
	private IdentityService identityService;
	
	@ExpectedScenarioState
    AcSponsor sponsor;
	
	@ExpectedScenarioState
	AcInvoice invoice;
	
	@ExpectedScenarioState
	AcSettlement settlement;
	
	@As("the sponsor coverage is added")
	public ThenAddSponsorAndCheckFees the_sponsor_coverage_is_added() {

		boolean hasCoverage = identityService.hasCoverage(sponsor);
		Assert.isTrue(hasCoverage, "Sponsor has coverage");

		return self();
	}
	

}
