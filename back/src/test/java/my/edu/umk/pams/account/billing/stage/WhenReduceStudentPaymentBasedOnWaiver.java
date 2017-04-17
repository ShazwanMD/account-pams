package my.edu.umk.pams.account.billing.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;

public class WhenReduceStudentPaymentBasedOnWaiver extends Stage<WhenReduceStudentPaymentBasedOnWaiver> {
	
    @As("Reduce student payment based on waiver")
	public WhenReduceStudentPaymentBasedOnWaiver Reduce_student_payment_based_on_waiver(){
		return self();
	}
}
