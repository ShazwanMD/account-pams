package my.edu.umk.pams.account;

import my.edu.umk.pams.account.identity.IdentityTestSuite;
import my.edu.umk.pams.account.billing.BillingTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BillingTestSuite.class,
        AccountTestSuite.class,
        IdentityTestSuite.class,
})
public class AccountTestSuite {
}