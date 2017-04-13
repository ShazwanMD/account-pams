package my.edu.umk.pams.account.account.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeType;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;

@JGivenStage
public class WhenListStudentChargesOfTypeCompoundByAccount extends
		Stage<WhenListStudentChargesOfTypeCompoundByAccount> {
	private static final Logger LOG = LoggerFactory
			.getLogger(WhenListStudentChargesOfTypeCompoundByAccount.class);

	@Autowired
	private AccountService accountService;

	@ProvidedScenarioState
	private AcAccount account;

	@ProvidedScenarioState
	private AcStudent student;

	@ProvidedScenarioState
	private List<AcAccountCharge> accountCharges;

	@ProvidedScenarioState
	private AcChargeCode chargeCodes;
	
	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	// private List<AcChargeCode> chargeCodes;

	@As("I want to list student charges of type compound by account")
	public WhenListStudentChargesOfTypeCompoundByAccount I_want_to_list_student_charges_of_type_compound_by_account_$(
			String matricNo, String Code) {

		AcAccountChargeType chargeType = AcAccountChargeType.ACADEMIC;
		List<AcAccountCharge> accountCharges = accountService.findAccountCharges(academicSession, chargeType);

		//NOT EMPTY : TO CHECK COLLECTION DATA
		Assert.notEmpty(accountCharges, "Account Charges is empty");

		for (AcAccountCharge charges : accountCharges) {

			//NOT NULL : TO CHECK DATA
			Assert.notNull(charges, "Charges is empty");

		}
		
		return self();
	}
}
