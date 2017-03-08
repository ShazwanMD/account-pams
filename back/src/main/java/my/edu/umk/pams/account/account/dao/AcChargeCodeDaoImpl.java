package my.edu.umk.pams.account.account.dao;

import my.utm.acad.sa.core.cmn.dao.GenericDaoSupport;
import my.utm.acad.sa.core.cmn.model.SaMetaState;
import my.utm.acad.sa.core.das.dao.SaChargeCodeDao;
import my.utm.acad.sa.core.das.model.SaChargeCode;
import my.utm.acad.sa.core.das.model.impl.SaChargeCodeImpl;
import my.utm.acad.sa.core.util.QueryUtil;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author team utmacad
 * @since 9/6/2015.
 */
@Repository("SaChargeCodeDao")
public class SaChargeCodeDaoImpl extends GenericDaoSupport<Long, SaChargeCode> implements SaChargeCodeDao {

    private static final Logger LOG = LoggerFactory.getLogger(SaChargeCodeDaoImpl.class);

    public SaChargeCodeDaoImpl() {
        super(SaChargeCodeImpl.class);
    }

    @Override
    public SaChargeCode findByCode(String code) {
        org.hibernate.Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from SaChargeCode s where " +
                "s.code = :code " +
                "and s.metadata.state = :state");
        query.setString("code", code);
        query.setCacheable(true);
        query.setInteger("state", SaMetaState.ACTIVE.ordinal());
        return (SaChargeCode) query.uniqueResult();
    }

    @Override
    public SaChargeCode findByDescription(String description) {
        org.hibernate.Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from SaChargeCode s where " +
                "s.description = :description " +
                "and s.metadata.state = :state");
        query.setString("description", description);
        query.setCacheable(true);
        query.setInteger("state", SaMetaState.ACTIVE.ordinal());
        return (SaChargeCode) query.uniqueResult();
    }

    @Override
    public List<SaChargeCode> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from SaChargeCode i where " +
                "(upper(i.code) like upper(:filter) " +
                "or upper(i.description) like upper(:filter)) " +
                "and i.metadata.state = :state ");
        query.setString("filter", QueryUtil.wildCard(filter));
        query.setInteger("state", SaMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<SaChargeCode>) query.list();
    }


    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(i) from SaChargeCode i where " +
                "(upper(i.code) like upper(:filter) " +
                "or upper(i.description) like upper(:filter)) " +
                "and i.metadata.state = :state ");
        query.setString("filter", QueryUtil.wildCard(filter));
        query.setInteger("state", SaMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }
}
