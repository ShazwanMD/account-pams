package my.edu.umk.pams.bdd.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.security.integration.AcUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@JGivenStage
public class GivenIAmBursary extends Stage<GivenIAmBursary> {

    private static final Logger LOG = LoggerFactory.getLogger(GivenIAmBursary.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountService accountService;

    @ProvidedScenarioState
    AcAcademicSession academicSession;

    public void I_am_a_bursary_in_$_academic_session(String academicSessionCode) {
        loginAsBursary();
        academicSession = accountService.findAcademicSessionByCode(academicSessionCode);
    }

    public void I_am_a_bursary_in_current_academic_session() {
        loginAsBursary();
        academicSession = accountService.findCurrentAcademicSession();
    }

    private void loginAsBursary() {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("bursary", "abc123");
        Authentication authed = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authed);
    }

    private void loginAsBusinessAdmin() {
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("pps", "abc123");
//        Authentication authed = authenticationManager.authenticate(token);
//        businessAdminUser = ((AcUserDetails) authed.getPrincipal()).getUser();
//        Assert.notNull(businessAdminUser, "We have no current user");
//        SecurityContextHolder.getContext().setAuthentication(authed);
    }
}
