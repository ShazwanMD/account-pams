package my.edu.umk.pams.account.billing.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.integration.spring.JGivenStage;
@JGivenStage
public class WhenGenerateReportByFaculty extends Stage<WhenGenerateReportByFaculty>{

	public WhenGenerateReportByFaculty I_want_to_generate_report_by_faculty() {
		return self();
		
	}

}
