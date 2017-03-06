package my.edu.umk.pams.account.security.integration.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final String token;

    public JwtAuthenticationToken() {
        this("");
    }

    public JwtAuthenticationToken(String token) {
        super("N/A", "N/A");
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
