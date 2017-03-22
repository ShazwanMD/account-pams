package my.edu.umk.pams.bdd.stage;

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
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.security.integration.AcUserDetails;

@JGivenStage
public class GivenIAmSecurity extends Stage<GivenIAmSecurity>{
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountService accountService;

    @ProvidedScenarioState
    private AcAcademicSession academicSession;

    @ProvidedScenarioState
    private AcStaff staff;

    public void I_am_security(){
        loginAsSecurity();
        academicSession = accountService.findCurrentAcademicSession();
    }

    private void loginAsSecurity() {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("security", "abc123");
        Authentication authed = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authed);

        // retrieve staff from user
        AcUser user = ((AcUserDetails) authed.getPrincipal()).getUser();
        staff = (AcStaff) user.getActor();

    }
}
