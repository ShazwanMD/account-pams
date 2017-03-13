package my.edu.umk.pams.account.billing.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;



@JGivenStage
public class ThenStudentAccountIsCharged extends Stage<ThenStudentAccountIsCharged> {

	@ExpectedScenarioState
	AcAccount account;
	
	@Autowired
	private AccountService accountService;
	
    public ThenStudentAccountIsCharged my_account_is_charged() {

    	Assert.isTrue(accountService.hasBalance(account));
    	
        return self();
    }

}
