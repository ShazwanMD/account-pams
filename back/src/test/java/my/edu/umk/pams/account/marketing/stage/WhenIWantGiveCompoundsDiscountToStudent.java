package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.marketing.model.*;
import my.edu.umk.pams.account.marketing.service.MarketingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@JGivenStage
public class WhenIWantGiveCompoundsDiscountToStudent extends Stage<WhenIWantGiveCompoundsDiscountToStudent> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenIWantGiveCompoundsDiscountToStudent.class);

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

    @As("I_want_give_compounds_discount_to_student")
    public WhenIWantGiveCompoundsDiscountToStudent I_want_give_compounds_discount_to_student_$(String matricNo) {

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

        String PROMO_CODE = "aa01";
        promoCode = new AcPromoCodeImpl();
        promoCode.setCode(PROMO_CODE);
        promoCode.setDescription("A rare discount");
        promoCode.setPromoCodeType(AcPromoCodeType.DISCOUNT);
        promoCode.setQuantity(1);
        promoCode.setValue(BigDecimal.TEN);
        promoCode.setExpiryDate(new Date());
        marketingService.addPromoCode(promoCode);
        
        AcAccount account = accountService.findAccountByCode("A17P002");
        

        AcPromoCodeItem item = new AcPromoCodeItemImpl();
//		item.setAccount(account);
        item.setSourceNo("abc123");
        item.setApplied(false);
        item.setAccount(account);
        marketingService.addPromoCodeItem(promoCode, item);

        Assert.notNull(promoCode, "promoCode cannot be  null");
        Assert.notNull(item, "promoCodeItem cannot be  null");

        return self();

    }
}