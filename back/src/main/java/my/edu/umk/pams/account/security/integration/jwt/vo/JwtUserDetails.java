package my.edu.umk.pams.account.security.integration.jwt.vo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by shazin on 12/9/16.
 */
public class JwtUserDetails implements UserDetails {

    private final String token;

    private final String username;

    private final String password;

    private final Collection<GrantedAuthority> authorities;

    private final Long userId;

    public JwtUserDetails(String token, String username, String password, Collection<GrantedAuthority> authorities, Long userId) {
        this.token = token;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.userId = userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getToken() {
        return token;
    }

    public Long getUserId() {
        return userId;
    }
}
