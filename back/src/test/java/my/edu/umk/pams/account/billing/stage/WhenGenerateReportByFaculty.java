package my.edu.umk.pams.account.billing.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.account.stage.WhenIListAllCompoundStudent;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class WhenGenerateReportByFaculty extends Stage<WhenGenerateReportByFaculty> {
	private static final Logger LOG = LoggerFactory.getLogger(WhenIListAllCompoundStudent.class);

	@ExpectedScenarioState
	private AcStudent student;

	@ProvidedScenarioState
	private AcAccount accounts;
	//private List<AcAccount> accounts;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@Autowired
	private AccountService accountService;

	@Autowired
	private IdentityService identityService;

	@ProvidedScenarioState
	private List<AcAccountCharge> accountCharges;

	@As("I want to generate report by faculty")
	public WhenGenerateReportByFaculty I_want_to_generate_report_by_faculty_$(String matricNo) {
		student = identityService.findStudentByMatricNo(matricNo);

		accounts = accountService.findAccountByActor(student);
		accountCharges = accountService.findAccountCharges(accounts);
		for (AcAccountCharge accountCharges : accountCharges) {
			LOG.debug("Description " + accountCharges.getDescription());
			LOG.debug("Session " + accountCharges.getSession().getCode());
		}

		return self();

	}

}
