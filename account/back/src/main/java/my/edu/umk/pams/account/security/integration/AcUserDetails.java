package my.edu.umk.pams.account.security.integration;

import my.edu.umk.pams.account.identity.model.AcUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @author canang technologies
 * @since 1/30/14
 */
public class AcUserDetails implements UserDetails {

    private AcUser user;
    private Set<GrantedAuthority> grantedAuthorities;

    public AcUserDetails() {
    }

    public AcUserDetails(AcUser user, Set<GrantedAuthority> grantedAuthorities) {
        this.user = user;
        this.grantedAuthorities = grantedAuthorities;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    public String getPassword() {
        return user.getPassword();
    }

    public String getUsername() {
        return user.getName();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return user.isLocked();
    }

    public void setUser(AcUser user) {
        this.user = user;
    }

    public AcUser getUser() {
        return user;
    }

    public String getRealName() {
        return user.getRealName();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public Long getId() {
        return user.getId();
    }
}
