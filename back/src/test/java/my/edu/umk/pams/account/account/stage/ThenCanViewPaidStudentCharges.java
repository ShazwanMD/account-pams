package my.edu.umk.pams.account.account.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import my.edu.umk.pams.account.account.model.AcAccountCharge;

public class ThenCanViewPaidStudentCharges extends Stage<ThenCanViewPaidStudentCharges> {
	
	private static final Logger LOG = LoggerFactory.getLogger(ThenCanViewPaidStudentCharges.class);
	
	@ExpectedScenarioState
    private List<AcAccountCharge> accountCharges;
	
	public ThenCanViewPaidStudentCharges I_can_view_paid_student_charges() {
		
		Assert.notEmpty(accountCharges, "This is list of paid charges");
		
		return self();
	}

}
