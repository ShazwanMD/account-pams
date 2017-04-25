package my.edu.umk.pams.account.financialaid.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.service.IdentityService;

public class WhenIGetListOfDeductions extends Stage<WhenIGetListOfDeductions> {

	@Autowired
	private IdentityService identityService;

	@ProvidedScenarioState
	AcSponsor sponsor;

	@As("I want to get list of deduction")
	public WhenIGetListOfDeductions I_get_list_of_deductions_$(String sponsorNo) {
		boolean hasSponsorship = identityService.hasSponsorship(sponsor);
		Assert.isTrue(hasSponsorship, "sponsor should have sponsorship");

		return self();
	}

}
