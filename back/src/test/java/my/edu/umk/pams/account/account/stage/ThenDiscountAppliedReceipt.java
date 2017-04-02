package my.edu.umk.pams.account.account.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.integration.spring.JGivenStage;

@JGivenStage
public class ThenDiscountAppliedReceipt extends Stage <ThenDiscountAppliedReceipt> {
	
	private static final Logger LOG = LoggerFactory.getLogger(WhenIWantGiveCompoundsDiscountStudent.class);

	@Pending
	@As("discount_can_applied_to_receipt")
	public ThenDiscountAppliedReceipt discount_can_applied_to_receipt(){
		
		return self();
	}

}
