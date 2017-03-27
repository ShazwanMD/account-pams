package my.edu.umk.pams.account.account.stage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.service.AccountService;

@JGivenStage
public class ThenCompoundDetailsRecord extends Stage<ThenCompoundDetailsRecord> {
	@ExpectedScenarioState
	private AcAccount account;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@Autowired
	private AccountService accountService;

	public ThenCompoundDetailsRecord the_compound_details_are_record() {
		List<AcAccountCharge> charges = accountService.findAccountCharges(academicSession, account);
		Assert.isTrue(!charges.isEmpty());
		return self();
		
	}
	
}
