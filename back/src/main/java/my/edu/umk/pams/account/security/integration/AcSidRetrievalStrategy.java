package my.edu.umk.pams.account.security.integration;

import my.edu.umk.pams.account.identity.dao.AcGroupDao;
import my.edu.umk.pams.account.identity.dao.AcPrincipalDao;
import my.edu.umk.pams.account.identity.dao.AcUserDao;
import my.edu.umk.pams.account.identity.model.AcGroup;
import my.edu.umk.pams.account.identity.model.AcUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.domain.SidRetrievalStrategyImpl;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author canang technologies
 * @since 1/13/14
 */
public class AcSidRetrievalStrategy extends SidRetrievalStrategyImpl {

    private static final Logger LOG = LoggerFactory.getLogger(AcSidRetrievalStrategy.class);

    @Autowired
    private AcPrincipalDao principalDao;

    @Autowired
    private AcGroupDao groupDao;

    @Autowired
    private AcUserDao userDao;

    @Override
    public List<Sid> getSids(Authentication authentication) {
        // add current sid to collection based
        // on our authentication
        List<Sid> sids = new ArrayList<Sid>();
        sids.addAll(super.getSids(authentication));

        // find all groups by username
        if (authentication.getPrincipal() instanceof UserDetails) {
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();
            AcUser user = userDao.findByUsername(username);
            sids.addAll(getSids(user));
        }
        return sids;
    }

    public Set<String> getSidsAsSet(Authentication authentication) {
        Set<String> sidSets = new HashSet<String>();
        List<Sid> sids = super.getSids(authentication);
        for (Sid sid : sids) {
            if (sid instanceof PrincipalSid)
                sidSets.add(((PrincipalSid) sid).getPrincipal());
        }

        if (authentication.getPrincipal() instanceof UserDetails) {
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();
            AcUser user = userDao.findByUsername(username);
            List<Sid> sids2 = getSids(user);
            for (Sid sid : sids2) {
                if (sid instanceof PrincipalSid)
                    sidSets.add(((PrincipalSid) sid).getPrincipal());
            }
        }
        return sidSets;
    }

    public List<Sid> getSids(my.edu.umk.pams.account.identity.model.AcPrincipal principal) {
        List<Sid> sids = new ArrayList<Sid>();
        sids.add(new PrincipalSid(principal.getName()));
        Set<AcGroup> groups = groupDao.findEffectiveAsNative(principal);
        for (AcGroup group : groups) {
            sids.add(new PrincipalSid(group.getName()));
        }
        return sids;
    }
}

