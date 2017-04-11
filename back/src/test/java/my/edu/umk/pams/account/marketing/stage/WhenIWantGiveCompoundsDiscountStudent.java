package my.edu.umk.pams.account.marketing.stage;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.marketing.model.AcPromoCode;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeImpl;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeItem;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeItemImpl;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeType;
import my.edu.umk.pams.account.marketing.service.MarketingService;

@JGivenStage
public class WhenIWantGiveCompoundsDiscountStudent extends Stage <WhenIWantGiveCompoundsDiscountStudent> {
	
	private static final Logger LOG = LoggerFactory.getLogger(WhenIWantGiveCompoundsDiscountStudent.class);

	@ExpectedScenarioState
    private AcStudent student;

    @ProvidedScenarioState
    private AcAccount account;

    @ProvidedScenarioState
    private AcPromoCode promoCode;

    @ExpectedScenarioState
    private AcAcademicSession academicSession;

    @Autowired
    private AccountService accountService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private MarketingService marketingService;

    @ProvidedScenarioState
    private List<AcAccountCharge> accountCharges;
	
	
	@As("I want to give compounds discount to student")
	public WhenIWantGiveCompoundsDiscountStudent I_want_to_give_compounds_discount_to_student_$(String matricNo){
		 
		 //cari student untuk cari account
        student = identityService.findStudentByMatricNo(matricNo);
        Assert.notNull(student, "student cannot be null");

        //cari akaun yg dpt dr student
        account = accountService.findAccountByActor(student);
        Assert.notNull(account, "account cannot be null");

        //senarai acc charge dari akaun yg kita jmp utk student ni
        accountCharges = accountService.findAccountCharges(account);

        for (AcAccountCharge accountCharges : accountCharges) {
            LOG.debug("Description " + accountCharges.getDescription());
            LOG.debug("Session " + accountCharges.getSession().getCode());
        }

        String PROMO_CODE = "PROMO50%";
        promoCode = new AcPromoCodeImpl();
        promoCode.setCode(PROMO_CODE);
        promoCode.setDescription("A rare discount");
        promoCode.setPromoCodeType(AcPromoCodeType.DISCOUNT);
        promoCode.setQuantity(1);
        promoCode.setValue(BigDecimal.TEN);
        promoCode.setExpiryDate(new Date());
        marketingService.addPromoCode(promoCode);
        
        AcAccount account = accountService.findAccountByCode("A17P001");
        
        AcPromoCodeItem item = new AcPromoCodeItemImpl();
        item.setSourceNo("abc123");
        item.setApplied(false);
        item.setAccount(account);
        marketingService.addPromoCodeItem(promoCode, item);

        Assert.notNull(promoCode, "promoCode cannot be  null");
        Assert.notNull(item, "promoCodeItem cannot be  null");

		return self();
	}
}
