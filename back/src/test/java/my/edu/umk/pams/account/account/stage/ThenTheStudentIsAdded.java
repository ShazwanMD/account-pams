package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;
import org.springframework.util.Assert;

/**
 * Created by PAMS on 3/8/2017.
 */
@JGivenStage
public class ThenTheStudentIsAdded extends Stage<ThenTheStudentIsAdded> {

    @ExpectedScenarioState
    private IdentityService identityService;

    @ExpectedScenarioState
    private String matricNo;

    @As("the student user is added")
    public ThenTheStudentIsAdded the_student_user_is_added(){
        AcStudent student = identityService.findStudentByMatricNo(matricNo);
        Assert.notNull(student, "Student is found");
        return self();
    }
}
