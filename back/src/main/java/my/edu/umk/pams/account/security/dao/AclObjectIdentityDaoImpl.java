package my.edu.umk.pams.account.security.dao;

import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.dao.AcPrincipalDao;
import my.edu.umk.pams.account.security.integration.AdPermission;
import my.edu.umk.pams.account.security.model.AclObjectIdentity;
import my.edu.umk.pams.account.security.model.AclObjectIdentityImpl;
import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author canang technologies
 * @since 1/31/14
 */
@Repository("aclObjectIdentityDao")
public final class AclObjectIdentityDaoImpl extends GenericDaoSupport<Long, AclObjectIdentity> implements AclObjectIdentityDao {

    private static final Logger LOG = LoggerFactory.getLogger(AclObjectIdentityDaoImpl.class);

    @Autowired
    private AcPrincipalDao principalDao;

    public AclObjectIdentityDaoImpl() {
        super(AclObjectIdentityImpl.class);
    }

    @Override
    public Set<my.edu.umk.pams.account.identity.model.AcPrincipal> findGrants(my.edu.umk.pams.account.core.AcMetaObject object) {
        Set<my.edu.umk.pams.account.identity.model.AcPrincipal> principals = new HashSet<my.edu.umk.pams.account.identity.model.AcPrincipal>();
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sid.sid from AclEntry ae join ae.objectIdentity aoi join ae.sid sid where " +
                "aoi.objectClass.aclClass = :clazz " +
                "and aoi.objectIdIdentity = :objectIdentityId");
        query.setString("clazz", object.getInterfaceClass().getCanonicalName());
        query.setLong("objectIdentityId", object.getId());
        query.setCacheable(true);
        List<String> sids = query.list();
        for (String sid : sids) {
            LOG.debug("sid {}", sid);
            principals.add(principalDao.findByName(sid));
        }
        return principals;
    }

    @Override
    public Set<my.edu.umk.pams.account.identity.model.AcPrincipal> findGrants(my.edu.umk.pams.account.core.AcMetaObject object, AdPermission permission) {
        Set<my.edu.umk.pams.account.identity.model.AcPrincipal> principals = new HashSet<my.edu.umk.pams.account.identity.model.AcPrincipal>();
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("" +
                "select sid.sid from AclEntry ae join ae.objectIdentity aoi join ae.sid sid where " +
                "aoi.objectClass.aclClass = :clazz " +
                "and ae.mask = :permissionMask " +
                "and aoi.objectIdIdentity = :objectIdentityId");
        query.setString("clazz", object.getInterfaceClass().getCanonicalName());
        query.setLong("objectIdentityId", object.getId());
        query.setInteger("permissionMask", permission.getMask());
        query.setCacheable(true);
        List<String> sids = query.list();
        for (String sid : sids) {
            principals.add(principalDao.findByName(sid));
        }
        return principals;
    }

    @Override
    public List<Long> find(String filter, Class clazz, Set<String> sids, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        session.clear();

        // set default filter
        if (switchFilter(filter, clazz, session))
            return new ArrayList<Long>();

        // query
        Query query = session.createQuery("select distinct aoi.objectIdIdentity from AclObjectIdentity aoi join aoi.entries e where " +
                "aoi.objectClass.aclClass = :clazz " +
                "and e.sid.sid in (:name) ");

        // DISTINCT + ORDER BY ISSUE
        // http://stackoverflow.com/questions/968438/hql-order-by-query-giving-problem
        // "order by e.aclOrder desc");
        query.setString("clazz", clazz.getCanonicalName());
        query.setParameterList("name", sids);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<Long>) query.list();
    }

    @Override
    public Integer count(String filter, Class clazz, Set<String> sids) {
        Session session = sessionFactory.getCurrentSession();
        session.clear();

        // switch filter
        if (switchFilter(filter, clazz, session))
            return 0;

        // query
        Query query = session.createQuery("select count(distinct aoi.id) from AclObjectIdentity aoi join aoi.entries e where " +
                "aoi.objectClass.aclClass = :clazz " +
                "and e.sid.sid in (:name) ");
        query.setString("clazz", clazz.getCanonicalName());
        query.setParameterList("name", sids);
        query.setCacheable(true);
        return ((Long) query.uniqueResult()).intValue();
    }

    private boolean switchFilter(String filter, Class clazz, Session session) {
        Filter hqlFilter;
        try {
            if (filter == null) filter = "";
            hqlFilter = session.enableFilter("filter1_" + clazz.getSimpleName());
            hqlFilter.setParameter("filter", "%" + filter + "%");
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }
}
