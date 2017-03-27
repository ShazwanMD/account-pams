package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcStudent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

/**
 * Created by PAMS on 3/8/2017.
 */
@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class WhenIAddAnAccountCharge extends Stage<WhenIAddAnAccountCharge> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenIAddAnAccountCharge.class);

    @Autowired
    private AccountService accountService;

    @ExpectedScenarioState
    private AcAcademicSession academicSession;

    @ExpectedScenarioState
    private AcStudent student;

    @ExpectedScenarioState
    private AcAccount account;

    @ProvidedScenarioState
    private AcAccountCharge accountCharge;

    public WhenIAddAnAccountCharge I_add_an_account_charge(double chargeAmount) {
        academicSession = accountService.findCurrentAcademicSession();

        // accountCharge
        accountCharge = new AcAccountChargeImpl();
        accountCharge.setReferenceNo("CHRG-" + System.currentTimeMillis());
        accountCharge.setSourceNo("abc123");
        accountCharge.setAmount(BigDecimal.valueOf(chargeAmount));
        accountCharge.setDescription("This is a test");
        accountCharge.setSession(academicSession);
        accountCharge.setChargeType(AcAccountChargeType.STUDENT_AFFAIRS);
        accountService.addAccountCharge(account, accountCharge);

        final String entityName = accountCharge.getClass().getSimpleName();
        Assert.notNull(accountCharge.getId(), entityName + " must have Id");
        return self();
    }

    @Pending
    public void I_show_charges_by_account_for_accountNo_$(String accountNo) {
    }
}
