package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import org.springframework.util.Assert;

@JGivenStage
public class ThenTheAccountIsAdded extends Stage<ThenTheAccountIsAdded> {

    @ExpectedScenarioState
    private AccountService accountService;

    @ExpectedScenarioState
    private Long accountId;

    @As("The student account is added")
    public ThenTheAccountIsAdded the_student_account_is_added(){

        AcAccount account = accountService.findAccountById(accountId);

        final String entityName = account.getClass().getSimpleName();
        Assert.notNull(account.getId(), "No " + entityName + " found with id " + account.getId());
        Assert.notNull(account.getCode(), "No " + entityName + " found with code " + account.getCode());

        return self();
    }
}
