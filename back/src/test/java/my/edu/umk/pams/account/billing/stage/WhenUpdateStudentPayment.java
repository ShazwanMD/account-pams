package my.edu.umk.pams.account.billing.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;

public class WhenUpdateStudentPayment extends Stage<WhenUpdateStudentPayment> {

	@As("Update student payment")
	public WhenUpdateStudentPayment Update_student_payment() {
		return self();
	}
}
