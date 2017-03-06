package my.edu.umk.pams.account.security.integration;

import my.edu.umk.pams.account.identity.dao.AcGroupDao;
import my.edu.umk.pams.account.identity.dao.AcUserDao;
import my.edu.umk.pams.account.identity.model.AcGroup;
import my.edu.umk.pams.account.identity.model.AcUser;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * @author canang technologies
 * @since 1/30/14
 */
@Service("userDetailService")
@Transactional
public class AdUserDetailService implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(AdUserDetailService.class);

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    private AcUserDao userDao;

    @Autowired
    private AcGroupDao groupDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        AcUser user = userDao.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("No such user");
        LOG.debug("loading #{} {}", user.getId(), user.getUsername());
        return new AdUserDetails(user, loadGrantedAuthoritiesFor(user));
    }

    private Set<GrantedAuthority> loadGrantedAuthoritiesFor(AcUser user) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        for (my.edu.umk.pams.account.identity.model.AcPrincipalRole role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().name()));
        }

        Set<AcGroup> groups = groupDao.findEffectiveAsNative(user);
        for (AcGroup group : groups) {
            for (my.edu.umk.pams.account.identity.model.AcPrincipalRole role : group.getRoles()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().name()));
            }
        }

        LOG.info("load auth for #{}{}", user.getId(), user.getUsername());
        return grantedAuthorities;
    }
}
