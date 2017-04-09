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

import my.edu.umk.pams.account.account.model.AcAcademicCharge;
import my.edu.umk.pams.account.account.model.AcAcademicChargeImpl;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcSecurityCharge;
import my.edu.umk.pams.account.account.model.AcSecurityChargeImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class WhenIFillInStudentCompound extends Stage<WhenIFillInStudentCompound> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIFillInStudentCompound.class);

	@Autowired
	private IdentityService identityService;
	
	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ProvidedScenarioState
	private AcAccount account;


	@ProvidedScenarioState
	private AcStudent student;

	@Autowired
	private AccountService accountService;



	public WhenIFillInStudentCompound I_fill_in_student_compound_$(String matricNo,
			String Code){
		

		student = identityService.findStudentByMatricNo(matricNo);

		account = accountService.findAccountByActor(student);

		AcAcademicCharge charge1 = new AcAcademicChargeImpl();

		charge1.setReferenceNo("REFNO/" + System.currentTimeMillis());
		charge1.setSourceNo("ACD - 001");
		charge1.setDescription("BAYAR YURAN LEWAT");
		charge1.setAmount(BigDecimal.valueOf(100.00));
		charge1.setChargeCode(accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79333"));
		charge1.setSession(academicSession);

		// use account service to add charge
		accountService.addAccountCharge(account, charge1);
		
		AcSecurityCharge charge2 = new AcSecurityChargeImpl();

		charge2.setReferenceNo("REFNO/" + System.currentTimeMillis());
		charge2.setSourceNo("SCTY - 001");
		charge2.setDescription("BAYAR YURAN LEWAT");
		charge2.setAmount(BigDecimal.valueOf(100.00));
		charge2.setChargeCode(accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79333"));
		charge2.setSession(academicSession);

		// use account service to add charge
		accountService.addAccountCharge(account, charge2);

		return self();
	}

}

