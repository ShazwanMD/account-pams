package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcInvoice;
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
public class WhenAddStudentCompound extends Stage<WhenAddStudentCompound> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenAddStudentCompound.class);

	@Autowired
	private IdentityService identityService;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ProvidedScenarioState
	private AcAccount account;

	@ProvidedScenarioState
	private AcStudent student;

	@ExpectedScenarioState
	private AcInvoice invoice;

	@Autowired
	private AccountService accountService;

	@As("I add student compound")
	public WhenAddStudentCompound I_add_student_compound_$(String matricNo) {

		student = identityService.findStudentByMatricNo(matricNo);
		account = accountService.findAccountByActor(student);

		AcAccountCharge charge1 = new AcAccountChargeImpl();

		charge1.setReferenceNo("REFNO/" + System.currentTimeMillis());
		charge1.setSourceNo("ACD - 001");
		charge1.setDescription("BAYAR YURAN LEWAT");
		charge1.setAmount(BigDecimal.valueOf(80.00));
		// cohort, study mode
		charge1.setSession(academicSession);

		// use account service to add charge
		accountService.addAccountCharge(account, charge1);

		return self();
	}

	@As("I add security compound")
	public WhenAddStudentCompound I_add_security_compound_$(String matricNo, String code) {

		student = identityService.findStudentByMatricNo(matricNo);
		account = accountService.findAccountByActor(student);

		AcAccountCharge charge = new AcAccountChargeImpl();
		charge.setReferenceNo("REFNO/" + System.currentTimeMillis());
		charge.setSourceNo("ACD - 002");
		charge.setDescription("TAK PAKAI KASUT");
		charge.setAmount(BigDecimal.valueOf(80.00));
		charge.setSession(academicSession);
		charge.setInvoice(invoice);

		// use account service to add charge
		accountService.addAccountCharge(account, charge);
		LOG.debug("charge " + charge);
		return self();
	}
}
