package my.edu.umk.pams.account.account.dao;

import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcUser;

import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author PAMS
 */
@Repository("acAccountWaiverDao")
public class AcAccountWaiverDaoImpl extends GenericDaoSupport<Long, AcAccountWaiver> implements  AcAccountWaiverDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcAccountWaiverDaoImpl.class);

    public AcAccountWaiverDaoImpl() {
        super(AcAccountWaiverImpl.class);
    }

    @Override
    public AcAccountWaiver findByReferenceNo(String refNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sa from AcAccountWaiver sa where " +
                "sa.referenceNo = :refNo " +
                "and sa.metadata.state = :state");
        query.setString("refNo", refNo);
        query.setCacheable(true);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (AcAccountWaiver) query.uniqueResult();
    }

    @Override
    public AcAccountWaiver findBySourceNo(String sourceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sa from AcAccountWaiver sa where " +
                "sa.sourceNo = :sourceNo " +
                "and sa.metadata.state = :state");
        query.setString("sourceNo", sourceNo);
        query.setCacheable(true);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (AcAccountWaiver) query.uniqueResult();
    }

    @Override
    public List<AcAccountWaiver> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountWaiver s where " +
                "(upper(s.referenceNo) like upper(:filter) " +
                "or upper(s.sourceNo) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcAccountWaiver>) query.list();
    }

    @Override
    public List<AcAccountWaiver> find(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountWaiver s where " +
                "s.account= :account " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcAccountWaiver>) query.list();
    }

    @Override
    public List<AcAccountWaiver> find(AcAcademicSession academicSession, AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountWaiver s where " +
                "s.account= :account " +
                "and s.session = :academicSession " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setEntity("academicSession", academicSession);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcAccountWaiver>) query.list();
    }

    @Override
    public List<AcAccountWaiver> find(AcAcademicSession academicSession, AcAccount account, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountWaiver s where " +
                "s.account= :account " +
                "and s.session = :academicSession " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setEntity("academicSession", academicSession);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcAccountWaiver>) query.list();
    }

    @Override
    public List<AcAccountWaiver> find(AcAccount account, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountWaiver s where " +
                "s.account= :account " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcAccountWaiver>) query.list();
    }

    @Override
    public Integer count(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccountWaiver s where " +
                "s.account = :account " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(AcAcademicSession academicSession, AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccountWaiver s where " +
                "s.account = :account " +
                "and s.session = :academicSession " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setEntity("academicSession", academicSession);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isWaiverExists(AcAccount account, AcAcademicSession academicSession) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccountWaiver s where " +
                "s.account = :account " +
                "and s.session = :academicSession " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setEntity("academicSession", academicSession);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue() > 0;
    }

    @Override
    public boolean isWaiverExists(AcAccount account, String sourceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccountWaiver s where " +
                "s.account = :account " +
                "and s.sourceNo = :sourceNo " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setString("sourceNo", sourceNo);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue() > 0;
    }
    
    @Override
    public void update(AcAccountWaiver waiver, AcUser user) {
        Validate.notNull(waiver, "waiver should not be null");

        Session session = sessionFactory.getCurrentSession();

        AcMetadata metadata = waiver.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        waiver.setMetadata(metadata);
        session.update(waiver);
    }
}
