package my.edu.umk.pams.account.security.dao;

import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.security.integration.AcPermission;
import my.edu.umk.pams.account.security.model.AclObjectIdentity;

import java.util.List;
import java.util.Set;

/**
 * @author canang technologies
 * @since 1/31/14
 */
public interface AclObjectIdentityDao extends GenericDao<Long, AclObjectIdentity> {

    Set<my.edu.umk.pams.account.identity.model.AcPrincipal> findGrants(my.edu.umk.pams.account.core.AcMetaObject object);

    Set<my.edu.umk.pams.account.identity.model.AcPrincipal> findGrants(my.edu.umk.pams.account.core.AcMetaObject object, AcPermission permission);

    List<Long> find(String filter, Class clazz, Set<String> sids, Integer offset, Integer limit);

    Integer count(String filter, Class clazz, Set<String> sids);

}
