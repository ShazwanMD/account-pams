package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcStudentImpl;
import my.edu.umk.pams.account.identity.service.IdentityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by PAMS on 3/8/2017.
 */
@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class WhenIAddAStudent extends Stage<WhenIAddAStudent> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenIAddAStudent.class);

    @ProvidedScenarioState
    private String studentNo = "19770816-07-1234";

    @ProvidedScenarioState
    private String studentName = "Elvis Presley";

    @ProvidedScenarioState
    private AcStudent student;

    @ProvidedScenarioState
    private Long studentId;

    @Autowired
    @ExpectedScenarioState
    private IdentityService identityService;

    public WhenIAddAStudent I_add_a_student_user() {
        student = new AcStudentImpl();
        student.setIdentityNo(studentNo);
        student.setName(studentName);
        student.setMobile("+60-0-555-1111");
        student.setMobile("+60-3-555-1212");
        student.setMobile("+60-3-555-1313");
        identityService.saveStudent(student);

        studentId = student.getId();
        final String entityName = student.getClass().getSimpleName();

        Assert.notNull(student.getId(), entityName + " must have Id");

        return self();
    }

}
