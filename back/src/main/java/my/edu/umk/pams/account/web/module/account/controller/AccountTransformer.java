package my.edu.umk.pams.account.web.module.account.controller;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.web.module.account.vo.Account;
import my.edu.umk.pams.account.web.module.identity.controller.IdentityTransformer;
import my.edu.umk.pams.account.workflow.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

/**
 * @author PAMS
 */
@Component("accountTransformer")
public class AccountTransformer {
    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private BillingService billingService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private IdentityTransformer identityTransformer;

    public Account toAccountVo(AcAccount e) {
        Account m = new Account();
        m.setCode(e.getCode());
        m.setActor(identityTransformer.toActorVo(e.getActor()));
        return m;
    }

    public List<Account> toAccountVos(List<AcAccount> accounts) {
        return accounts.stream()
                .map((task) -> toAccountVo(task))
                .collect(toCollection(() -> new ArrayList<Account>()));
    }
}
