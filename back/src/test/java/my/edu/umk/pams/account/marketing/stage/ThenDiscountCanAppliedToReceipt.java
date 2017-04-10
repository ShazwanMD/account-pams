package my.edu.umk.pams.account.marketing.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.marketing.model.AcPromoCode;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeItem;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeType;
import my.edu.umk.pams.account.marketing.service.MarketingService;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

@JGivenStage
public class ThenDiscountCanAppliedToReceipt extends Stage<ThenDiscountCanAppliedToReceipt> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenIWantGiveCompoundsDiscountToStudent.class);

   // @ExpectedScenarioState
   // private AcPromoCode promoCode;
    
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
    
    public ThenDiscountCanAppliedToReceipt discount_can_applied_to_receipt() {
    	
    	AcAccount account = accountService.findAccountByCode("A17P002");
    	Assert.notNull(account, "account cannot be null");
    	LOG.debug("Student:"+account.getActor());
    	
    
    	promoCodeItem = marketingService.findPromoCodeItems(account);
    	
    	for (AcPromoCodeItem promoCodeItems : promoCodeItem) {
          LOG.debug("SourceNo : "+promoCodeItems.getSourceNo());
         LOG.debug("Account : "+promoCodeItems.getAccount());
           LOG.debug("PromoCode : "+promoCodeItems.getPromoCode());
        
    	}
    	//Assert.notNull(promoCode, "promoCode cannot be null");
        
        return self();
    }

}
