package my.edu.umk.pams.account.common.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import my.edu.umk.pams.account.common.model.AcSecurityChargeCode;
import my.edu.umk.pams.account.common.model.AcSecurityChargeCodeImpl;
import my.edu.umk.pams.account.core.GenericDaoSupport;


@Repository("securityChargesDao")
public class AcSecurityChargeCodeDaoImpl extends GenericDaoSupport<Long, AcSecurityChargeCode> implements AcSecurityChargeCodeDao {


    private static final Logger LOG = LoggerFactory.getLogger(AcSecurityChargeCodeDaoImpl.class);

    public AcSecurityChargeCodeDaoImpl() {
        super(AcSecurityChargeCodeImpl.class);
    }
    
    @Override
    public AcSecurityChargeCode findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcSecurityChargeCode s where " +
                " s.metadata.state = :state");
        query.setCacheable(true);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return (AcSecurityChargeCode) query.uniqueResult();
    }
    
    @Override
    public List<AcSecurityChargeCode> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcSecurityChargeCode s where " +
                " s.metadata.state = :state");
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcSecurityChargeCode>) query.list();
    }
    
    @Override
    public List<AcSecurityChargeCode> findAcSecurityChargeCodesByActive(Boolean active) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from AcSecurityChargeCode p " +
                "where p.active = :active ");
        query.setBoolean("active", active);
        return (List<AcSecurityChargeCode>) query.list();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcSecurityChargeCode s where " +
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
        Query query = session.createQuery("select count(*) from AcSecurityChargeCode s where " +
                "s.code = :code " +
                "and s.metadata.state = :state ");
        query.setString("code", code);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return 0 < ((Long) query.uniqueResult()).intValue();
    }
}
