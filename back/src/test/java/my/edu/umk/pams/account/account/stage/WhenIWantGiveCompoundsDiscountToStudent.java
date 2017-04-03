package my.edu.umk.pams.account.account.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import my.edu.umk.pams.account.identity.model.AcStaff;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.marketing.model.AcPromoCode;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeImpl;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeItemImpl;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeType;
import my.edu.umk.pams.account.marketing.service.MarketingService;

import my.edu.umk.pams.account.security.integration.AcUserDetails;

import org.springframework.util.Assert;

@JGivenStage
public class WhenIWantGiveCompoundsDiscountToStudent extends Stage<WhenIWantGiveCompoundsDiscountToStudent> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIWantGiveCompoundsDiscountToStudent.class);

	@ExpectedScenarioState
	private AcStudent student;

	@ProvidedScenarioState
	private AcAccount account;
	
	@ProvidedScenarioState
	private AcPromoCode promocode;
	
	@ProvidedScenarioState
	private AcPromoCodeItemImpl detail;
	
	@ProvidedScenarioState
	private AcPromoCodeType promoCodeType;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@Autowired
	private AccountService accountService;

	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private MarketingService marketingService;
	
    @ExpectedScenarioState
    private AcStaff staff;
	
	@ProvidedScenarioState
    private List<AcAccountCharge> accountCharges;

	@Pending
	@As("I_want_give_compounds_discount_to_student")
	public WhenIWantGiveCompoundsDiscountToStudent I_want_give_compounds_discount_to_student_$(String matricNo) {
		
		//cari student untuk cari account 
		student = identityService.findStudentByMatricNo(matricNo);
	
        AcUser user = identityService.findUserByActor(student);

		//cari akaun yg dpt dr student
		account = accountService.findAccountByActor(student);
		
		//senarai acc charge dari akaun yg kita jmp utk student ni
		accountCharges= accountService.findAccountCharges(account);
		for(AcAccountCharge accountCharges : accountCharges){
			LOG.debug("Description "+ accountCharges.getDescription());
			LOG.debug("Session "+ accountCharges.getSession().getCode());
		}

		
		promocode = new AcPromoCodeImpl();
		promocode.setCode("aa01");
		promocode.setDescription("discount");
		promocode.setPromoCodeType(promoCodeType.DISCOUNT);
		promocode.setQuantity(1);
	//	promocode.setExpiryDate(expiryDate);
		LOG.debug("Student: {}", promocode);
		

		detail = new AcPromoCodeItemImpl();
	//	detail.setAccount(account);
		detail.setApplied(false);
		detail.setPromoCode(promocode);
		detail.setSourceNo("abc123");
		LOG.debug("Student: {}", detail);

		marketingService.addPromoCodeItem(promocode, detail, user );
	
		return self();
	
}
}