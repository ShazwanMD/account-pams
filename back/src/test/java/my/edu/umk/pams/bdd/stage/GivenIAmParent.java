package my.edu.umk.pams.bdd.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.security.integration.AcUserDetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@JGivenStage
public class GivenIAmParent extends Stage<GivenIAmParent> {

	private static final Logger LOG = LoggerFactory.getLogger(GivenIAmParent.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AccountService accountService;

	@ProvidedScenarioState
	private AcAcademicSession academicSession;

	@ProvidedScenarioState
	private AcStudent student;

	@ProvidedScenarioState
	private AcAccount account;

	public void I_am_a_parent_for_a_student_in_$_academic_session(String academicSessionCode) {
		loginAsParent();
		academicSession = accountService.findAcademicSessionByCode(academicSessionCode);
	}

	public void I_am_a_parent_for_a_student_in_current_academic_session() {
		loginAsParent();
		academicSession = accountService.findCurrentAcademicSession();
	}

	private void loginAsParent() {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("student1", "abc123");
		Authentication authed = authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authed);

		// retrieve student from user
		AcUser user = ((AcUserDetails) authed.getPrincipal()).getUser();
		student = (AcStudent) user.getActor();
		account = accountService.findAccountByActor(student);
	}
}
