package my.edu.umk.pams.account.account.stage;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.financialaid.model.AcSettlementImpl;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.marketing.model.AcPromoCode;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeImpl;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeItem;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeItemImpl;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeType;
import my.edu.umk.pams.account.marketing.service.MarketingService;


@JGivenStage
public class WhenIWantGiveCompoundsDiscountToStudent extends Stage<WhenIWantGiveCompoundsDiscountToStudent> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIWantGiveCompoundsDiscountToStudent.class);

	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private MarketingService marketingService;
	
	@ProvidedScenarioState
	private AcStudent student;
	
	@ProvidedScenarioState
	private AcPromoCode promocode;
	
	@Autowired
	private AcPromoCodeType promoCodeType;
	
	@As("I_want_give_compounds_discount_to_student")
	public WhenIWantGiveCompoundsDiscountToStudent I_want_give_compounds_discount_to_student$(String code){
		
		AcUser user = identityService.findUserByActor(student);
		LOG.debug("Student No :" + student.getMatricNo());

		LOG.debug("DISCOUNT :" + promocode.getCode());

		promocode = new AcPromoCodeImpl();
		promocode.setCode("aa01");
		promocode.setDescription("discount");
		promocode.setPromoCodeType(promoCodeType.DISCOUNT);
		promocode.setQuantity(1);
		promocode.setExpiryDate(new Date());

		AcPromoCodeItem detail = new AcPromoCodeItemImpl();
		// detail.setAccount(account);
		detail.setApplied(false);
		detail.setPromoCode(promocode);
		detail.setSourceNo("abc123");

		marketingService.addPromoCodeItem(promocode, detail, user );

		return self();
	}
}
