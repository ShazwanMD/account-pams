package my.edu.umk.pams.account.financialaid.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcStudentStatus;

@JGivenStage
public class WhenIWantToListStudentStatus extends Stage<WhenIWantToListStudentStatus>{

	private static final Logger LOG = LoggerFactory.getLogger(WhenIWantToListStudentStatus.class);
	
	@ExpectedScenarioState
	private AcAcademicSession academicSession;
	
	@ProvidedScenarioState
	private AcStudent student;
	
	@As("I want to list student status")
	public WhenIWantToListStudentStatus list_student_status() {

		LOG.debug("session " + academicSession.getId());
		
		Assert.notNull(student, "student was null");
		
		if(student.getStudentStatus()==AcStudentStatus.ACTIVE){
			
			LOG.debug("Name: " + student.getName());
			LOG.debug("Matric No: " + student.getMatricNo());
			LOG.debug("Identity No: " + student.getIdentityNo());

		}
		return self();
	}
}
