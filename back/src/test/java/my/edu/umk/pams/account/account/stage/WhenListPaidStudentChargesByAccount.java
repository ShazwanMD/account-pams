package my.edu.umk.pams.account.account.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcCoverage;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class WhenListPaidStudentChargesByAccount extends Stage<WhenListPaidStudentChargesByAccount>{
	private static final Logger LOG = LoggerFactory.getLogger(WhenListPaidStudentChargesByAccount.class);
	@Autowired
	private IdentityService identityService;

	@Autowired
	private AccountService accountService;
	
	@ProvidedScenarioState
    private AcAccount account;
	
	@ProvidedScenarioState
    private AcStudent student;
	
	@ProvidedScenarioState
    private List<AcAccountCharge> accountCharges;
	
	@As("I want to list student charges by account")
	public WhenListPaidStudentChargesByAccount I_want_to_list_paid_student_charges_by_account_$(String matricNo) {
		
		String filter = "";
		Integer offset = 0;
		Integer limit = 0;
		
		student = identityService.findStudentByMatricNo(matricNo);
		
		account = accountService.findAccountByActor(student);
		
		accountCharges = accountService.findPaidAccountCharges(filter,account,offset,limit);
		
		return self();	
	}
}
