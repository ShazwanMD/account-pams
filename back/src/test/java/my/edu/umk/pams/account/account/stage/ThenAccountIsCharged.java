package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * @author PAMS
 */
@JGivenStage
public class ThenAccountIsCharged extends Stage<ThenAccountIsCharged> {

    private static final Logger LOG = LoggerFactory.getLogger(ThenAccountIsCharged.class);

    @Autowired
    private AccountService accountService;

    @ExpectedScenarioState
    AcAcademicSession academicSession;

    @ExpectedScenarioState
    AcAccount account;

    @ExpectedScenarioState
    AcAccountCharge accountCharge;

    public ThenAccountIsCharged student_account_is_charged_$(double chargeAmount){
        Assert.notNull(academicSession, "academic session is a prerequisite");
        Assert.notNull(accountCharge, "account charge is a prerequisite");

        accountCharge = accountService.findAccountChargeById(accountCharge.getId());

        final BigDecimal EXPECTED = BigDecimal.valueOf(200.00);
        BigDecimal amount = accountCharge.getAmount();

        String message = "expected " + EXPECTED + " but found " + amount;
        Assert.isTrue(amount.compareTo(EXPECTED) == 0, message);

        return self();
    }

    @Pending
    public void the_charges_for_the_student_are_listed() {

    }

    public ThenAccountIsCharged the_charges_are_misplaced(String message) {
        Assert.isTrue("WILL_NEVER_MATCH".equals("EVER"), message);
        return self();
    }
}
