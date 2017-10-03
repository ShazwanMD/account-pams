
package my.edu.umk.pams.account.workflow;

import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcInvoiceItemImpl;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.model.AcTaxCode;
import my.edu.umk.pams.account.common.model.AcTaxCodeImpl;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.marketing.model.*;
import my.edu.umk.pams.account.marketing.service.MarketingService;
import my.edu.umk.pams.account.workflow.service.WorkflowService;
import net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Initial;

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

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * @author PAMS
 */
@JGivenStage
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class CalculateNetAmountTest {

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

//    @Before
//    public void before() {
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("bursary", "abc123");
//        Authentication authed = authenticationManager.authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(authed);
//    }

    @Test
    @Rollback(false)
    public void calculateNetAmount(){
    	
    	AcInvoice invoice = billingService.findInvoiceByReferenceNo("INVC001");
    	LOG.debug("Invoice:{}", invoice);

    	List<AcInvoiceItem> invoiceItems = billingService.findInvoiceItems(invoice, 0, 1);
    	LOG.debug("Invoice Items:{}", invoiceItems);
		
    	for (AcInvoiceItem invoiceItem : invoiceItems) {
    		
    		LOG.debug("Invoice ada x:{}", invoice );
		
		LOG.debug("tax Rate:{}", invoiceItem.getChargeCode().getTaxCode().getTaxRate());
		
		BigDecimal taxRate = invoiceItem.getChargeCode().getTaxCode().getTaxRate().setScale(2, RoundingMode.HALF_UP);
		LOG.debug("Tax Rate", taxRate);
		
		BigDecimal amount = invoiceItem.getAmount().setScale(2, RoundingMode.HALF_UP);
		LOG.debug("Amount", amount);
		
		BigDecimal taxAmount =  amount.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
		LOG.debug("Tax Amount: {}", taxAmount);
		
		BigDecimal netAmount = amount.add(taxAmount).setScale(2, RoundingMode.HALF_UP);
		LOG.debug("Net Amount: {}", netAmount);
		

		
		if(invoiceItem.getChargeCode().getInclusive()==false){
			LOG.debug("Exclusive: {}", invoiceItem.getChargeCode().getInclusive());
				invoiceItem.setNetAmount(netAmount);		
				invoiceItem.setTaxAmount(taxAmount);
				LOG.debug("Exclusive TaxAmount: {}",taxAmount);
			//	billingService.updateInvoiceItem(invoice, invoiceItem);
			}
			
			else if(invoiceItem.getChargeCode().getInclusive()==true){
				LOG.debug("Inclusive: {}", invoiceItem.getChargeCode().getInclusive());
				invoiceItem.setTaxAmount(taxAmount);
				LOG.debug(" TaxAmount: {}",taxAmount);
				LOG.debug(" TaxAmount invoice: {}",invoiceItem.getTaxAmount());
				LOG.debug("TaxAmount invoice item: {}",invoice.getReferenceNo());
			//	billingService.deleteInvoiceItem(invoice, invoiceItem);
			}
    }}

    
    
}