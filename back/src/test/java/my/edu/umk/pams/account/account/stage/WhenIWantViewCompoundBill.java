package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeImpl;
import my.edu.umk.pams.account.account.model.AcAccountChargeType;
import my.edu.umk.pams.account.account.model.AcSecurityCharge;
import my.edu.umk.pams.account.account.model.AcSecurityChargeImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@JGivenStage
public class WhenIWantViewCompoundBill extends Stage<WhenIWantViewCompoundBill> {
	private static final Logger LOG = LoggerFactory.getLogger(WhenIWantViewCompoundBill.class);

	@ExpectedScenarioState
	private AcStudent student;

	@ProvidedScenarioState
	private AcAccount account;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@Autowired
	private AccountService accountService;

	@Autowired
	private IdentityService identityservice;
	
	//public WhenIWantViewCompoundBill I_want_view_my_compound_bill(String matricNo)

	public WhenIWantViewCompoundBill I_want_view_my_compound_bill(){
		/*
		 * // 1. guna identity service untuk find student by matric number
		 * AcStudent student = identityservice.findStudentByMatricNo(student);
		 * 
		 * // gunakan account service untuk dapatkan charges by student account
		 * account = accountservice.findAccountByActor(student);
		 * 
		 * // find student account //account =
		 * accountService.findAccountByActor(student);
		 * 
		 * // account = accountService.findAccountById("1");
		 * 
		 * //get student compound bill from account charge AcAccountCharge
		 * charge = new AcAccountChargeImpl(); charge.getReferenceNo();
		 * charge.getSourceNo(); charge.getDescription(); charge.getAmount();
		 * charge.getChargeType(); charge.getAccount(); charge.getSession();
		 * 
		 * // use account service to get charge
		 * 
		 * 
		 * // academicSession = accountService.find
		 * 
		 * // academicSession =
		 * accountService.findAcademicSessionByCode(academicSessionCode);
		 * 
		 * 
		 * // add charges to student account AcSecurityCharge charge = new
		 * AcSecurityChargeImpl(); charge.setReferenceNo("REFNO/" +
		 * System.currentTimeMillis()); charge.setSourceNo("SRCNO");
		 * charge.setDescription("tatatataa");
		 * charge.setAmount(BigDecimal.valueOf(100.00));
		 * charge.setChargeCode(accountService.findChargeCodeByCode(
		 * "TMGSEB-MBA-00-H79321")); charge.setSession(academicSession);
		 * 
		 * //get student charges AcAccountCharge charge = new
		 * AcAccountChargeImpl(); charge.setReferenceNo("REFNO/");
		 * charge.setSourceNo("SRCNO"); charge.setDescription("tatatataa");
		 * charge.setAmount(BigDecimal.valueOf(100.00));
		 * charge.setSession(academicSession);
		 * 
		 * // use account service to add charge
		 * accountService.addAccountCharge(account, charge);
		 * 
		 * 
		 * 
		 * //1. Guna identity service untuk find student by matric number
		 * AcStudent acstudent =
		 * identityservice.findStudentByMatricNo(StudentNo);
		 * 
		 * 
		 * // 1. find student account //account =
		 * accountService.findAccountByActor(student);
		 * 
		 * student = identityservice.findStudentByMatricNo("1");
		 * 
		 * // 2. get charges from student account AcAccountCharge charge = new
		 * AcAccountChargeImpl(); charge.getReferenceNo(); charge.getSourceNo();
		 * charge.getDescription(); charge.getAmount(); charge.getSession();
		 * 
		 * // 3. use account service to add charge
		 * accountService.findAccountCharges(account);
		 

		// 1. guna identity service untuk find student by matric number
		AcStudent student = identityservice.findStudentByMatricNo(matricNo);

		// 2. gunakan account service untuk dapatkan charges by student
		account = accountService.findAccountByActor(student);

		// 3. account service dapatkan charges
		List<AcAccountCharge> charges = accountService.findAccountCharges(account);

		for (AcAccountCharge charge : charges) {
			charge.getAccount();
		}
*/
		
		return self();
		
		
	}

}
