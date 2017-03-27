package my.edu.umk.pams.account.security.integration.jwt.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenExpiredException extends AuthenticationException {

    public JwtTokenExpiredException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtTokenExpiredException(String msg) {
        super(msg);
    }
}
