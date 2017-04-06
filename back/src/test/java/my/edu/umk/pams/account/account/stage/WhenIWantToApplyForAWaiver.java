package my.edu.umk.pams.account.account.stage;

import java.math.BigDecimal;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplicationImpl;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class WhenIWantToApplyForAWaiver extends Stage<WhenIWantToApplyForAWaiver> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenIWantToApplyForAWaiver.class);

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private FinancialAidService financialAidService;

    @ExpectedScenarioState
    private AcAccount account;
    
    @ProvidedScenarioState
	private AcWaiverApplication waiverApplication;
    
    /*
	 * scenario 
	 * student balance = 3000
	 * waived amount = 2800
	 * graced amount = 200
	 * Effective balance = balance - (waived amount + graced amount)
	 *
	 * 
	 * */
    
    private BigDecimal balance = new BigDecimal("3000");
    
    private BigDecimal waivedAmount = new BigDecimal("2800");
    
    private BigDecimal effectiveBalance = new BigDecimal("0");
    
    private BigDecimal gracedAmount = new BigDecimal("200");
    
    public WhenIWantToApplyForAWaiver I_want_to_apply_for_a_waiver() {
    	
    	waiverApplication = new AcWaiverApplicationImpl();
    	waiverApplication.setAccount(account);
    	waiverApplication.setReferenceNo("qwe123");
    	waiverApplication.setBalance(balance);
    	waiverApplication.setWaivedAmount(waivedAmount);
    	waiverApplication.setGracedAmount(gracedAmount);
    	waiverApplication.setEffectiveBalance(effectiveBalance);
    	waiverApplication.setDescription("this is test of waiver application for student " + account.getActor().getName());
    	financialAidService.startWaiverApplicationTask(waiverApplication);
       
        return self();
    }


}
