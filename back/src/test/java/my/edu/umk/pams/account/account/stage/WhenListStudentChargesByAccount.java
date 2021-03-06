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

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class WhenListStudentChargesByAccount extends
		Stage<WhenListStudentChargesByAccount> {
	private static final Logger LOG = LoggerFactory
			.getLogger(WhenListStudentChargesByAccount.class);

	@ExpectedScenarioState
	private AcStudent student;

	@ProvidedScenarioState
	private AcAccount account;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@Autowired
	private AccountService accountService;

	@Autowired
	private IdentityService identityService;

	@ProvidedScenarioState
	private List<AcAccountCharge> accountCharges;

	@As("I want to list student charges by account")
	public WhenListStudentChargesByAccount I_want_to_list_student_charges_by_account_$(
			String matricNo) {

		student = identityService.findStudentByMatricNo(matricNo);
		
		account = accountService.findAccountByActor(student);
		accountCharges = accountService.findAccountCharges(account);
		for (AcAccountCharge accountCharges : accountCharges) {
			LOG.debug("Identity Number : " + accountCharges.getAccount().getActor().getIdentityNo());
			LOG.debug("Name : " + accountCharges.getAccount().getActor().getName());
			LOG.debug("Source Number : " + accountCharges.getSourceNo());
			LOG.debug("Description : " + accountCharges.getDescription());
			LOG.debug("Session : " + accountCharges.getSession().getCode());
			LOG.debug("");
		}

		return self();
	}
}
