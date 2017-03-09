package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@JGivenStage
public class GivenIAmPPSAdministrator extends Stage<GivenIAmPPSAdministrator> {

    private static final Logger LOG = LoggerFactory.getLogger(GivenIAmPPSAdministrator.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    public void I_am_a_PPS_administrator(){
        LOG.debug("i'm logging in as pps");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("pps", "abc123");
        Authentication authed = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authed);
    }
}
