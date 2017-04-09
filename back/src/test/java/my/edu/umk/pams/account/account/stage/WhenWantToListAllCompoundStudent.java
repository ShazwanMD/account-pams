package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcStudent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import java.util.List;

@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class WhenWantToListAllCompoundStudent extends Stage<WhenWantToListAllCompoundStudent> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenWantToListAllCompoundStudent.class);

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
