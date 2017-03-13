package my.edu.umk.pams.account.billing.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.security.service.SecurityService;

@JGivenStage
public class WhenIPayCompound extends Stage<WhenIPayCompound> {
	
	  private static final Logger LOG = LoggerFactory.getLogger(WhenIPayCompound.class);
	  
	    @ProvidedScenarioState
	    private double compoundFees = 20.60;
	    
	    @ExpectedScenarioState
	    private AcUser currentUser;
	    
	    @ExpectedScenarioState
	    private SecurityService securityService;
	    
	    public WhenIPayCompound I_pay_compound() {
	    	   return self();
	    }

}
