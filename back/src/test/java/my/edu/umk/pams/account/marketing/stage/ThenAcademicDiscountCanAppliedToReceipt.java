package my.edu.umk.pams.account.marketing.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.marketing.model.AcPromoCode;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeItem;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeType;
import my.edu.umk.pams.account.marketing.service.MarketingService;

@JGivenStage
public class ThenAcademicDiscountCanAppliedToReceipt extends Stage<ThenAcademicDiscountCanAppliedToReceipt> {
	private static final Logger LOG = LoggerFactory.getLogger(ThenAcademicDiscountCanAppliedToReceipt.class);

    @ExpectedScenarioState
    private AcStudent student;

    @Autowired
    private MarketingService marketingService;
    
    @Autowired
    private AccountService accountService;
    
    @ProvidedScenarioState
    private List<AcPromoCodeItem> promoCodeItem;
    
    @ProvidedScenarioState
    private AcPromoCodeType promoCodeType;
    
    @ProvidedScenarioState
    private List<AcPromoCode> promoCode;

	public ThenAcademicDiscountCanAppliedToReceipt discount_can_applied_to_receipt() {
		AcAccount account = accountService.findAccountByCode("A17P001");
    	Assert.notNull(account, "account cannot be null");
    	LOG.debug("Student:"+account.getActor());
    	
    
    	promoCodeItem = marketingService.findPromoCodeItems(account);
    	
    	for (AcPromoCodeItem promoCodeItems : promoCodeItem) {
          LOG.debug("SourceNo : "+promoCodeItems.getSourceNo());
         LOG.debug("Account : "+promoCodeItems.getAccount());
           LOG.debug("PromoCode : "+promoCodeItems.getPromoCode());
        
    	}
    	return self();
	}
}
