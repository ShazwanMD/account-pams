package my.edu.umk.pams.account.financialaid.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcStudentImpl;
import my.edu.umk.pams.account.identity.model.AcStudentStatus;
import my.edu.umk.pams.account.identity.service.IdentityService;


@JGivenStage
public class WhenIUpdateStudentStatus extends Stage<WhenIUpdateStudentStatus>{

	@Autowired
	private IdentityService identityService;
	
	@ProvidedScenarioState
	private AcStudent student;
	
	@ProvidedScenarioState
	private String code;
	
	@As("I want to update student status")
	public WhenIUpdateStudentStatus I_want_to_update_student_status_$(String code) {

		AcStudent student = identityService.findStudentByMatricNo(code);
		// = new AcStudentImpl();
		student.setStudentStatus(AcStudentStatus.ACTIVE);
		
		identityService.updateStudent(student);
		
		return self();
	}
}
