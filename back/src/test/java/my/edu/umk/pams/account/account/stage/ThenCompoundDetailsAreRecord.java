package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.security.service.AccessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

@JGivenStage
public class ThenCompoundDetailsAreRecord extends Stage<ThenCompoundDetailsAreRecord> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIRegisterStudentCompoundBill.class);

	@Autowired
	private IdentityService identityService;

	@Autowired
	private AccessService accessService;

	@ExpectedScenarioState
	private AcStudent student;

	public ThenCompoundDetailsAreRecord compound_details_are_record() {

		final String entityName = student.getClass().getSimpleName();
		Assert.notNull(student.getId(), "No " + entityName + " found with studentNo " + student.getMatricNo());
		Assert.notNull(student.getName(), "No " + entityName + " found with studentName " + student.getName());
		
		return self();
	}

}
