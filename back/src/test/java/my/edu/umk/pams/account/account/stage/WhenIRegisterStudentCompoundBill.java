package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcStudent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class WhenIRegisterStudentCompoundBill extends Stage<WhenIRegisterStudentCompoundBill> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIRegisterStudentCompoundBill.class);

    @Autowired
    private AccountService accountService;

    @ExpectedScenarioState
    private AcAcademicSession academicSession;

    @ExpectedScenarioState
    private AcStudent student;

    @ExpectedScenarioState
    private AcAccount account;

	public WhenIRegisterStudentCompoundBill register_student_compound_bill() {
	    // register compound bill for given account
        // add charges to given student account
        AcAccountCharge charge = new AcAccountChargeImpl();
        charge.setReferenceNo("REFNO/" + System.currentTimeMillis());
        charge.setSourceNo("SRCNO");
        charge.setDescription("Charge is:");
        charge.setAmount(BigDecimal.valueOf(100.00));
        charge.setSession(academicSession);
        accountService.addAccountCharge(account, charge);
        return self();
	}
}
