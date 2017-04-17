package my.edu.umk.pams.account.billing.stage;

import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;

@JGivenStage
public class ThenAReceiptIsGenerated extends Stage<ThenAReceiptIsGenerated> {

	@ExpectedScenarioState
	private AcAccount account;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@As("Payment receipt will be generated")
	public ThenAReceiptIsGenerated Payment_receipt_generated() {
		// List<AcAccountCharge> charges =
		// accountService.findAccountCharges(academicSession, account);
		// accountService.findChargeCodeByCode();
		// Assert.isTrue(!charges.isEmpty());

		Assert.notNull(academicSession, "academic session is a prerequisite");
		return self();

	}

}
