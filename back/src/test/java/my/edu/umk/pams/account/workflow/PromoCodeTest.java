
package my.edu.umk.pams.account.workflow;

import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.marketing.model.*;
import my.edu.umk.pams.account.marketing.service.MarketingService;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author PAMS
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class PromoCodeTest {

    private static final Logger LOG = LoggerFactory.getLogger(PromoCodeTest.class);

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
    private MarketingService marketingService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private IdentityService identityService;

    @Before
    public void before() {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("bursary", "abc123");
        Authentication authed = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authed);
    }

    @Test
    @Rollback(false)
    public void testWorkflow() {
        AcPromoCode promoCode  = new AcPromoCodeImpl();
        promoCode.setDescription("Promotional code for processing fee intake 201720181");
        promoCode.setExpiryDate(new Date());
        promoCode.setPromoCodeType(AcPromoCodeType.DISCOUNT);
        promoCode.setQuantity(20);
        promoCode.setValue(BigDecimal.valueOf(120.00));
        String referenceNo = marketingService.initPromoCode(promoCode);

         promoCode = marketingService.findPromoCodeByReferenceNo(referenceNo);
        AcPromoCodeItem item1 = new AcPromoCodeItemImpl();
        item1.setCode("ABC123");
        item1.setApplied(false);
        marketingService.addPromoCodeItem(promoCode, item1);

        AcPromoCodeItem item2 = new AcPromoCodeItemImpl();
        item2.setCode("DEF123");
        item2.setApplied(false);
        marketingService.addPromoCodeItem(promoCode, item2);
    }
}
