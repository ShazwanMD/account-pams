package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@JGivenStage
public class WhenIListStudentChargesOfTypeCompound extends Stage<WhenIListStudentChargesOfTypeCompound> {
	private static final Logger LOG = LoggerFactory.getLogger(WhenIListStudentChargesOfTypeCompound.class);

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ProvidedScenarioState
	private AcAccount account;

	@ProvidedScenarioState
	private AcAccountCharge accountCharge;

	@ProvidedScenarioState
	private AcStudent student;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private IdentityService identityservice;

	@Autowired
	private AccountService accountservice;

	@Pending
	public void I_list_student_charges_of_type_compound_$(String matricNo) {
		// guna identity service untuk find student by matric number
		AcStudent student = identityservice.findStudentByMatricNo(matricNo);

		// gunakan account service untuk dapatkan charges by student
		account = accountservice.findAccountByActor(student);

		// account service dapatkan charges
		List<AcAccountCharge> charges = accountservice.findAccountCharges(account);

		for (AcAccountCharge charge : charges) {
			charge.getAccount();
		}
	}
}
