package my.edu.umk.pams.bdd.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStaff;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.security.integration.AcUserDetails;

@JGivenStage
public class GivenIAmSecurity extends Stage<GivenIAmSecurity> {
	private static final Logger LOG = LoggerFactory.getLogger(GivenIAmSecurity.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AccountService accountService;

	@Autowired
	private IdentityService identityService;

	@ProvidedScenarioState
	private AcAcademicSession academicSession;

	@ProvidedScenarioState
	private AcStudent student;
	
	@ProvidedScenarioState
	private AcStaff staff;

	public GivenIAmSecurity I_am_security_$(String matricNo) {
		loginAsSecurity();
		academicSession = accountService.findCurrentAcademicSession();
		student = identityService.findStudentByMatricNo(matricNo);
		return self();
	}
	
	public GivenIAmSecurity I_am_security() {
		loginAsSecurity();
		academicSession = accountService.findCurrentAcademicSession();
		return self();
	}
	
	private void loginAsSecurity() {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("security", "abc123");
		Authentication authed = authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authed);

	}
}
