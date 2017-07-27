package my.edu.umk.pams.account.billing.stage;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeImpl;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

/**
 * @author PAMS
 */
@JGivenStage
public class WhenIssueInvoice extends Stage<WhenIssueInvoice> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIssueInvoice.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private BillingService billingService;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ExpectedScenarioState
	private AcStudent student;

	@ProvidedScenarioState
	private AcAccount account;

	@ProvidedScenarioState
	private AcAccountCharge charge;

	@ProvidedScenarioState
	private AcChargeCode chargeCode;

	public WhenIssueInvoice I_charge_student_with_academic_fees() {

		return self();
	}

	@As("I create security charge to student")
	public WhenIssueInvoice I_create_security_charge_to_student(String matricNo) {
		AcStudent student = identityService.findStudentByMatricNo(matricNo);
		account = accountService.findAccountByActor(student);

		charge = new AcAccountChargeImpl();
		charge.setReferenceNo("CHRG-" + System.currentTimeMillis());
		charge.setSourceNo("abc123");
		charge.setAmount(BigDecimal.valueOf(20.00));
		charge.setDescription("chargeA");
		charge.setSession(academicSession);
		charge.setReferenceNo("SEC_CHRGE" + System.currentTimeMillis());

		chargeCode = accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79321");
		Assert.notNull(chargeCode, "chargeCode cannot be null");

		accountService.addAccountCharge(account, charge);

		LOG.debug("Student :--> " + student.getIdentityNo());

		return self();

	}
}
