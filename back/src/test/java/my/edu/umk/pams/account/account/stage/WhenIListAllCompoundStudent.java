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
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;

@JGivenStage
public class WhenIListAllCompoundStudent extends Stage<WhenIListAllCompoundStudent> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIListAllCompoundStudent.class);

	@ExpectedScenarioState
	private AcStudent student;

	@ProvidedScenarioState
	private AcAccount account;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@Autowired
	private AccountService accountService;
	
	@ProvidedScenarioState
    private List<AcAccountCharge> accountCharges;
	
	@As("list all compound student of type academic")
	public WhenIListAllCompoundStudent I_want_to_list_all_compound_student_of_type_academic_$(String matricNo) {

		AcAccountChargeType chargeType = AcAccountChargeType.ADMISSION;
		List<AcAccountCharge> accountCharges = accountService.findAccountCharges(academicSession, chargeType);

		Assert.notEmpty(accountCharges, "Account Charges is empty");

		for (AcAccountCharge charges : accountCharges) {

			Assert.notNull(charges, "Charges is empty");

		}
		
		return self();
		
	}
		
}
