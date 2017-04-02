package my.edu.umk.pams.account.account.stage;

import java.util.List;

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
import my.edu.umk.pams.account.account.model.AcAccountChargeType;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class WhenListStudentCharges extends Stage<WhenListStudentCharges> {
	private static final Logger LOG = LoggerFactory
			.getLogger(WhenListStudentCharges.class);

	@ExpectedScenarioState
	private AcStudent student;

	@ProvidedScenarioState
	private AcAccount account;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@Autowired
	private AccountService accountService;

	@Autowired
	private IdentityService identityService;

	@ProvidedScenarioState
	private List<AcAccountCharge> accountCharges;

	@ProvidedScenarioState
	 private AcChargeCode chargeCodes;
	// private List<AcChargeCode> chargeCodes;
	
	public WhenListStudentCharges I_want_to_list_student_charges_of_type_academic_by_account_$(
			String matricNo, String Code) {

		student = identityService.findStudentByMatricNo(matricNo);

		account = accountService.findAccountByActor(student);
		
		chargeCodes = accountService.findChargeCodeByCode(Code);
		
		List<AcAccountCharge> accountCharge = accountService.findAccountCharges(account);

		for (AcAccountCharge charge : accountCharge) {

			LOG.debug("Charge Type :" + charge.getChargeType("ACADEMIC"));
			//LOG.debug("Charge Type :" + charge.getChargeType());
			LOG.debug("Description : " + charge.getDescription());
		}
		
		//for (AcChargeCode chargeCodes : chargeCodes) {		
			//LOG.debug("Code : " + chargeCodes.getCode());
			//LOG.debug("Description : " + chargeCodes.getDescription());
		//}
		
		return self();
	}
}
