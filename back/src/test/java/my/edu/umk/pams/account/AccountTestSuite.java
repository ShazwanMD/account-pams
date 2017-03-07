package my.edu.umk.pams.account;

import my.edu.umk.pams.account.account.AccountModuleTestSuite;
import my.edu.umk.pams.account.billing.BillingModuleTestSuite;
import my.edu.umk.pams.account.identity.IdentityModuleTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BillingModuleTestSuite.class,
        AccountModuleTestSuite.class,
        IdentityModuleTestSuite.class,
})
public class AccountTestSuite {
}