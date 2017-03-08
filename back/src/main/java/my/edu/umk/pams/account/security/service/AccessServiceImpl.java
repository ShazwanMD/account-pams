package my.edu.umk.pams.account.security.service;

import my.edu.umk.pams.account.identity.model.AcGroup;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.security.dao.AclObjectIdentityDao;
import my.edu.umk.pams.account.security.event.AccessEvent;
import my.edu.umk.pams.account.security.integration.AcAclPermissionEvaluator;
import my.edu.umk.pams.account.security.integration.AcPermission;
import my.edu.umk.pams.account.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author canang technologies
 * @since 4/18/14
 */
@Transactional
@Service("accessService")
public class AccessServiceImpl implements AccessService {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private AclObjectIdentityDao aclObjectIdentityDao;

    @Autowired
    private AcAclPermissionEvaluator aclPermissionEvaluator;

    public Integer countAuthorizedObject(String filter, Class clazz, AcUser user) {
        return aclObjectIdentityDao.count(filter, clazz, retrieveSids(user));
    }

    public List<Long> findAuthorizedObject(String filter, Class clazz, AcUser user, Integer offset, Integer limit) {
        return aclObjectIdentityDao.find(filter, clazz, retrieveSids(user), offset, limit);
    }

    @Override
    public Set<my.edu.umk.pams.account.identity.model.AcPrincipal> findGrants(my.edu.umk.pams.account.core.AcMetaObject object) {
        return aclObjectIdentityDao.findGrants(object);
    }

    @Override
    public Set<my.edu.umk.pams.account.identity.model.AcPrincipal> findGrants(my.edu.umk.pams.account.core.AcMetaObject object, AcPermission permission) {
        return aclObjectIdentityDao.findGrants(object, permission);
    }

    @Override
    public boolean checkPermission(my.edu.umk.pams.account.core.AcMetaObject object, my.edu.umk.pams.account.identity.model.AcPrincipal principal, AcPermission permission) {
        return aclPermissionEvaluator.checkPermissionByProxy(principal, object, permission);
    }

    @Override
    public void grantPermission(my.edu.umk.pams.account.core.AcMetaObject object, my.edu.umk.pams.account.identity.model.AcPrincipal principal, AcPermission permission) {
        context.publishEvent(new AccessEvent(object, principal, permission, AccessEvent.Command.GRANT));
    }

    @Override
    public void inheritPermission(my.edu.umk.pams.account.core.AcMetaObject parent, my.edu.umk.pams.account.core.AcMetaObject object) {
        context.publishEvent(new AccessEvent(parent, object));
    }

    @Override
    public void revokePermission(my.edu.umk.pams.account.core.AcMetaObject object, my.edu.umk.pams.account.identity.model.AcPrincipal principal, AcPermission permission) {
        context.publishEvent(new AccessEvent(object, principal, permission, AccessEvent.Command.REVOKE));
    }

    @Override
    public boolean hasPermission(my.edu.umk.pams.account.core.AcMetaObject object, my.edu.umk.pams.account.identity.model.AcPrincipal principal, AcPermission permission) {
        if (null == object) return false;
        if (null == principal) return false;
        if (null == permission) return false;
        return aclPermissionEvaluator.checkPermissionByProxy(principal, object, permission);
    }

    @Override
    public boolean hasPermission(my.edu.umk.pams.account.core.AcMetaObject object, Authentication authentication, AcPermission permission) {
        if (null == object) return false;
        if (null == authentication) return false;
        if (null == permission) return false;
        return aclPermissionEvaluator.hasPermission(authentication, object, permission);
    }

    @Override
    public Integer countArchivedRecord(String filter, Date startDate, Date endDate, Class<?> aClass) {
        return aclObjectIdentityDao.count(filter, aClass, identityService.findEffectiveGroupsAsString(Util.getCurrentUser()));
    }

    @Override
    public Integer countArchivedRecord(String filter, my.edu.umk.pams.account.core.AcFlowState flowType, Date startDate, Date endDate, Class<?> aClass) {
        return aclObjectIdentityDao.count(filter, aClass, identityService.findEffectiveGroupsAsString(Util.getCurrentUser()));
    }

    @Override
    public List<Long> findArchived(String filter, my.edu.umk.pams.account.core.AcFlowState flowType, Date startDate, Date endDate, Class<?> aClass, Integer offset, Integer limit) {
        return aclObjectIdentityDao.find(filter, aClass, identityService.findEffectiveGroupsAsString(Util.getCurrentUser()), offset, limit);
    }

    private Set<String> retrieveSids(AcUser user) {
        Set<String> sids = new HashSet<String>();
        List<AcGroup> groups = identityService.findImmediateGroups(user);
        for (AcGroup group : groups) {
            sids.add(group.getName());
        }
        sids.add(user.getName());
        return sids;
    }


}
