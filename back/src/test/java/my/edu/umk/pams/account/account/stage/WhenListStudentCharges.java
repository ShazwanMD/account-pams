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
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeType;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import org.springframework.util.Assert;

@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class WhenListStudentCharges extends Stage<WhenListStudentCharges> {
	private static final Logger LOG = LoggerFactory.getLogger(WhenListStudentCharges.class);


	@Autowired
	private AccountService accountService;


	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ProvidedScenarioState
	private AcAccountChargeType chargeType;

	@ProvidedScenarioState
	List<AcAccountCharge> accountCharges;

	public WhenListStudentCharges I_want_to_list_student_charges_of_type_academic_by_account_$(String matricNo,
			String Code) {


		AcAccountChargeType chargeType = AcAccountChargeType.ACADEMIC;
		List<AcAccountCharge> accountCharges = accountService.findAccountCharges(academicSession, chargeType);

		Assert.notEmpty(accountCharges, "Account Charges is empty");

		for (AcAccountCharge charges : accountCharges) {

			Assert.notNull(charges, "Charges is empty");

		}
		
		return self();
		
	}
		
		public WhenListStudentCharges I_want_to_list_student_charges_of_type_security_by_account_$(String matricNo,
				String Code) {


			AcAccountChargeType chargeType = AcAccountChargeType.SECURITY;
			List<AcAccountCharge> accountCharges = accountService.findAccountCharges(academicSession, chargeType);

			Assert.notEmpty(accountCharges, "Account Charges is empty");

			for (AcAccountCharge charges : accountCharges) {

				Assert.notNull(charges, "Charges is empty");

			}

		return self();
	}
}
