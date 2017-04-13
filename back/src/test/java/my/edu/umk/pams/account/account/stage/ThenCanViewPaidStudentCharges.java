package my.edu.umk.pams.account.account.stage;

import java.util.List;
import org.springframework.util.Assert;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of http://119.110.101.9/pams/account.git
import my.edu.umk.pams.account.account.model.AcAccountCharge;

public class ThenCanViewPaidStudentCharges extends Stage<ThenCanViewPaidStudentCharges> {

	@ExpectedScenarioState
	private List<AcAccountCharge> accountCharges;

	public ThenCanViewPaidStudentCharges I_can_view_paid_student_charges() {

		Assert.notEmpty(accountCharges, "This is list of paid charges");

		return self();
	}

}
