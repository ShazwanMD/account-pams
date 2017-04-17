package my.edu.umk.pams.account.billing.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.JGivenStage;

@JGivenStage
public class WhenCreateAdvancedPayment extends Stage <WhenCreateAdvancedPayment> {
	private static final Logger LOG = LoggerFactory.getLogger(WhenCreateAdvancedPayment.class);

	@As("create advance payment for student")
	public WhenCreateAdvancedPayment Create_advanced_payment_for_student_$(String matricNo) {
		// TODO Auto-generated method stub
		return self();
	}
}
