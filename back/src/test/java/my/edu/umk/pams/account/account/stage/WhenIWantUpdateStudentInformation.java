package my.edu.umk.pams.account.account.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcStudentImpl;
import my.edu.umk.pams.account.identity.service.IdentityService;


public class WhenIWantUpdateStudentInformation extends Stage <WhenIWantUpdateStudentInformation>{
	private static final Logger LOG = LoggerFactory.getLogger(WhenIWantUpdateStudentInformation.class);

	@ProvidedScenarioState
    private String studentNo = "19770816-07-5442";

    @ProvidedScenarioState
    private String studentName = "Siti Aisyah";

    @ProvidedScenarioState
    private AcStudent student;

    @ProvidedScenarioState
    private Long studentId;

    @Autowired
    @ExpectedScenarioState
    private IdentityService identityService;
    
	@Pending
	public WhenIWantUpdateStudentInformation I_want_update_student_information(){
		
		AcStudent student = new AcStudentImpl();
        student.setIdentityNo(studentNo);
        student.setName(studentName);
        student.setMobile("+60197888446");
        identityService.updateStudent(student);

        studentId = student.getId();
        final String entityName = student.getClass().getSimpleName();

        Assert.notNull(student.getId(), entityName + " must have Id");

        return self();
		
	}
}
