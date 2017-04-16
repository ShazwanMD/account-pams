package my.edu.umk.pams.account.system.dao;

import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.system.model.AcConfiguration;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author canang technologies
 * @since 1/31/14
 */
@Repository("configurationDao")
public class AcConfigurationDaoImpl extends GenericDaoSupport<Long, AcConfiguration> implements AcConfigurationDao {

    public AcConfigurationDaoImpl() {
        super(my.edu.umk.pams.account.system.model.AcConfigurationImpl.class);
    }

    public AcConfigurationDaoImpl(Class clazz) {
        super(clazz);
    }

    @Override
    public AcConfiguration findByKey(String key) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcConfiguration s where " +
                "s.key = :key and  " +
                " s.metadata.state = :state");
        query.setString("key", key);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (AcConfiguration) query.uniqueResult();
    }

    @Override
    public List<AcConfiguration> findByPrefix(String prefix) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcConfiguration s where " +
                "upper(s.key) like upper(:prefix)  " +
                "and s.metadata.state = :state");
        query.setString("prefix", prefix + WILDCARD);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return query.list();
    }

    @Override
    public List<AcConfiguration> find() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcConfiguration s where " +
                "s.metadata.state = :state");
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return query.list();
    }

    @Override
    public List<AcConfiguration> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcConfiguration s where " +
                "(upper(s.key) like upper(:filter)  " +
                "or upper(s.description) like upper(:filter))  " +
                "and s.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public List<AcConfiguration> find(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcConfiguration s where " +
                "upper(s.key) like upper(:filter)  " +
                "and s.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return query.list();
    }

    @Override
    public List<AcConfiguration> findInverse(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcConfiguration s where " +
                "upper(s.key) not like upper(:filter)  " +
                "and s.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return query.list();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcConfiguration s where " +
                "(upper(s.key) like upper(:filter)  " +
                "or upper(s.description) like upper(:filter))  " +
                "and s.metadata.state = :state");
        query.setCacheable(true);
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

}
