package my.edu.umk.pams.account.account.stage;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeImpl;
import my.edu.umk.pams.account.account.model.AcSecurityCharge;
import my.edu.umk.pams.account.account.model.AcSecurityChargeImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;


@JGivenStage
public class WhenIWantViewCompoundBill extends Stage<WhenIWantViewCompoundBill>{
	private static final Logger LOG = LoggerFactory.getLogger(WhenIWantViewCompoundBill.class);
	
	@ExpectedScenarioState
    private AcStudent student;

    @ProvidedScenarioState
    private AcAccount account;

    @ExpectedScenarioState
    private AcAcademicSession academicSession;
    
    @Autowired
    private AccountService accountService;

    public WhenIWantViewCompoundBill I_want_view_my_compound_bill() {
    	
        // find student account
    	/*    account = accountService.findAccountByActor(student);
       
        
        
        // add charges to student account
        AcSecurityCharge charge = new AcSecurityChargeImpl();
        charge.setReferenceNo("REFNO/" + System.currentTimeMillis());
        charge.setSourceNo("SRCNO");
        charge.setDescription("tatatataa");
        charge.setAmount(BigDecimal.valueOf(100.00));
        charge.setChargeCode(accountService.findChargeCodeByCode("AC-0001"));
        charge.setSession(academicSession);
        
        
        
        //get student charges
        AcAccountCharge charge = new AcAccountChargeImpl();
        charge.setReferenceNo("REFNO/");
        charge.setSourceNo("SRCNO");
        charge.setDescription("tatatataa");
        charge.setAmount(BigDecimal.valueOf(100.00));
        charge.setSession(academicSession);
           

        // use account service to add charge
        accountService.addAccountCharge(account, charge); 
*/
        return self();
    }

}
