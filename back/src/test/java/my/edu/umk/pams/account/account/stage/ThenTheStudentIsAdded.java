package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

/**
 * Created by PAMS on 3/8/2017.
 */
@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class ThenTheStudentIsAdded extends Stage<ThenTheStudentIsAdded> {


    private static final Logger LOG = LoggerFactory.getLogger(WhenIAddAStudent.class);

    @ExpectedScenarioState
    private IdentityService identityService;

    @ExpectedScenarioState
    private String studentNo;

    @ExpectedScenarioState
    private String studentName;

    @ExpectedScenarioState
    private Long studentId;

    public ThenTheStudentIsAdded the_student_user_is_added(){

        AcStudent student = identityService.findStudentById(studentId);

        final String entityName = student.getClass().getSimpleName();
        Assert.notNull(student.getId(), "No " + entityName + " found with studentNo " + studentNo);
        Assert.notNull(student.getName(), "No " + entityName + " found with studentName " + studentName);

        return self();
    }
}
