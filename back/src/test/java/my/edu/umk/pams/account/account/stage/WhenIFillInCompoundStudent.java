package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class WhenIFillInCompoundStudent extends Stage<WhenIFillInCompoundStudent>{
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
	
	public WhenIFillInCompoundStudent I_fill_in_student_compound_$(String matricNo,
			String Code){
		

		student = identityService.findStudentByMatricNo(matricNo);

		account = accountService.findAccountByActor(student);

		AcAccountCharge charge1 = new AcAccountChargeImpl();

		charge1.setReferenceNo("REFNO/" + System.currentTimeMillis());
		charge1.setSourceNo("ACD - 001");
		charge1.setDescription("BAYAR YURAN LEWAT");
		charge1.setAmount(BigDecimal.valueOf(80.00));
		// todo: cohort, studymode
		charge1.setSession(academicSession);

		// use account service to add charge
		accountService.addAccountCharge(account, charge1);
		
		AcAccountCharge charge2 = new AcAccountChargeImpl();

		charge2.setReferenceNo("REFNO/" + System.currentTimeMillis());
		charge2.setSourceNo("SCTY - 001");
		charge2.setDescription("TIADA KAD MATRIK");
		charge2.setAmount(BigDecimal.valueOf(90.00));
		charge2.setSession(academicSession);

		// use account service to add charge
		accountService.addAccountCharge(account, charge2);
		
		AcAccountCharge charge3 = new AcAccountChargeImpl();
		charge3.setReferenceNo("REFNO/" + System.currentTimeMillis());
		charge3.setSourceNo("STDA - 001");
		charge3.setDescription("BARANG ELEKTRIK TIDAK DAFTAR");
		charge3.setAmount(BigDecimal.valueOf(100.00));
		charge3.setSession(academicSession);

		// use account service to add charge
		accountService.addAccountCharge(account, charge3);
		return self();

	}

}
