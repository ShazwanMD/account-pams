package my.edu.umk.pams.account.billing.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcSecurityCharge;
import my.edu.umk.pams.account.account.model.AcSecurityChargeImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.security.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@JGivenStage
public class WhenSecurityChargeMeCompound extends Stage<WhenSecurityChargeMeCompound> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenSecurityChargeMeCompound.class);

    @ExpectedScenarioState
    private AcStudent student;

    @ProvidedScenarioState
    private AcAccount account;

    @ExpectedScenarioState
    private AcAcademicSession academicSession;

    @Autowired
    private CommonService commonService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private AccountService accountService;

    public WhenSecurityChargeMeCompound security_issues_me_compound_charges() {

        // find student account
        account = accountService.findAccountByActor(student);

        // add charges to student account
        AcSecurityCharge charge = new AcSecurityChargeImpl();
        charge.setReferenceNo("REFNO");
        charge.setSourceNo("SRCNO");
        charge.setDescription("description");
        charge.setAmount(BigDecimal.valueOf(100.00));
        charge.setChargeCode(accountService.findChargeCodeByCode("AC-0001"));
        charge.setSession(academicSession);

        // use account service to add charge
        accountService.addAccountCharge(account, charge);

        return self();
    }
}
