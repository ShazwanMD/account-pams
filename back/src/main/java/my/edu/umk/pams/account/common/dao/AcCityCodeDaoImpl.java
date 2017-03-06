package my.edu.umk.pams.account.common.dao;

import my.edu.umk.pams.account.common.model.AcCityCode;
import my.edu.umk.pams.account.common.model.AcCityCodeImpl;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("acCityCodeDao")
public class AcCityCodeDaoImpl extends GenericDaoSupport<Long, AcCityCode> implements AcCityCodeDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcCityCodeDaoImpl.class);

    public AcCityCodeDaoImpl() {
        super(AcCityCodeImpl.class);
    }

    @Override
    public AcCityCode findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcCityCode s where " +
                "s.code = :code " +
                "and s.metadata.state = :state");
        query.setString("code", code);
        query.setCacheable(true);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return (AcCityCode) query.uniqueResult();
    }

    // TODO:
    @Override
    public List<AcCityCode> find(my.edu.umk.pams.account.common.model.AcStateCode stateCode) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcCityCode s where " +
                "s.metadata.state = :state ");
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcCityCode>) query.list();
    }

    @Override
    public List<AcCityCode> find(my.edu.umk.pams.account.common.model.AcStateCode stateCode, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcCityCode s where " +
                "s.metadata.state = :state ");
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcCityCode>) query.list();
    }

    @Override
    public List<AcCityCode> find(String filter, my.edu.umk.pams.account.common.model.AcStateCode stateCode, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcCityCode s where " +
                "s.metadata.state = :state ");
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcCityCode>) query.list();
    }

    @Override
    public Integer count(my.edu.umk.pams.account.common.model.AcStateCode stateCode) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcCityCode s where " +
                "s.metadata.state = :state ");
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter, my.edu.umk.pams.account.common.model.AcStateCode stateCode) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcCityCode s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcCityCode s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isExists(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from AcCityCode s where " +
                "s.code = :code " +
                "and s.metadata.state = :state ");
        query.setString("code", code);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return 0 < ((Long) query.uniqueResult()).intValue();
    }
}
