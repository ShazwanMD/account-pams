package my.edu.umk.pams.account.account.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.marketing.model.AcPromoCode;

@JGivenStage
public class ThenAcademicDiscountCanAppliedToReceipt extends Stage<ThenAcademicDiscountCanAppliedToReceipt> {
	private static final Logger LOG = LoggerFactory.getLogger(ThenAcademicDiscountCanAppliedToReceipt.class);

	@ExpectedScenarioState
	private AcPromoCode promoCode;

	public ThenAcademicDiscountCanAppliedToReceipt discount_can_applied_to_receipt() {
		Assert.notNull(promoCode, "promoCode cannot be null");
		return self();
	}
}
