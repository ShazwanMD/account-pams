package my.edu.umk.pams.account.core;

import my.edu.umk.pams.account.identity.model.AcUser;
import org.apache.commons.lang3.Validate;
import org.hibernate.*;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.type.LongType;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.InstantiationException;
import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author canang technologies
 * @since 1/27/14
 */
public class GenericDaoSupport<K, I> implements GenericDao<K, I>, InitializingBean {

    public static final String WILDCARD = "%";
    @Deprecated // not recursive
    public static final String ACL_QUERY = "select aoi.objectIdIdentity from AclObjectIdentity " +
            "aoi join aoi.entries e " +
            "where aoi.objectClass.aclClass = :clazz " +
            "and e.sid.sid in (:name)";
    private static final Logger LOG = LoggerFactory.getLogger(GenericDaoSupport.class);
    private final String RECURSIVE_ACL_QUERY1 = "select distinct aoi.object_id_identity as id from ACL_OBJECT_IDENTITY AOI  " +
            "left join ACL_ENTRY AE on AE.ACL_OBJECT_IDENTITY in (" +
            "with recursive Q as " +
            " ( select  PARENT_OBJECT, ID " +
            "     from    ACL_OBJECT_IDENTITY " +
            "     where   ID = AOI.ID " +
            "     union all " +
            "     select  AOI.PARENT_OBJECT, AOI.ID " +
            "     from    ACL_OBJECT_IDENTITY AOI " +
            "     join    Q " +
            "     on      AOI.ID = Q.PARENT_OBJECT " +
            " )" +
            " select  ID from  Q " +
            ") " +
            "left join ACL_CLASS AC ON AC.ID = AOI.OBJECT_ID_CLASS " +
            "left join ACL_SID SID ON SID.ID = AE.SID ";

    private final String RECURSIVE_ACL_QUERY =
            "with recursive top_buttom as   (" +
                    "  select parent_object,id from (" +
                    "  " +
                    "        with recursive bottom_up(PARENT_OBJECT,ID) as   (" +
                    "            select aoi.parent_object,aoi.id from ACL_AOID aoi" +
                    "            where AOI.class = '%s'" +
                    "            union all" +
                    "            select  AOI.PARENT_OBJECT, AOI.ID" +
                    "            from    ACL_AOID AOI" +
                    "              inner join  bottom_up on AOI.ID = bottom_up.PARENT_OBJECT" +
                    "          )  " +
                    "          select distinct aoi.id,aoi.parent_object,aoi.sid,aoi.class from ACL_AOID AOI" +
                    "            inner join bottom_up q on aoi.id = q.id " +
                    "            " +
                    "            ) a" +
                    "  where sid in (%s)" +
                    "  union all" +
                    "  select  p.PARENT_OBJECT, p.ID" +
                    "  from    (" +
                    "        with recursive bottom_up(PARENT_OBJECT,ID) as   (" +
                    "            select aoi.parent_object,aoi.id from ACL_AOID aoi" +
                    "            where AOI.class = '%s'" +
                    "            union all" +
                    "            select  AOI.PARENT_OBJECT, AOI.ID" +
                    "            from    ACL_AOID AOI" +
                    "              inner join  bottom_up on AOI.ID = bottom_up.PARENT_OBJECT" +
                    "          )  " +
                    "          " +
                    "          select distinct aoi.id,aoi.parent_object,aoi.sid,aoi.class from ACL_AOID AOI" +
                    "            inner join bottom_up q on aoi.id = q.id" +
                    "            ) p" +
                    "            inner join  top_buttom on p.parent_object = top_buttom.id" +
                    "        )" +
                    " select b.object_id_identity as id from top_buttom t" +
                    " inner join ACL_AOID b on t.id = b.id" +
                    " where b.class = '%s'";

    @Autowired(required = true)
    protected SessionFactory sessionFactory;

    private Class<I> interfaceClass;
    private Class entityClass;

    public GenericDaoSupport(Class entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        interfaceClass = (Class<I>) genericSuperclass.getActualTypeArguments()[1];
    }

    @Override
    public I newInstance() {
        try {
            return (I) entityClass.newInstance();
        } catch (IllegalAccessException e) {
        } catch (InstantiationException e) {
        }
        return null;
    }

    public I refresh(I i) {
        sessionFactory.getCurrentSession().refresh(i);
        return i;
    }

    public I findById(K k) {
        Session session = sessionFactory.getCurrentSession();
        return (I) session.get(entityClass, (Serializable) k);
    }

    public List<I> find() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(entityClass).list();
    }

    public List<I> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a "
                + " from " + entityClass.getName() + " a");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<I>) query.list();
    }

    @Override
    public List<I> findAuthorized(Set<String> sids) {
        Validate.notNull(sids, "Sids cannot be null");

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a " + " from " + entityClass.getName() + " a " +
                "where a.id in (" + ACL_QUERY + ")");
        query.setString("clazz", interfaceClass.getCanonicalName());
        query.setParameterList("name", sids);
        return (List<I>) query.list();
    }

    @Override
    public List<I> findAuthorized(Set<String> sids, Integer offset, Integer limit) {
        Validate.notNull(sids, "Sids cannot be null");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a " + " from " + entityClass.getName() + " a " +
                "where a.id in (" + ACL_QUERY + ")");
        query.setString("clazz", interfaceClass.getCanonicalName());
        query.setParameterList("name", sids);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<I>) query.list();
    }

    @Override
    public List<Long> findAuthorizedIds(Set<String> sids) {
        Validate.notNull(sids, "Sids cannot be null");
        Session session = sessionFactory.getCurrentSession();
//        String queryString = RECURSIVE_ACL_QUERY + String.format(" where AC.CLASS = '%s' and SID.SID in (%s) ", interfaceClass.getCanonicalName(), sidsToStringArray(sids));
        String queryString = String.format(RECURSIVE_ACL_QUERY, interfaceClass.getCanonicalName(), sidsToStringArray(sids), interfaceClass.getCanonicalName(), interfaceClass.getCanonicalName());
        SQLQuery query = session.createSQLQuery(queryString);
        query.addScalar("ID", LongType.INSTANCE);
        query.setCacheable(true);

        List<Long> result = new ArrayList<Long>();
        try {
            result = (List<Long>) query.list();
        } catch (GenericJDBCException e) {
            Throwable cause = e.getCause();
            if (!(cause instanceof PSQLException && cause.getMessage().equals("No results were returned by the query."))) {
                e.printStackTrace();
                throw e;
            }
        }
        return result;
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(a) "
                + " from " + entityClass.getName() + " a");
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countAuthorized(Set<String> sids) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(a) "
                + " from " + entityClass.getName() + " a where a.id in (" + ACL_QUERY + ")");
        query.setString("clazz", interfaceClass.getCanonicalName());
        query.setParameterList("name", sids);
        return ((Long) query.uniqueResult()).intValue();
    }

    public void save(I entity, AcUser user) {
        Validate.notNull(user, "User cannot be null");
        Validate.notNull(entity, "Object cannot be null");

        try {
            Session session = sessionFactory.getCurrentSession();
            prepareMetadata(entity, user, true);
            prepareFlowData(entity, user);
            session.save(entity);
        } catch (HibernateException e) {
            LOG.debug("error occurred", e);
        }
    }

    public void saveOrUpdate(I entity, AcUser user) {
        Validate.notNull(user, "User cannot be null");
        Validate.notNull(entity, "Object cannot be null");

        try {
            Session session = sessionFactory.getCurrentSession();
            prepareMetadata(entity, user, true);
            prepareFlowData(entity, user);
            session.saveOrUpdate(entity);
        } catch (HibernateException e) {
            LOG.debug("error occurred", e);
        }
    }

    public void update(I entity, AcUser user) {
        Validate.notNull(user, "User cannot be null");
        Validate.notNull(entity, "Object cannot be null");

        Session session = sessionFactory.getCurrentSession();
        prepareMetadata(entity, user, true);
        session.update(entity);
    }

    public void deactivate(I entity, AcUser user) {
        Validate.notNull(user, "User cannot be null");
        Validate.notNull(entity, "Object cannot be null");

        // session
        Session session = sessionFactory.getCurrentSession();
        prepareMetadata(entity, user, false);
        session.update(entity);
    }

    /**
     * @param entity
     * @param user
     */
    public void remove(I entity, AcUser user) {
        Validate.notNull(user, "User cannot be null");
        Validate.notNull(entity, "Object cannot be null");

        Session session = sessionFactory.getCurrentSession();
        prepareMetadata(entity, user, false);
        session.update(entity);
    }

    @Override
    public void delete(I entity, AcUser user) {
        Validate.notNull(user, "User cannot be null");
        Validate.notNull(entity, "Object cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(entity);
    }

    private void prepareMetadata(I i, AcUser user, boolean active) {
        if (i instanceof AcMetaObject) {
            AcMetadata metadata = null;
            if (((AcMetaObject) i).getMetadata() != null)
                metadata = ((AcMetaObject) i).getMetadata();
            else
                metadata = new AcMetadata();
            metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            metadata.setCreatorId(user.getId());
            metadata.setState(active ? AcMetaState.ACTIVE : AcMetaState.INACTIVE);
            ((AcMetaObject) i).setMetadata(metadata);
        }
    }

    private void prepareFlowData(I i, AcUser user) {
        if (i instanceof AcFlowObject) {
            AcFlowdata flowData = null;
            if (((AcFlowObject) i).getFlowdata() != null)
                flowData = ((AcFlowObject) i).getFlowdata();
            else
                flowData = new AcFlowdata();
            flowData.setDraftedDate(new Timestamp(System.currentTimeMillis()));
            flowData.setDrafterId(user.getId());
            flowData.setState(AcFlowState.DRAFTED);
            ((AcFlowObject) i).setFlowdata(flowData);
        }
    }

    private String sidsToStringArray(Set<String> sids) {
        String[] sidsArr = sids.toArray(new String[sids.size()]);
        StringBuffer buff = new StringBuffer();
        for (String s : sidsArr) {
            buff.append("'" + s + "',");
        }
        if (sids.size() > 0) buff.deleteCharAt(buff.length() - 1);
        return buff.toString();
    }
}
