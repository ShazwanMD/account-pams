package my.edu.umk.pams.account.account.dao;

import my.utm.acad.sa.core.cmn.dao.GenericDaoSupport;
import my.utm.acad.sa.core.cmn.model.SaMetaState;
import my.utm.acad.sa.core.das.dao.SaAcademicSessionDao;
import my.utm.acad.sa.core.das.model.SaAcademicSession;
import my.utm.acad.sa.core.das.model.impl.SaAcademicSessionImpl;
import my.utm.acad.sa.core.sys.dao.impl.SaGroupDaoImpl;
import my.utm.acad.sa.core.util.QueryUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author team utmacad
 * @since 20/4/2015
 */
@Repository("saAcademicSessionDao")
public class SaAcademicSessionDaoImpl extends GenericDaoSupport<Long, SaAcademicSession> implements SaAcademicSessionDao {

    private static final Logger LOG = LoggerFactory.getLogger(SaGroupDaoImpl.class);

    public SaAcademicSessionDaoImpl() {
        super(SaAcademicSessionImpl.class);
    }

    @Override
    public SaAcademicSession findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from SaAcademicSession s where s.code = :code and " +
                "s.metadata.state = :state");
        query.setString("code", code);
        query.setCacheable(true);
        query.setInteger("state", SaMetaState.ACTIVE.ordinal());
        return (SaAcademicSession) query.uniqueResult();
    }

    @Override
    public SaAcademicSession findCurrentSession() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from SaAcademicSession s where " +
                "s.ongoing = :ongoing " +
                "and s.metadata.state = :state");
        query.setBoolean("ongoing", true);
        query.setInteger("state", SaMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (SaAcademicSession) query.uniqueResult();
    }

    @Override
    public SaAcademicSession findNextSession(SaAcademicSession current) {
        return null;
    }

    @Override
    public SaAcademicSession findPreviousSession() {
        return null;
    }

    @Override
    public List<SaAcademicSession> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from SaAcademicSession s where " +
                "(upper(s.code) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", QueryUtil.wildCard(filter));
        query.setInteger("state", SaMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<SaAcademicSession>) query.list();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from SaAcademicSession s where " +
                "(upper(s.code) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", QueryUtil.wildCard(filter));
        query.setInteger("state", SaMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isCodeExists(String code) {
        return false;
    }
}
