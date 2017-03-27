package my.edu.umk.pams.account.common.dao;

import my.edu.umk.pams.account.common.model.AcStateCode;
import my.edu.umk.pams.account.common.model.AcStateCodeImpl;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("stateCodeDao")
public class AcStateCodeDaoImpl extends GenericDaoSupport<Long, AcStateCode> implements AcStateCodeDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcStateCodeDaoImpl.class);

    public AcStateCodeDaoImpl() {
        super(AcStateCodeImpl.class);
    }

    @Override
    public AcStateCode findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcStateCode s where s.code = :code and  " +
                " s.metadata.state = :state");
        query.setString("code", code);
        query.setCacheable(true);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return (AcStateCode) query.uniqueResult();
    }

    @Override
    public List<AcStateCode> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcStateCode s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter) " +
                "or upper(s.countryCode.code) like upper(:filter) " +
                "or upper(s.countryCode.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return query.list();
    }

    @Override
    public List<AcStateCode> find(my.edu.umk.pams.account.common.model.AcCountryCode countryCode, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcStateCode s where " +
                "s.countryCode = :countryCode " +
                "and s.metadata.state = :state ");
        query.setEntity("countryCode", countryCode);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<AcStateCode>) query.list();
    }

    @Override
    public List<AcStateCode> find(String filter, my.edu.umk.pams.account.common.model.AcCountryCode countryCode, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcStateCode s where " +
                "s.countryCode = :countryCode " +
                "and (upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter) " +
                "or upper(s.countryCode.code) like upper(:filter) " +
                "or upper(s.countryCode.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setEntity("countryCode", countryCode);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setCacheable(true);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<AcStateCode>) query.list();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcStateCode s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter) " +
                "or upper(s.countryCode.code) like upper(:filter) " +
                "or upper(s.countryCode.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setCacheable(true);
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(my.edu.umk.pams.account.common.model.AcCountryCode countryCode) {
        org.hibernate.Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcStateCode s where " +
                "s.countryCode = :countryCode " +
                "and s.metadata.state = :state ");
        query.setEntity("countryCode", countryCode);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter, my.edu.umk.pams.account.common.model.AcCountryCode countryCode) {
        org.hibernate.Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcStateCode s where " +
                "s.countryCode = :countryCode " +
                "and (upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter) " +
                "or upper(s.countryCode.code) like upper(:filter) " +
                "or upper(s.countryCode.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isExists(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from AcStateCode s where " +
                "s.code = :code " +
                "and s.metadata.state = :state ");
        query.setString("code", code);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return 0 < ((Long) query.uniqueResult()).intValue();
    }
}
