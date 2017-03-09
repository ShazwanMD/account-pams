package my.edu.umk.pams.bdd.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.identity.dao.AcStudentDao;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcStudentImpl;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.security.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by PAMS on 3/8/2017.
 */
@JGivenStage
public class WhenIAddAStudent extends Stage<WhenIAddAStudent> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenIAddAStudent.class);

    @ProvidedScenarioState
    private String studentNo = "19990201-07-1234";

    @ProvidedScenarioState
    private String studentName = "Elvis Presley";

    @ProvidedScenarioState
    private Long studentId;

    @ExpectedScenarioState
    private AcUser currentUser;

    @ExpectedScenarioState
    private AcStudentDao studentDao;

    @ExpectedScenarioState
    private SecurityService securityService;

    @As("I add student user Stuart")
    public WhenIAddAStudent I_add_a_student_user() {
        AcStudent student = new AcStudentImpl();
        student.setIdentityNo(studentNo);
        student.setName(studentName);

        studentDao.save(student, currentUser);
        securityService.getCurrentSession().flush();
        studentDao.refresh(student);

        studentId = student.getId();

        final String entityName = student.getClass().getSimpleName();

        Assert.notNull(student.getId(), entityName + " must have Id");

        return self();
    }

}
