package my.edu.umk.pams.account.account.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountSTL;
import my.edu.umk.pams.account.account.model.AcAccountSTLImpl;
import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.GenericDaoSupport;

@Deprecated
@Repository("acAccountSTLDao")
public class AcAccountSTLDaoImpl extends GenericDaoSupport<Long, AcAccountSTL> implements  AcAccountSTLDao {


    private static final Logger LOG = LoggerFactory.getLogger(AcAccountSTLDaoImpl.class);

    public AcAccountSTLDaoImpl() {
        super(AcAccountSTLImpl.class);
    }

    @Override
    public AcAccountSTL findByReferenceNo(String refNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sa from AcAccountSTL sa where " +
                "sa.referenceNo = :refNo " +
                "and sa.metadata.state = :state");
        query.setString("refNo", refNo);
        query.setCacheable(true);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (AcAccountSTL) query.uniqueResult();
    }

    @Override
    public AcAccountSTL findBySourceNo(String sourceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sa from AcAccountSTL sa where " +
                "sa.sourceNo = :sourceNo " +
                "and sa.metadata.state = :state");
        query.setString("sourceNo", sourceNo);
        query.setCacheable(true);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (AcAccountSTL) query.uniqueResult();
    }

    @Override
    public List<AcAccountSTL> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountSTL s where " +
                "(upper(s.referenceNo) like upper(:filter) " +
                "or upper(s.sourceNo) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcAccountSTL>) query.list();
    }

    @Override
    public List<AcAccountSTL> find(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountSTL s where " +
                "s.account= :account " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcAccountSTL>) query.list();
    }

    @Override
    public List<AcAccountSTL> find(AcAcademicSession academicSession, AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountSTL s where " +
                "s.account= :account " +
                "and s.session = :academicSession " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setEntity("academicSession", academicSession);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcAccountSTL>) query.list();
    }

    @Override
    public List<AcAccountSTL> find(AcAcademicSession academicSession, AcAccount account, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountSTL s where " +
                "s.account= :account " +
                "and s.session = :academicSession " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setEntity("academicSession", academicSession);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcAccountSTL>) query.list();
    }

    @Override
    public List<AcAccountSTL> find(AcAccount account, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountSTL s where " +
                "s.account= :account " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcAccountSTL>) query.list();
    }

    @Override
    public Integer count(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccountSTL s where " +
                "s.account = :account " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(AcAcademicSession academicSession, AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccountSTL s where " +
                "s.account = :account " +
                "and s.session = :academicSession " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setEntity("academicSession", academicSession);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isShortTermlLoanExists(AcAccount account, AcAcademicSession academicSession) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccountSTL s where " +
                "s.account = :account " +
                "and s.session = :academicSession " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setEntity("academicSession", academicSession);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue() > 0;
    }

    @Override
    public boolean isShortTermlLoanExists(AcAccount account, String sourceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccountSTL s where " +
                "s.account = :account " +
                "and s.sourceNo = :sourceNo " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setString("sourceNo", sourceNo);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue() > 0;
    }
}
