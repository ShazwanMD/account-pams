package my.edu.umk.pams.account.security.integration.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.edu.umk.pams.account.security.integration.jwt.exception.InvalidLoginRequestException;

/**
 * Created by shazi on 12/28/2016.
 */
public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

    private boolean postOnly = true;

    public LoginAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    public LoginAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        if (this.postOnly && !httpServletRequest.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + httpServletRequest.getMethod());
        }

        ServletServerHttpRequest servletServerHttpRequest = new ServletServerHttpRequest(httpServletRequest);
        String username = null;
        String password = null;
        Map<String, String> loginRequest = objectMapper.readValue(servletServerHttpRequest.getBody(), Map.class);
        if (loginRequest == null || loginRequest.isEmpty()) {
            throw new IOException("Not a valid JSON Request Body");
        } else {
            username = loginRequest.get("username");
            password = loginRequest.get("password");
            if (username == null || username.length() == 0) {
                throw new InvalidLoginRequestException("username is not valid");
            }
            if (password == null || password.length() == 0) {
                throw new InvalidLoginRequestException("password is not valid");
            }
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        return getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


}
