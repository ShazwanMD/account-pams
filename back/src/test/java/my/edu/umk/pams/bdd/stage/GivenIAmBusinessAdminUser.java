package my.edu.umk.pams.bdd.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.security.integration.AcUserDetails;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by PAMS on 3/8/2017.
 */
@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class GivenIAmBusinessAdminUser extends Stage<GivenIAmBusinessAdminUser> {

    private static final Logger LOG = LoggerFactory.getLogger(GivenIAmBusinessAdminUser.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @ProvidedScenarioState
    private AcUser businessAdminUser;

    public GivenIAmBusinessAdminUser I_am_a_business_admin_user() {
        loginAsBusinessAdmin();
        return self();
    }

    private void loginAsBusinessAdmin() {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("pps", "abc123");
        Authentication authed = authenticationManager.authenticate(token);
        businessAdminUser = ((AcUserDetails) authed.getPrincipal()).getUser();
        Assert.notNull(businessAdminUser, "We have no current user");
        SecurityContextHolder.getContext().setAuthentication(authed);
    }

}
