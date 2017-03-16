package my.edu.umk.pams.account.account.stage;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicCharge;
import my.edu.umk.pams.account.account.model.AcAcademicChargeImpl;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcStudent;


@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class WhenIListAllCompoundStudent extends Stage<WhenIListAllCompoundStudent> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIListAllCompoundStudent.class);

	@ExpectedScenarioState
    private AcStudent student;

    @ProvidedScenarioState
    private AcAccount account;

    @ExpectedScenarioState
    private AcAcademicSession academicSession;

    @Autowired
    private AccountService accountService;
	
	public WhenIListAllCompoundStudent list_all_compound_student() {
		
		// find student account
        account = accountService.findAccountByActor(student);
        
        // add charges to student account
        AcAcademicCharge charge = new AcAcademicChargeImpl();
        charge.setReferenceNo("REFNO/" + System.currentTimeMillis());
        charge.setSourceNo("SRCNO");
        charge.setDescription("Compound");
        charge.setAmount(BigDecimal.valueOf(100.00));
        charge.setChargeCode(accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79323"));
        charge.setSession(academicSession);

        // use account service to add charge
        accountService.addAccountCharge(account, charge);

        return self();
	}

}
