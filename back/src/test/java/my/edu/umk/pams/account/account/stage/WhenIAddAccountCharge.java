package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@JGivenStage
public class WhenIAddAccountCharge extends Stage<WhenIAddAccountCharge> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIAddAccountCharge.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private IdentityService identityService;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ProvidedScenarioState
	private AcAccount account;

	@ProvidedScenarioState
	AcAccountCharge accountCharge;

	@ProvidedScenarioState
    AcAdmissionCharge academicCharge;

	@As("I add account charge for account")
	public WhenIAddAccountCharge I_add_account_charge_for_account_$(String code) {
		LOG.debug("when i add account charge on " + academicSession.getCode());

		account = accountService.findAccountByCode(code);

		// charge
		AcAdmissionCharge charge = new AcAdmissionChargeImpl();
		charge.setReferenceNo("abc123");
		charge.setSourceNo("abc123");
		charge.setAmount(BigDecimal.valueOf(200.00));
		charge.setDescription("This is a test");
		charge.setSession(academicSession);
		// todo: cohort, studymode
		accountService.addAccountCharge(account, charge);

		return self();
	}

	@As("I add account charge of student")
	public WhenIAddAccountCharge I_pick_student_account_and_add_account_charge_of_$(String matricNo,
			double chargeAmount) {
		Assert.notNull(academicSession.getCode(), "academic session record is a prerequite");

		// find student and account
		AcStudent student = identityService.findStudentByMatricNo(matricNo);
		account = accountService.findAccountByActor(student);

		// add accountCharge
		accountCharge = new AcAccountChargeImpl();
		accountCharge.setReferenceNo("CHRG-" + System.currentTimeMillis());
		accountCharge.setSourceNo("abc123");
		accountCharge.setAmount(BigDecimal.valueOf(chargeAmount));
		accountCharge.setDescription("This is a test");
		accountCharge.setSession(academicSession);
		accountCharge.setChargeType(AcAccountChargeType.STUDENT_AFFAIRS);
		accountService.addAccountCharge(account, accountCharge);

		return self();
	}

	@As("I add account charge for academic charge")
	public WhenIAddAccountCharge I_pick_student_account_and_add_academic_charge(String matricNo) {
		// find student and account
		AcStudent student = identityService.findStudentByMatricNo(matricNo);
		account = accountService.findAccountByActor(student);

		// charge
		AcAdmissionCharge charge = new AcAdmissionChargeImpl();
		charge.setReferenceNo("CHRG-" + System.currentTimeMillis());
		charge.setSourceNo("abc123");
		charge.setAmount(BigDecimal.valueOf(200.00));
		charge.setDescription("This is a test");
		charge.setSession(academicSession);
		// todo: cohort, studymode
		accountService.addAccountCharge(account, charge);

		return self();
	}
}
