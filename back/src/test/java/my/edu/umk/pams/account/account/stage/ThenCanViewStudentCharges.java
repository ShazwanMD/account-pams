package my.edu.umk.pams.account.account.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import my.edu.umk.pams.account.account.model.AcAccountCharge;

public class ThenCanViewStudentCharges extends Stage<ThenCanViewStudentCharges> {
	
	private static final Logger LOG = LoggerFactory.getLogger(ThenCanViewStudentCharges.class);
	
	@ExpectedScenarioState
    private List<AcAccountCharge> accountCharges;
	
	public ThenCanViewStudentCharges I_can_view_student_charges() {
		//LOG.debug(accountCharges);
		
		Assert.notNull(accountCharges, "There is charges");
		
		return self();
	}

}
