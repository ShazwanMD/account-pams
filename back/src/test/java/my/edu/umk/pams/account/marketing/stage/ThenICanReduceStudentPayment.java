package my.edu.umk.pams.account.marketing.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;

public class ThenICanReduceStudentPayment extends Stage<ThenICanReduceStudentPayment> {

	@As("I can reduce payment for student")
	public ThenICanReduceStudentPayment I_can_reduce_payment_for_student_$(String matricNo) {
		return self();
	}

}
