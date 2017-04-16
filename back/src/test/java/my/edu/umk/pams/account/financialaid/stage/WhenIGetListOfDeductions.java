package my.edu.umk.pams.account.financialaid.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;

public class WhenIGetListOfDeductions extends Stage<WhenIGetListOfDeductions> {

	@As("I want to get list of deduction")
	public WhenIGetListOfDeductions I_get_list_of_deductions_$(String sponsorNo) {
		return self();
	}

}
