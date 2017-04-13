package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

@JGivenStage
public class WhenWantToListAllCompoundStudent extends Stage<WhenWantToListAllCompoundStudent> {

	@Autowired
	private AccountService accountService;

	@ProvidedScenarioState
	private AcStudent student;

	@ProvidedScenarioState
	private AcAccount account;

	@ProvidedScenarioState
	List<AcAccountCharge> accountCharges;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@As("I want to list all compound student")
	public WhenWantToListAllCompoundStudent I_want_to_list_all_compound_student_$(String matricNo) {

		AcAccountChargeType chargeType = AcAccountChargeType.SECURITY;

		accountCharges = accountService.findAccountCharges(academicSession, chargeType);

		Assert.notEmpty(accountCharges, "Account Charges is empty");

		for (AcAccountCharge charges : accountCharges) {

			Assert.notNull(charges, "Charges is empty");

		}

		return self();

	}

}
