package my.edu.umk.pams.account.account.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class ThenCompoundDetailsAreRecord extends Stage<ThenCompoundDetailsAreRecord> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIRegisterStudentCompoundBill.class);

	@ExpectedScenarioState
	private IdentityService identityService;

	@ExpectedScenarioState
	private String studentNo;

	@ExpectedScenarioState
	private String studentName;

	@ExpectedScenarioState
	private Long studentId;

	public ThenCompoundDetailsAreRecord compound_details_are_record() {

		AcStudent student = identityService.findStudentById(studentId);

		final String entityName = student.getClass().getSimpleName();
		Assert.notNull(student.getId(), "No " + entityName + " found with studentNo " + studentNo);
		Assert.notNull(student.getName(), "No " + entityName + " found with studentName " + studentName);
		
		return self();
	}

}
