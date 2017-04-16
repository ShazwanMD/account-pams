package my.edu.umk.pams.account.billing.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.JGivenStage;

@JGivenStage
public class ThenICanPrintReportByFaculty extends Stage<ThenICanPrintReportByFaculty>{

	@As("I can print report")
	public ThenICanPrintReportByFaculty I_can_print_report() {
		return self();		
	}

}
