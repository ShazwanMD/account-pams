package my.edu.umk.pams.account.billing.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;

@JGivenStage
public class WhenIPayCompound extends Stage<WhenIPayCompound> {
	private static final Logger LOG = LoggerFactory.getLogger(WhenIPayCompound.class);
	
    @ExpectedScenarioState
    private AcStudent student;

    @ProvidedScenarioState
    private AcAccount account;
    
    @Autowired
    private AccountService accountService;
    
    public WhenIPayCompound I_pay_the_compound(){
		return self();}


}
