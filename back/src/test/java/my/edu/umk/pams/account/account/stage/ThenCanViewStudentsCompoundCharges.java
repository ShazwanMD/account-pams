package my.edu.umk.pams.account.account.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcCoverage;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class ThenCanViewStudentsCompoundCharges extends Stage<ThenCanViewStudentsCompoundCharges> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIListAllCompoundStudent.class);

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ProvidedScenarioState
	private AcAccount account;

	@ExpectedScenarioState
	private AcAccountCharge accountCharge;

	@ProvidedScenarioState
	private AcStudent student;

	@Autowired
	private AccountService accountService;

	@Autowired
	private IdentityService identityService;

	@As("can view students compound charges")
	public ThenCanViewStudentsCompoundCharges can_view_students_compound_charges_$(String matricNo) {

		student = identityService.findStudentByMatricNo(matricNo);
		LOG.debug("Student ID :" + student.getIdentityNo());

		// sponsorship = identityService.findSponsorships(student);
		accountCharge = accountService.findAccountChargeByReferenceNo("REFNO/1490156078146");

		List<AcAccountCharge> accountCharge = accountService.findAccountCharges(account);

		for (AcAccountCharge charge : accountCharge) {

			LOG.debug("Charge Type :" + charge.getChargeType());
		}

		return self();
	}
}
