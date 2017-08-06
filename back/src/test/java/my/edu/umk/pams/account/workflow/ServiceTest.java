
package my.edu.umk.pams.account.workflow;

import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcReceipt;
import my.edu.umk.pams.account.billing.model.AcReceiptInvoice;
import my.edu.umk.pams.account.billing.model.AcReceiptInvoiceImpl;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author PAMS
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class ServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceTest.class);

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
    public void testService() {
    	   	
    	AcReceipt receipt = billingService.findReceiptByReferenceNo("RCPT002");
    	
    	AcInvoice invoice = billingService.findInvoiceByReferenceNo("INVC003");
    	
        AcReceiptInvoice receiptInvoice = new AcReceiptInvoiceImpl();
        receiptInvoice.setReceipt(receipt);
        receiptInvoice.setInvoice(invoice);
        billingService.addReceiptInvoice(receipt, invoice);

}
}
