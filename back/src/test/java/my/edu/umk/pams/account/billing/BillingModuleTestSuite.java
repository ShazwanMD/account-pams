package my.edu.umk.pams.account.billing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import my.edu.umk.pams.account.marketing.US_AC_ACT_7002;

/**
 * @author PAMS
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
		US_AC_BLG_2001.class, //faizal
		US_AC_BLG_2002.class, //faizal
		US_AC_BLG_2003.class,
        US_AC_BLG_2007.class, //hajar
        US_AC_BLG_2008.class, //sahir
        US_AC_BLG_2009.class,
        US_AC_ACT_7002.class, //sahir
        US_AC_BLG_2015.class, //sahir
        US_AC_BLG_2016.class, //sahir
        US_AC_BLG_2017.class,
        US_AC_BLG_2025.class, //hajar
        US_AC_BLG_2027.class, //aida
        US_AC_BLG_2031.class,
})
public class BillingModuleTestSuite {
}
