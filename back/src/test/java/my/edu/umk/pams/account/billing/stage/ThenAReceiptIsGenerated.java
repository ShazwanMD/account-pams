package my.edu.umk.pams.account.billing.stage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.service.AccountService;

@JGivenStage
public class ThenAReceiptIsGenerated extends Stage<ThenAReceiptIsGenerated> {
	@ExpectedScenarioState
	private AcAccount account;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@Autowired
	private AccountService accountService;
	

	public ThenAReceiptIsGenerated Payment_receipt_generated() {
		//List<AcAccountCharge> charges = accountService.findAccountCharges(academicSession, account);
	//	accountService.findChargeCodeByCode();
		//Assert.isTrue(!charges.isEmpty());
   
		return self();
		// TODO Auto-generated method stub
		
	}

}
