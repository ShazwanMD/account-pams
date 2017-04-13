package my.edu.umk.pams.account.account.stage;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcSecurityCharge;
import my.edu.umk.pams.account.account.model.AcSecurityChargeImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;

@JGivenStage
public class WhenRegisterStudentCompoundBill extends Stage<WhenRegisterStudentCompoundBill> {

	@ExpectedScenarioState
	private AcStudent student;

	@ProvidedScenarioState
	private AcAccount account;


	@ProvidedScenarioState
	private AcAccountCharge charge;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@Autowired
	private AccountService accountService;

	@As("I want to register student compound bill")
	public WhenRegisterStudentCompoundBill I_want_to_register_student_compound_bill() {
		// find student account

		account = accountService.findAccountByActor(student);

		// add charges to student account
		AcSecurityCharge charge = new AcSecurityChargeImpl();

		charge.setReferenceNo("REFNO/" + System.currentTimeMillis());
		charge.setSourceNo("SCTY - 001");
		charge.setDescription("TIADA KAD MATRIK");
		charge.setAmount(BigDecimal.valueOf(80.00));
		charge.setChargeCode(accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79335"));
		charge.setSession(academicSession);

		// use account service to add charge
		accountService.addAccountCharge(account, charge);
		return self();
	}

}
