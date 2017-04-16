package my.edu.umk.pams.account.marketing.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;

public class WhenIWantToValidateWaiverPromoCode extends Stage<WhenIWantToValidateWaiverPromoCode> {

	@As("I want to validate waiver promo code")
	public WhenIWantToValidateWaiverPromoCode I_want_to_validate_waiver_promo_code_$(String code) {
		return self();
	}

}
