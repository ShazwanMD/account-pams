package my.edu.umk.pams.account.billing.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@JGivenStage
public class WhenIListAcademicChargesByAccount extends Stage<WhenIListAcademicChargesByAccount> {
    private static final Logger LOG = LoggerFactory.getLogger(WhenIListAcademicChargesByAccount.class);

    @ExpectedScenarioState
    private AcStudent student;

    @ProvidedScenarioState
    private AcAccount account;

    @ProvidedScenarioState
    private AcAccountChargeType chargeType;

    @Autowired
    private AccountService accountService;

    public WhenIListAcademicChargesByAccount I_list_charges_by_acount() {
        Assert.notNull(student, "Student cannot be null");

        account = new AcAccountImpl();
        Assert.notNull(account, "Account cannot be null");

        chargeType = AcAccountChargeType.ACADEMIC_LATE;

        AcAccountCharge charge = new AcAccountChargeImpl();
        charge.setChargeType(chargeType);

        return self();
    }
}
