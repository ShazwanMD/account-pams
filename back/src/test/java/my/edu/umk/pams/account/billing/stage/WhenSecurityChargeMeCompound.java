package my.edu.umk.pams.account.billing.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcSecurityCharge;
import my.edu.umk.pams.account.account.model.AcSecurityChargeImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@JGivenStage
public class WhenSecurityChargeMeCompound extends Stage<WhenSecurityChargeMeCompound> {

    @Autowired
    private AccountService accountService;

    @ExpectedScenarioState
    private AcStudent student;

    @ProvidedScenarioState
    private AcAccount account;

    @ExpectedScenarioState
    private AcAcademicSession academicSession;

    @As("Security issues my compound charges")
    public WhenSecurityChargeMeCompound security_issues_me_compound_charges() {
        // add charges to given student account
        AcSecurityCharge charge = new AcSecurityChargeImpl();
        charge.setReferenceNo("REFNO/" + System.currentTimeMillis());
        charge.setSourceNo("SRCNO");
        charge.setDescription("Charge is:");
        charge.setAmount(BigDecimal.valueOf(100.00));
        charge.setChargeCode(accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79321"));
        charge.setSession(academicSession);
        accountService.addAccountCharge(account, charge);

        return self();
    }
}