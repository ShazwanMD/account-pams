package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Pending;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

public class ThenIKnowTheCompoundAmount extends Stage<ThenIKnowTheCompoundAmount> {
    @ExpectedScenarioState
    private AcAccount account;

    @ExpectedScenarioState
    private AcAcademicSession academicSession;

    @Autowired
    private AccountService accountService;

    @Pending
    public ThenIKnowTheCompoundAmount I_know_the_compound_amount() {
        /*List<AcAccountCharge> charges = accountService.findAccountCharges(academicSession, account);
		Assert.isTrue(!charges.isEmpty());*/
        return self();
    }

}
