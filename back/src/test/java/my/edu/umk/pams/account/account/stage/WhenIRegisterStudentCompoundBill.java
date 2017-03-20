package my.edu.umk.pams.account.account.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.account.model.AcAcademicCharge;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcStudentImpl;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class WhenIRegisterStudentCompoundBill extends Stage<WhenIRegisterStudentCompoundBill> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIRegisterStudentCompoundBill.class);
	
	@ProvidedScenarioState
    private String studentNo = "19770816-07-4444";

    @ProvidedScenarioState
    private String studentName = "Siti";

    @ProvidedScenarioState
    private AcStudent student;

    @ProvidedScenarioState
    private Long studentId;

    @Autowired
    @ExpectedScenarioState
    private IdentityService identityService;
    
	public WhenIRegisterStudentCompoundBill register_student_compound_bill() {
		
		AcStudent student = new AcStudentImpl();
        student.setIdentityNo(studentNo);
        student.setName(studentName);
        student.setMobile("+60197888446");
        identityService.saveStudent(student);

        studentId = student.getId();
        final String entityName = student.getClass().getSimpleName();

        Assert.notNull(student.getId(), entityName + " must have Id");

        return self();
	}
}
