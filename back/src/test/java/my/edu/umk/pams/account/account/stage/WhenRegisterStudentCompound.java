package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@JGivenStage
public class WhenRegisterStudentCompound extends Stage<WhenRegisterStudentCompound>{

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
    
    @As("I want to register student compound bill")
	public WhenRegisterStudentCompound I_want_to_register_student_compound_bill() {
		
		// find student account
	       account = accountService.findAccountByActor(student);
		
		// add charges to student account
       AcAdmissionCharge charge = new AcAdmissionChargeImpl();
   
       charge.setReferenceNo("REFNO/" + System.currentTimeMillis());
       charge.setSourceNo("ACD - 001");
       charge.setDescription("BAYAR YURAN LEWAT");
       charge.setAmount(BigDecimal.valueOf(100.00));
       // todo: cohort, study mode
       charge.setSession(academicSession);

        // use account service to add charge
        accountService.addAccountCharge(account, charge);
        
        return self();	
	}
}
