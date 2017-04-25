package my.edu.umk.pams.account.financialaid.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;

import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.service.IdentityService;

public class ThenICanGenerateTextFile extends Stage<ThenICanGenerateTextFile> {

	@Autowired
	private IdentityService identityService;
	
	@ExpectedScenarioState
    AcSponsor sponsor;
	
	public ThenICanGenerateTextFile I_can_generate_text_file() {
		
		boolean hasCoverage = identityService.hasCoverage(sponsor);
		Assert.isTrue(hasCoverage, "Sponsor has coverage");
		
		return self();
	}

}
