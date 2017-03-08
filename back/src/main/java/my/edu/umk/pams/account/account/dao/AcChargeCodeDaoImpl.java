package my.edu.umk.pams.account.account.dao;

import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.account.model.AcChargeCodeImpl;
import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author PAMS
 */
@Repository("acChargeCodeDao")
public class AcChargeCodeDaoImpl extends GenericDaoSupport<Long, AcChargeCode> implements AcChargeCodeDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcChargeCodeDaoImpl.class);

    public AcChargeCodeDaoImpl() {
        super(AcChargeCodeImpl.class);
    }

    @Override
    public AcChargeCode findByCode(String code) {
        org.hibernate.Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcChargeCode s where " +
                "s.code = :code " +
                "and s.metadata.state = :state");
        query.setString("code", code);
        query.setCacheable(true);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (AcChargeCode) query.uniqueResult();
    }

    @Override
    public AcChargeCode findByDescription(String description) {
        org.hibernate.Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcChargeCode s where " +
                "s.description = :description " +
                "and s.metadata.state = :state");
        query.setString("description", description);
        query.setCacheable(true);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (AcChargeCode) query.uniqueResult();
    }

    @Override
    public List<AcChargeCode> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from AcChargeCode i where " +
                "(upper(i.code) like upper(:filter) " +
                "or upper(i.description) like upper(:filter)) " +
                "and i.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<AcChargeCode>) query.list();
    }


    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(i) from AcChargeCode i where " +
                "(upper(i.code) like upper(:filter) " +
                "or upper(i.description) like upper(:filter)) " +
                "and i.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }
}
