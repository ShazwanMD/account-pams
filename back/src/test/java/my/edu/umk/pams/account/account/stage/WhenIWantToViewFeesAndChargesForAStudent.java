package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@JGivenStage
public class WhenIWantToViewFeesAndChargesForAStudent extends Stage<WhenIWantToViewFeesAndChargesForAStudent> {
	private static final Logger LOG = LoggerFactory.getLogger(WhenIWantToViewFeesAndChargesForAStudent.class);

	@ExpectedScenarioState
	private AcStudent student;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ProvidedScenarioState
	private AcAccount account;

	@Autowired
	private AccountService accountService;

	// public WhenIWantViewCompoundBill I_want_view_my_compound_bill(String
	// matricNo)

	@Pending
	public WhenIWantToViewFeesAndChargesForAStudent I_want_to_view_fees_and_charges_for_a_student() {

		accountService.findAccountCharges(academicSession, account);

		return self();

	}

}
