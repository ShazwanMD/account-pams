package my.edu.umk.pams.account.account.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.service.AccountService;

@JGivenStage
public class ThenICanCheckMyPaymentStatus extends Stage<ThenICanCheckMyPaymentStatus> {

	private static final Logger LOG = LoggerFactory.getLogger(ThenICanCheckMyPaymentStatus.class);

	@Autowired
	private AccountService accountService;

	@ExpectedScenarioState
	AcAcademicSession academicSession;

	@ExpectedScenarioState
	AcAccount account;

	@ExpectedScenarioState
	AcAccountCharge accountCharge;

	@Pending
	public void I_can_check_my_payment_status() {
		
		//todo: check the status, make sure payment is cleared

	}

}
