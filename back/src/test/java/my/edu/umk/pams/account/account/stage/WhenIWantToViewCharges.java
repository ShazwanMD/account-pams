package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeImpl;
import my.edu.umk.pams.account.account.model.AcAccountChargeType;
import my.edu.umk.pams.account.account.model.AcSecurityCharge;
import my.edu.umk.pams.account.account.model.AcSecurityChargeImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@JGivenStage
public class WhenIWantToViewCharges extends Stage<WhenIWantToViewCharges> {
	private static final Logger LOG = LoggerFactory.getLogger(WhenIWantToViewCharges.class);

	@ExpectedScenarioState
	private AcStudent student;
	
	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ProvidedScenarioState
	private AcAccount account;

	
	@Autowired
	private AccountService accountService;

	@Autowired
	private IdentityService identityservice;
	
	//public WhenIWantViewCompoundBill I_want_view_my_compound_bill(String matricNo)

	public WhenIWantToViewCharges I_want_to_view_charges(){
		
		List<AcAccountCharge> charges = accountService.findAccountCharges(academicSession, account);
		

		
		return self();
		
		
	}

}
