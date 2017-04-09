package my.edu.umk.pams.account.account.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeType;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcStudent;

@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class ThenICanViewStudentsCompoundCharges extends Stage <ThenICanViewStudentsCompoundCharges> {
	
	private static final Logger LOG = LoggerFactory.getLogger(WhenWantToListAllCompoundStudent.class);

	@Autowired
	private AccountService accountService;

	@ProvidedScenarioState
	private AcStudent student;

	@ProvidedScenarioState
	private AcAccount account;

	@ExpectedScenarioState
	List<AcAccountCharge> accountCharges;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;
	
	public ThenICanViewStudentsCompoundCharges can_view_students_compound_charges(){
		
		AcAccountChargeType chargeType = AcAccountChargeType.SECURITY;
		List<AcAccountCharge> accountCharge = accountService.findAccountCharges(chargeType);
		
		for (AcAccountCharge charge : accountCharge) {
			
			Assert.notNull(charge, "Charges is empty");

			LOG.debug("Student Name :" + charge.getAccount().getActor().getName());
			LOG.debug("Charge Type :" + charge.getChargeType());
			LOG.debug("Reference No : " + charge.getReferenceNo());
			LOG.debug("Description : " + charge.getDescription());
			
		}
		return self();
	}
}

