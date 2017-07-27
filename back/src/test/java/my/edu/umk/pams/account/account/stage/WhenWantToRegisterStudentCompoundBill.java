package my.edu.umk.pams.account.account.stage;

import java.math.BigDecimal;

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
import my.edu.umk.pams.account.account.model.AcAccountChargeImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class WhenWantToRegisterStudentCompoundBill extends Stage<WhenWantToRegisterStudentCompoundBill> {
	
	private static final Logger LOG = LoggerFactory.getLogger(WhenWantToRegisterStudentCompoundBill.class);

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

	@As("I want to register student compound bill by account")
	public WhenWantToRegisterStudentCompoundBill I_want_to_register_student_compound_bill_by_account_$(String matricNo) {
		
		student = identityService.findStudentByMatricNo(matricNo);	
		LOG.debug("Matric No:"+ student.getMatricNo());
		
		account = accountService.findAccountByActor(student);
		LOG.debug("Name:"+ student.getName());
		
		charge = new AcAccountChargeImpl();
		charge.setReferenceNo("REFNO/" + System.currentTimeMillis());
		charge.setSourceNo("SRCNO");
		charge.setDescription("BAJU TIDAK BERKOLAR");
		charge.setAmount(BigDecimal.valueOf(50.00));	
		charge.setSession(academicSession);
		charge.setAccount(account);

		accountService.addAccountCharge(account, charge);

		return self();
	}

}
