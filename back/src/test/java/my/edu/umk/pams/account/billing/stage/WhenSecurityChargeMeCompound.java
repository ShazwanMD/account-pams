package my.edu.umk.pams.account.billing.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcSecurityCharge;
import my.edu.umk.pams.account.account.model.AcSecurityChargeImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.security.service.SecurityService;

@JGivenStage
public class WhenSecurityChargeMeCompound extends Stage<WhenSecurityChargeMeCompound> {
	
	  private static final Logger LOG = LoggerFactory.getLogger(WhenSecurityChargeMeCompound.class);
	  
	    @ProvidedScenarioState
	    private double compoundFees = 20.60;
	    
	    @ExpectedScenarioState
	    private AcStudent student;
	    
	    @ProvidedScenarioState
	    private AcAccount account;
	    
	    @Autowired
	    private SecurityService securityService;
	    
	    @Autowired
	    private AccountService accountService;
	    

	    public WhenSecurityChargeMeCompound security_charge_me_compound() {
	    	
	    	  // find student account
	    	   account = accountService.findAccountByActor(student);
	    	
	    	  // add charges to student account
	    	  AcSecurityCharge charge = new AcSecurityChargeImpl();
	    	  // charge.set
	    	  // todo
	    	  
	    	  // use account service to add charge
	    	  accountService.addAccountCharge(account, charge);
	    	  
	    	   return self();
	    }

}
