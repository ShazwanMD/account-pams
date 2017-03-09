package my.edu.umk.pams.account.account.dao;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAcademicSessionImpl;
import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.dao.AcGroupDaoImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author PAMS
 */
@Repository("acAcademicSessionDao")
public class AcAcademicSessionDaoImpl extends GenericDaoSupport<Long, AcAcademicSession> implements AcAcademicSessionDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcGroupDaoImpl.class);

    public AcAcademicSessionDaoImpl() {
        super(AcAcademicSessionImpl.class);
    }

    @Override
    public AcAcademicSession findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAcademicSession s where s.code = :code and " +
                "s.metadata.state = :state");
        query.setString("code", code);
        query.setCacheable(true);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (AcAcademicSession) query.uniqueResult();
    }

    @Override
    public AcAcademicSession findCurrentSession() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAcademicSession s where " +
                "s.current = :current " +
                "and s.metadata.state = :state");
        query.setBoolean("current", true);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (AcAcademicSession) query.uniqueResult();
    }

    @Override
    public List<AcAcademicSession> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAcademicSession s where " +
                "(upper(s.code) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcAcademicSession>) query.list();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAcademicSession s where " +
                "(upper(s.code) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    // todo:
    @Override
    public boolean isCodeExists(String code) {
        return false;
    }
}
