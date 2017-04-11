package my.edu.umk.pams.account.billing.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.common.model.AcProgramCode;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class WhenIGenerateReport extends Stage<WhenIGenerateReport> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIGenerateReport.class);

	@Autowired
	private CommonService commonService;

	@Autowired
	private IdentityService identityService;
	
	@ExpectedScenarioState
	private List<AcSponsorship> sponsorship;

	@ExpectedScenarioState
	private AcProgramCode programCode;

	@As("I generate report by program")
	public WhenIGenerateReport I_generate_report_by_program$(String code) {

		programCode = commonService.findProgramCodeByCode(code);
		LOG.debug("Program Code " + programCode.getDescription());

		sponsorship = identityService.findSponsorships(programCode);

		for (AcSponsorship sponsorship : sponsorship) {

			LOG.debug("Name: " + sponsorship.getStudent().getName());
			LOG.debug("Matric No: " + sponsorship.getStudent().getMatricNo());
			LOG.debug("Status: " + sponsorship.getStudent().getStudentStatus());
			LOG.debug("Sponsorship " + sponsorship.getSponsor().getName());
			LOG.debug("IC No: " + sponsorship.getStudent().getIdentityNo());

			
		}
		return self();
	}
}
