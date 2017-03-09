package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.service.AccountService;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

    // todo: use $ placeholder
    public void student_account_is_charged(){
        LOG.debug("student account is charged {}");
        List<AcAccountCharge> charges = accountService.findAccountCharges(academicSession, account);
        Assert.assertTrue(!(charges.isEmpty()));

    }
}
