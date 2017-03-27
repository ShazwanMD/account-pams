package my.edu.umk.pams.account.account.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class WhenListStudentChargesOfTypeCompoundByAccount extends Stage<WhenListStudentChargesOfTypeCompoundByAccount>{
	private static final Logger LOG = LoggerFactory.getLogger(WhenListStudentChargesOfTypeCompoundByAccount.class);
	@Autowired
	private IdentityService identityService;

	@Autowired
	private AccountService accountService;
	
	@ProvidedScenarioState
    private AcAccount account;
	
	@ProvidedScenarioState
    private AcAccountCharge accountchrg;
	
	@ProvidedScenarioState
    private AcStudent student;
	
	@ProvidedScenarioState
    private List<AcAccountCharge> accountCharges;
	
	@ProvidedScenarioState
    private AcChargeCode chargeCode;
	
	@As("I want to list student charges of type compound by account")
	public WhenListStudentChargesOfTypeCompoundByAccount I_want_to_list_student_charges_of_type_compound_by_account_$(String matricNo, String Code) {
		
		student = identityService.findStudentByMatricNo(matricNo);
		
		account = accountService.findAccountByActor(student);
		
		chargeCode = accountService.findChargeCodeByCode(Code);
		
		accountCharges = account.getCharges();
				
		return self();	
	}
}
