package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.identity.model.AcStudent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JGivenStage
public class WhenIWantToViewMyPaymentSlip extends Stage<WhenIWantToViewMyPaymentSlip> {
	private static final Logger LOG = LoggerFactory.getLogger(WhenIWantToViewMyPaymentSlip.class);

	@ExpectedScenarioState
	private AcStudent student;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ProvidedScenarioState
	private AcAccount account;

	@Pending
	public WhenIWantToViewMyPaymentSlip I_want_to_view_my_payment_slip() {

		// todo: get data of previous payment

		return self();

	}

}
