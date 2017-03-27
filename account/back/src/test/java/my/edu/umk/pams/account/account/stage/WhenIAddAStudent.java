package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;
import org.hibernate.SessionFactory;
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

    @Autowired
    private IdentityService identityService;

    @Autowired
    private SessionFactory sessionFactory;

    @ProvidedScenarioState
    private String matricNo = "19770816-07-1234";

    @ProvidedScenarioState
    private String studentName = "Elvis Presley";

    @ProvidedScenarioState
    private AcStudent student;

    public WhenIAddAStudent I_pick_a_student_$(String matricNo) {
        student = identityService.findStudentByMatricNo(matricNo);
        return self();
    }
}
