package my.edu.umk.pams.account.security.integration.jwt.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.account.security.integration.jwt.JwtAuthenticationToken;
import my.edu.umk.pams.account.security.integration.jwt.JwtUserDetailService;

/**
 * Created by shazin on 12/9/16.
 */
@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private static final Logger LOG = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

    @Autowired
    @Qualifier("jwtUserDetailService")
    private JwtUserDetailService userDetailService;
    
    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        LOG.debug("retrieve user");
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        UserDetails userDetails=userDetailService.loadUserByUsername(jwtAuthenticationToken.getToken());        	
        if (!userDetails.isEnabled())
        	throw new DisabledException("Account disabled. Please verify");      	
        return userDetails;
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return super.authenticate(authentication);
    }
}
