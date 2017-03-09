package my.edu.umk.pams.account.security.service;

import my.edu.umk.pams.account.identity.dao.AcUserDao;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.security.dao.AclObjectIdentityDao;
import my.edu.umk.pams.account.security.integration.AcSidRetrievalStrategy;
import my.edu.umk.pams.account.system.service.SystemService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author canang technologies
 * @since 1/31/14
 */
@Transactional
@Service("securityService")
public class SecurityServiceImpl implements SecurityService {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityServiceImpl.class);
    private static final StringKeyGenerator tokenGenerator = KeyGenerators.string();
    private static final long ONE_DAY = DateTime.now().plusDays(1).getMillis();

    @Autowired
    private SystemService systemService;

    @Autowired
    private AclObjectIdentityDao aclObjectIdentityDao;

    @Autowired
    private AcSidRetrievalStrategy sidRetrievalStrategy;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AcUserDao userDao;


    @Override
    public List<my.edu.umk.pams.account.core.AcMetaObject> find(Authentication authentication, Class clazz, Integer offset, Integer limit) {
        List<my.edu.umk.pams.account.core.AcMetaObject> metaObjects = new ArrayList<my.edu.umk.pams.account.core.AcMetaObject>();
        Set<String> sids = sidRetrievalStrategy.getSidsAsSet(authentication);
        List<Long> longs = aclObjectIdentityDao.find("", clazz, sids, offset, limit);
        for (Long aLong : longs) {
            metaObjects.add((my.edu.umk.pams.account.core.AcMetaObject) sessionFactory.getCurrentSession().get(clazz, aLong));
        }
        return metaObjects;
    }

    @Override
    public Integer count(Authentication authentication, Class clazz) {
        Set<String> sids = sidRetrievalStrategy.getSidsAsSet(authentication);
        return aclObjectIdentityDao.count("", clazz, sids);
    }

    @Override
    public AcUser getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) {
            return userDao.findByUsername(((UserDetails) auth.getPrincipal()).getUsername());
        } else if (auth.getPrincipal() instanceof User) {
            return userDao.findByUsername(((User) auth.getPrincipal()).getUsername());
        } else {
            return null;
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
