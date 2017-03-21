package my.edu.umk.pams.bdd.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcSponsor;
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
public class GivenIAmBursary extends Stage<GivenIAmBursary> {

    private static final Logger LOG = LoggerFactory.getLogger(GivenIAmBursary.class);

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
    private AcSponsor sponsor;

    public GivenIAmBursary I_am_a_bursary_in_$_academic_session(String academicSessionCode) {
        loginAsBursary();
        academicSession = accountService.findAcademicSessionByCode(academicSessionCode);
        return self();
    }

    public GivenIAmBursary I_am_a_bursary_in_current_academic_session() {
        loginAsBursary();
        academicSession = accountService.findCurrentAcademicSession();
        return self();
    }

    public GivenIAmBursary I_pick_a_student_with_matric_no_$(String matricNo){
        student = identityService.findStudentByMatricNo(matricNo);
        return self();
    }

    public GivenIAmBursary I_pick_a_sponsor_with_sponsor_no_$(String sponsorNo){
        sponsor = identityService.findSponsorBySponsorNo(sponsorNo);
        return self();
    }


    private void loginAsBursary() {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("bursary", "abc123");
        Authentication authed = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authed);
    }

}
