package my.edu.umk.pams.account.security.integration.jwt.handler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import my.edu.umk.pams.account.security.integration.jwt.vo.JwtUser;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by shazin on 12/9/16.
 */
@Component
public class JwtHandler {

    private String secret = "!@#$%^&*()0987654321";
    private Long ttlSeconds = 36000L;

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    public JwtUser parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            JwtUser u = new JwtUser();
            u.setUsername(body.getSubject());
            u.setUserId(Long.parseLong((String) body.get("userId")));
            u.setRole((String) body.get("role"));
            u.setExpirationDate(body.getExpiration());
            return u;
        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     *
     * @param u the user for which the token will be generated
     * @return the JWT token
     */
    public String generateToken(JwtUser u) {
        Claims claims = Jwts.claims().setSubject(u.getUsername());
        claims.setExpiration(new Date(System.currentTimeMillis() + (ttlSeconds * 1000)));
        claims.put("userId", u.getUserId() + "");
        claims.put("role", u.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
