package my.edu.umk.pams.bdd.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStaff;
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
public class GivenIAmCPSPegawaiAdministrator extends Stage<GivenIAmCPSPegawaiAdministrator> {

    private static final Logger LOG = LoggerFactory.getLogger(GivenIAmCPSPegawaiAdministrator.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountService accountService;

    @ProvidedScenarioState
    private AcAcademicSession academicSession;

    @ProvidedScenarioState
    private AcStaff staff;

    public void I_am_a_CPS_administrator_in_$_academic_session(String academicSessionCode){
        loginAsCPS();
        academicSession = accountService.findAcademicSessionByCode(academicSessionCode);
    }

    public void I_am_a_CPS_administrator_in_current_academic_session(){
        loginAsCPS();
        academicSession = accountService.findCurrentAcademicSession();
    }

    private void loginAsCPS() {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("cps-pegawai", "abc123");
        Authentication authed = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authed);

        // retrieve staff from user
        AcUser user = ((AcUserDetails) authed.getPrincipal()).getUser();
        staff = (AcStaff) user.getActor();

    }
}
