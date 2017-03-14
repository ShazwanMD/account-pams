package my.edu.umk.pams.account.account;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.bdd.stage.GivenIAmStudent;
import my.edu.umk.pams.bdd.stage.ThenUsedAsProofOfPayment;
import my.edu.umk.pams.bdd.stage.WhenIPrintCompoundReceipt;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.account.account.stage.ThenAccountIsCharged;
import my.edu.umk.pams.account.account.stage.WhenIAddAccountCharge;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * As a Student, I want to list student charges by account,  so that I can view student's charges
 *
 * @author PAMS
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
@Issue(value = { "PAMSU-4" })
public class US_AC_ACT_0012 extends SpringScenarioTest<GivenIAmStudent, WhenIPrintCompoundReceipt, ThenUsedAsProofOfPayment> {

    private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_0012.class);

    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    private static final String STUDENT_NO = "ABC001";
    private static final String SESSION_CODE = "201720181";
    private static final double CHARGE_AMOUNT = 200.00;

    /**
     * TODO: As a Student in current academic session
     * I want to list student charges by account,
     * so that I can view student's charges
     */
    @Test
    public void testScenario0() {
        given().I_am_a_student_in_current_academic_session();
        when().I_print_compound_receipt_for_studentNo_$(STUDENT_NO);
        then().Used_as_proof_of_payment();
    }

    
}

