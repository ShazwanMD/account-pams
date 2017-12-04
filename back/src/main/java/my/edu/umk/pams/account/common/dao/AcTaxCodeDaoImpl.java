package my.edu.umk.pams.account.common.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import my.edu.umk.pams.account.common.model.AcSecurityChargeCode;
import my.edu.umk.pams.account.common.model.AcTaxCode;
import my.edu.umk.pams.account.common.model.AcTaxCodeImpl;
import my.edu.umk.pams.account.core.GenericDaoSupport;

@Repository("taxCodeDao")
public class AcTaxCodeDaoImpl extends GenericDaoSupport<Long, AcTaxCode> implements AcTaxCodeDao {


    private static final Logger LOG = LoggerFactory.getLogger(AcTaxCodeDaoImpl.class);

    public AcTaxCodeDaoImpl() {
        super(AcTaxCodeImpl.class);
    }

    @Override
    public AcTaxCode findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcTaxCode s where s.code = :code and  " +
                " s.metadata.state = :state");
        query.setString("code", code);
        query.setCacheable(true);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return (AcTaxCode) query.uniqueResult();
    }
    
    @Override
    public List<AcTaxCode> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcTaxCode s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcTaxCode>) query.list();
    }
    
    @Override
    public List<AcTaxCode> findAcTaxCodesByActive(Boolean active) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcTaxCode s " +
                "where s.active = :active ");
        query.setBoolean("active", active);
        return (List<AcTaxCode>) query.list();
    }
    
    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcTaxCode s where " +
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
        Query query = session.createQuery("select count(*) from AcTaxCode s where " +
                "s.code = :code " +
                "and s.metadata.state = :state ");
        query.setString("code", code);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return 0 < ((Long) query.uniqueResult()).intValue();
    }
}
