package my.edu.umk.pams.account.account.stage;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;


@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class WhenListAllCompoundStudent extends Stage<WhenListAllCompoundStudent>{
	
	private static final Logger LOG = LoggerFactory.getLogger(WhenListAllCompoundStudent.class);
	
	@Autowired
	private AccountService accountService;
	
	public WhenListAllCompoundStudent I_want_to_list_all_compound_student() {
		
		AcAccount account = accountService.findAccountByCode("A17P001");
		
		
		List<AcAccountCharge> charges = accountService.findAccountCharges(account);
		
		for (AcAccountCharge accountcharge : charges) {
	            LOG.debug(accountcharge.getDescription());
	        }
	        
		return self();		
	}

}
