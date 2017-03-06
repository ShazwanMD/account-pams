package my.edu.umk.pams.account.security.integration.jwt.vo;

import java.util.Date;
import java.util.Random;

/**
 * Created by shazin on 12/9/16.
 */
public class JwtUser {

    private String username;

    private Long userId;

    private String role;

    public JwtUser() {
        this.userId = new Random(System.currentTimeMillis()).nextLong();
    }

    public JwtUser(String username, String role) {
        this();
        this.username = username;
        this.role = role;
    }

    private Date expirationDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
