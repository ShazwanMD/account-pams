package my.edu.umk.pams.bdd.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@JGivenStage
public class GivenIAmCPSAdministrator extends Stage<GivenIAmCPSAdministrator> {

    private static final Logger LOG = LoggerFactory.getLogger(GivenIAmCPSAdministrator.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private AccountService accountService;

    @ProvidedScenarioState
    private AcAcademicSession academicSession;

    @ProvidedScenarioState
    private AcStudent student;

    @ProvidedScenarioState
    private AcAccount account;

    public GivenIAmCPSAdministrator I_am_a_CPS_administrator_in_$_academic_session(String academicSessionCode){
        loginAsCPS();
        academicSession = accountService.findAcademicSessionByCode(academicSessionCode);
        return self();
    }

    public GivenIAmCPSAdministrator I_am_a_CPS_administrator_in_current_academic_session(){
        loginAsCPS();
        academicSession = accountService.findCurrentAcademicSession();
        return self();
    }

    public GivenIAmCPSAdministrator I_pick_a_student_$(String matricNo){
        student = identityService.findStudentByMatricNo(matricNo);
        account = accountService.findAccountByActor(student);
        return self();
    }

    private void loginAsCPS() {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("cps", "abc123");
        Authentication authed = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authed);
    }
}
