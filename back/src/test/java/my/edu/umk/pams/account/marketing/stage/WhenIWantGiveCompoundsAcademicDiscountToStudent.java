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
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.account.stage.WhenIWantGiveCompoundsDiscountToStudent;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.marketing.model.AcPromoCode;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeImpl;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeItem;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeItemImpl;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeType;
import my.edu.umk.pams.account.marketing.service.MarketingService;

@JGivenStage
public class WhenIWantGiveCompoundsAcademicDiscountToStudent
		extends Stage<WhenIWantGiveCompoundsAcademicDiscountToStudent> {
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
	public WhenIWantGiveCompoundsAcademicDiscountToStudent I_want_give_compounds_discount_to_student_$(
			String matricNo) {
		// 1. cari student untuk cari account
		student = identityService.findStudentByMatricNo(matricNo);
		Assert.notNull(student, "student cannot be null");

		// 2. cari akaun yg dpt dari student
		account = accountService.findAccountByActor(student);
		Assert.notNull(account, "account cannot be null");

		// 3. senarai acc charge dari akaun yg kita jumpa utk student ni
		accountCharges = accountService.findAccountCharges(account);

		for (AcAccountCharge accountCharges : accountCharges) {
			LOG.debug("Description " + accountCharges.getDescription());
			LOG.debug("Session " + accountCharges.getSession().getCode());
		}

		// 4. add details into promo code
		String PROMO_CODE = "MGSEB01";
		promoCode = new AcPromoCodeImpl();
		promoCode.setCode(PROMO_CODE);
		promoCode.setDescription("MGSEB give dicsount");
		promoCode.setPromoCodeType(AcPromoCodeType.DISCOUNT);
		promoCode.setQuantity(1);
		promoCode.setValue(BigDecimal.TEN);
		promoCode.setExpiryDate(new Date());
		marketingService.addPromoCode(promoCode);

		// 5. cari akaun pelajar
		AcAccount account = accountService.findAccountByCode("A17P001");

		// 6. add details into promo code item
		AcPromoCodeItem item = new AcPromoCodeItemImpl();
		// item.setAccount(account);
		item.setSourceNo("abc123");
		item.setApplied(false);
		item.setAccount(account);
		marketingService.addPromoCodeItem(promoCode, item);

		Assert.notNull(promoCode, "promoCode cannot be  null");
		Assert.notNull(item, "promoCodeItem cannot be  null");

		return self();
	}
}
