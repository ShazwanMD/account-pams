package my.edu.umk.pams.account.account.stage;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcStudentAffairCharge;
import my.edu.umk.pams.account.account.model.AcStudentAffairChargeImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcActorType;
import my.edu.umk.pams.account.identity.model.AcStudent;
@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class WhenIWantToRegisterStudentCompoundBill extends Stage<WhenIWantToRegisterStudentCompoundBill>{
	private static final Logger LOG = LoggerFactory.getLogger(WhenIWantToRegisterStudentCompoundBill.class);

    @ExpectedScenarioState
    private AcStudent student;

    @ProvidedScenarioState
    private AcAccount account;
    
    @ProvidedScenarioState
    private AcAccountCharge charge;

    @ExpectedScenarioState
    private AcAcademicSession academicSession;

    @Autowired
    private AccountService accountService;
/*
    public WhenIWantToRegisterStudentCompoundBill ()
    {
    	 charge = new AcStudentAffairChargeImpl();
    }
    
    @As("I register compound bill")
    public WhenIWantToRegisterStudentCompoundBill I_Register_Compound_Bill() {
        
    	charge.setReferenceNo("REFNO/" + System.currentTimeMillis());
        charge.setSourceNo("SRCNO");
        charge.setDescription("test");
        charge.setAmount(BigDecimal.valueOf(100.00));
        accountService.addAccountCharge(account, charge);
        
        return self();
    }
 */   
    
  
    public WhenIWantToRegisterStudentCompoundBill I_Register_Compound_Bill() {
    	/*
    	 * As a Student Affair,  
    	 * I want to register student compound bill 
    	 * so that the compound details are record. 
    	 */
    	 
    	//1.  Find student account
    	//AcAccount account = accountService.findAccountByActor(student);
       // account = accountService.findAccountByActor(student);
    	// System.out.println("test");
        
        //2.  Add charges to student account
    	 charge = new AcStudentAffairChargeImpl();
    	//AcSecurityCharge charge = new AcSecurityChargeImpl();
        charge.setReferenceNo("REFNO/" + System.currentTimeMillis());
        charge.setSourceNo("SRCNO");
        charge.setDescription("Ta test");
        charge.setAmount(BigDecimal.valueOf(100.00));
        //charge.setChargeCode(accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79321"));
        charge.setSession(academicSession);

        //3.  Use account service to add charge
        accountService.addAccountCharge(account, charge);

        return self();
    }

}
