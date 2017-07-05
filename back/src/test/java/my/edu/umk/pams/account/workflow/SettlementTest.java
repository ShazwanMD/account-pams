package my.edu.umk.pams.account.workflow;

import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.financialaid.model.AcSettlementImpl;
import my.edu.umk.pams.account.financialaid.model.AcSettlementItem;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.workflow.service.WorkflowService;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author PAMS
 */
@Transactional
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class SettlementTest {

    private static final Logger LOG = LoggerFactory.getLogger(SettlementTest.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BillingService billingService;

    @Autowired
    private FinancialAidService financialAidService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private IdentityService identityService;
    public static final String COHORT_CODE = "FIAT-PHD-0001-CHRT-201720181";

    @Before
    public void before() {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("bursary", "abc123");
        Authentication authed = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authed);
    }

    @Test
    @Rollback(false)
    public void testWorkflow() {
        AcCohortCode cohortCode = commonService.findCohortCodeByCode(COHORT_CODE);
        AcSettlement settlement = new AcSettlementImpl();
        settlement.setDescription("Settlement for cohort " + COHORT_CODE);
        settlement.setSession(accountService.findCurrentAcademicSession());
        String referenceNo = financialAidService.initSettlementByCohortCode(settlement, cohortCode);

        // find generated settlement
        settlement = financialAidService.findSettlementByReferenceNo(referenceNo);

        // check if we have settlement item
        List<AcSettlementItem> settlementItems = financialAidService.findSettlementItems(settlement);
        LOG.debug("items size: {}", settlementItems.size());

        // execute settlement
        financialAidService.executeSettlement(settlement);

        // check if we generated invoice for each item
         settlementItems = financialAidService.findSettlementItems(settlement);
        for (AcSettlementItem settlementItem : settlementItems) {
            LOG.debug("invoice?: " + settlementItem.getInvoice());
        }


    }
}
