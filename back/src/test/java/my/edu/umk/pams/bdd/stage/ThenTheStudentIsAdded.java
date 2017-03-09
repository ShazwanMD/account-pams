package my.edu.umk.pams.bdd.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.identity.dao.AcStudentDao;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.security.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * Created by PAMS on 3/8/2017.
 */
@JGivenStage
public class ThenTheStudentIsAdded extends Stage<ThenTheStudentIsAdded> {


    private static final Logger LOG = LoggerFactory.getLogger(WhenIAddAStudent.class);

    @ExpectedScenarioState
    private AcStudentDao studentDao;

    @ExpectedScenarioState
    private String studentNo;

    @ExpectedScenarioState
    private String studentName;

    @ExpectedScenarioState
    private Long studentId;

    @As("the student user Stuart is added")
    public ThenTheStudentIsAdded the_student_user_is_added(){
//        LOG.debug("A: " + studentNo);
//        LOG.debug("B: " + studentName);
//        LOG.debug("C: " + studentDao.getClass().getSimpleName());

        AcStudent student = studentDao.findById(studentId);

        final String entityName = student.getClass().getSimpleName();
        Assert.notNull(student.getId(), "No " + entityName + " found with studentNo " + studentNo);
        Assert.notNull(student.getName(), "No " + entityName + " found with studentName " + studentName);

        return self();
    }
}
