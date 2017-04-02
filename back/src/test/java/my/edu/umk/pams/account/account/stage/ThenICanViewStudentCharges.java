package my.edu.umk.pams.account.account.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

public class ThenICanViewStudentCharges {
	private static final Logger LOG = LoggerFactory.getLogger(ThenCanViewStudentCharges.class);

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ProvidedScenarioState
	private AcAccount account;

	@ExpectedScenarioState
	private AcAccountCharge accountCharge;

	@ProvidedScenarioState
	private AcStudent student;

	@Autowired
	private IdentityService identityService;


	@ExpectedScenarioState
	private AcChargeCode chargeCode;
	
	public void I_can_view_student_compound_charges_$(String code) {
		
		Assert.isNull(chargeCode, "There is no compound charges");
	}

}
