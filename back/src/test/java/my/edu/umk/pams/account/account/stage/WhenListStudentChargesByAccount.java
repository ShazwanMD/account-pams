package my.edu.umk.pams.account.account.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.identity.model.AcCoverage;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class WhenListStudentChargesByAccount extends Stage<WhenListStudentChargesByAccount>{
	private static final Logger LOG = LoggerFactory.getLogger(WhenListStudentChargesByAccount.class);
	@Autowired
	private IdentityService identityService;

	@ExpectedScenarioState
	private AcStudent student;

	@ExpectedScenarioState
	private AcAccount account;

	@ExpectedScenarioState
	private List<AcSponsorship> sponsorship;

	@ExpectedScenarioState
	private List<AcCoverage> coverage;
	
	@As("I want to list student charges by account")
	public WhenListStudentChargesByAccount I_want_to_list_student_charges_by_account_$(String matricNo) {
		
		return self();	
	}
	}
