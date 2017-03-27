package my.edu.umk.pams.account.account.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.account.service.AccountService;

public class ThenCanViewStudentCharges extends Stage<ThenCanViewStudentCharges> {

	private static final Logger LOG = LoggerFactory.getLogger(ThenCanViewStudentCharges.class);

	@Autowired
	private AccountService accountService;
	
	@ProvidedScenarioState
    private AcAccountCharge accountchrg;
	
	@ExpectedScenarioState
	private List<AcAccountCharge> accountCharges;

	@ExpectedScenarioState
	private AcChargeCode chargeCode;

	public ThenCanViewStudentCharges I_can_view_student_charges() {
		//LOG.debug(accountCharges);
		Assert.notNull(accountCharges, "There is charges");
		return self();
	}

	public ThenCanViewStudentCharges I_can_view_student_compound_charges_$(String Code){
		
		Assert.notNull(chargeCode, "There is compound charges");
		return self();
	}

}
