package my.edu.umk.pams.account.account.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.integration.spring.JGivenStage;

@JGivenStage
public class ThenDiscountCanAppliedToReceipt extends Stage<ThenDiscountCanAppliedToReceipt> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIWantGiveCompoundsDiscountToStudent.class);

	public ThenDiscountCanAppliedToReceipt discount_can_applied_to_receipt() {
		return self();
	}

}
