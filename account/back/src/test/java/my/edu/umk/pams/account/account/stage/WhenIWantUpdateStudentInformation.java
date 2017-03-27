package my.edu.umk.pams.account.account.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcChargeCodeType;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcActorImpl;
import my.edu.umk.pams.account.identity.model.AcActorType;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcStudentImpl;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class WhenIWantUpdateStudentInformation extends Stage<WhenIWantUpdateStudentInformation> {
	private static final Logger LOG = LoggerFactory.getLogger(WhenIWantUpdateStudentInformation.class);

	@ExpectedScenarioState
	private AcStudent student;

	@ProvidedScenarioState
	private AcAccount account;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@Autowired
	private IdentityService identityService;

	public WhenIWantUpdateStudentInformation I_want_update_student_information() {

		AcStudent student = new AcStudentImpl();

		// find student by id
		student = identityService.findStudentByMatricNo("A17P001");
		LOG.debug("test", student);

		// update student
		student.setMobile("012-87965454"); 
		student.setActorType(AcActorType.STUDENT);

		// use account service to update charge
		identityService.updateStudent(student);

		return self();

	}
}
