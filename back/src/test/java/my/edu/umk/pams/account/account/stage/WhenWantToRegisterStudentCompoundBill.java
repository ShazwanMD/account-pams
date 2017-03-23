package my.edu.umk.pams.account.account.stage;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.account.model.AcStudentAffairChargeImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class WhenWantToRegisterStudentCompoundBill extends Stage<WhenWantToRegisterStudentCompoundBill> {
	private static final Logger LOG = LoggerFactory.getLogger(WhenIWantToRegisterStudentCompoundBill.class);

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
	
	@Autowired
	private IdentityService identityService;

	public WhenWantToRegisterStudentCompoundBill I_want_to_register_student_compound_bill_by_account_$(String matricNo) {
		
		student = identityService.findStudentByMatricNo(matricNo);
		
		account = accountService.findAccountByActor(student);
		
		charge = new AcStudentAffairChargeImpl();
		charge.setReferenceNo("REFNO/" + System.currentTimeMillis());
		charge.setSourceNo("SRCNO");
		charge.setDescription("Register Compound");
		charge.setAmount(BigDecimal.valueOf(50.00));
		charge.setSession(academicSession);
		charge.setAccount(account);

		accountService.addAccountCharge(account, charge);

		return self();
	}

}
