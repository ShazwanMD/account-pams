package my.edu.umk.pams.account.common.dao;

import my.edu.umk.pams.account.common.model.AcStudyCenterCode;
import my.edu.umk.pams.account.common.model.AcStudyCenterCodeImpl;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("acStudyCenterCodeDao")
public class AdStudyCenterCodeDaoImpl extends GenericDaoSupport<Long, AcStudyCenterCode> implements AcStudyCenterCodeDao {

    private static final Logger LOG = LoggerFactory.getLogger(AdStudyCenterCodeDaoImpl.class);

    public AdStudyCenterCodeDaoImpl() {
        super(AcStudyCenterCodeImpl.class);
    }

    @Override
    public AcStudyCenterCode findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcStudyCenterCode s where " +
                "s.code = :code " +
                "and s.metadata.state = :state");
        query.setString("code", code);
        query.setCacheable(true);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return (AcStudyCenterCode) query.uniqueResult();
    }

    @Override
    public List<AcStudyCenterCode> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcStudyCenterCode s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcStudyCenterCode>) query.list();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcStudyCenterCode s where " +
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
        Query query = session.createQuery("select count(*) from AcStudyCenterCode s where " +
                "s.code = :code " +
                "and s.metadata.state = :state ");
        query.setString("code", code);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return 0 < ((Long) query.uniqueResult()).intValue();
    }
}
