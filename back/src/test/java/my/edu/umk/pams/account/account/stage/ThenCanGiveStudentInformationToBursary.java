package my.edu.umk.pams.account.account.stage;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcStudent;

@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class ThenCanGiveStudentInformationToBursary extends Stage<ThenCanGiveStudentInformationToBursary> {

	@ExpectedScenarioState
	private AcStudent student;

	@ProvidedScenarioState
	private AcAccount account;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ExpectedScenarioState
	private String matricNo;

	@As("I can give student information to bursary")
	public ThenCanGiveStudentInformationToBursary can_give_student_information_to_Bursary() {

		Assert.notNull(academicSession, "academic session is a prerequisite");
		return self();
	}

}
