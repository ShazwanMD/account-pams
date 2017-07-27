package my.edu.umk.pams.account.security.integration.jwt.handler;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.edu.umk.pams.account.security.integration.jwt.vo.JwtUser;
import my.edu.umk.pams.account.security.integration.jwt.vo.LoginResponse;

/**
 * Created by shazin on 12/7/16.
 */
public class JwtAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger LOG = LoggerFactory.getLogger(JwtAuthenticationSuccessHandler.class);

    @Autowired
    private JwtHandler jwtHandler;

    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_OK);
        LoginResponse success = new LoginResponse();
        StringBuilder roles = new StringBuilder();
        int size = authentication.getAuthorities().size();

        int i = 0;
        for (GrantedAuthority ga : authentication.getAuthorities()) {
            roles.append(ga.getAuthority());
            LOG.debug("grantedAuthority: " + ga.getAuthority());
            if (i != size - 1) {
                roles.append(",");
            }
            i++;
        }
        String username = "";
        LOG.debug("authentication: " + authentication.getPrincipal().getClass().getSimpleName());
        if (authentication.getPrincipal() instanceof User) {
            username = ((User) authentication.getPrincipal()).getUsername();
        } else if (authentication.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        } else {
            username = authentication.getPrincipal().toString();
        }
        LOG.debug("username: " + username);

        JwtUser jwtUser = new JwtUser(username, roles.toString());
        success.setToken(jwtHandler.generateToken(jwtUser));
        success.setStatus(HttpServletResponse.SC_OK);
        success.setCode(UUID.randomUUID().toString());
        response.getWriter().print(new ObjectMapper().writeValueAsString(success));
        response.getWriter().flush();
    }
}