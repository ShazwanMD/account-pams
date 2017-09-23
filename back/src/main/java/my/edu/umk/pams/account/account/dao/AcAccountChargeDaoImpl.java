package my.edu.umk.pams.account.account.dao;

import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.model.AcActor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import static my.edu.umk.pams.account.core.AcMetaState.ACTIVE;

import java.util.List;

/**
 * @author PAMS
 */
@Repository("acAccountChargeDao")
public class AcAccountChargeDaoImpl extends GenericDaoSupport<Long, AcAccountCharge> implements  AcAccountChargeDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcAccountChargeDaoImpl.class);

    public AcAccountChargeDaoImpl() {
        super(AcAccountChargeImpl.class);
    }

    @Override
    public AcAccountCharge findByReferenceNo(String referenceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sa from AcAccountCharge sa where " +
                "sa.referenceNo = :referenceNo " +
                "and sa.metadata.state = :state");
        query.setString("referenceNo", referenceNo);
        query.setCacheable(true);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (AcAccountCharge) query.uniqueResult();
    }

    @Override
    public AcAccountCharge findBySourceNo(String sourceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sa from AcAccountCharge sa where " +
                "sa.sourceNo = :sourceNo " +
                "and sa.metadata.state = :state");
        query.setString("sourceNo", sourceNo);
        query.setCacheable(true);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (AcAccountCharge) query.uniqueResult();
    }

    @Override
    public AcAccountCharge findByActor(AcActor actor) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sa from AcAccountCharge sa where " +
                "sa.account.actor = :actor " +
                "and sa.metadata.state = :state");
        query.setEntity("actor", actor);
        query.setCacheable(true);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (AcAccountCharge) query.uniqueResult();
    }

    @Override
    public List<AcAccountCharge> find(AcAccountChargeType[] chargeType) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sa from AcAccountCharge sa " +
                "where sa.chargeType in (:chargeType) " +
                "and sa.metadata.state = :state");
        query.setParameterList("chargeType", chargeType);
        query.setCacheable(true);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return query.list();
    }

    @Override
    public List<AcAccountCharge> find(AcAcademicSession academicSession, AcAccountChargeType[] chargeType) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sa from AcAccountCharge sa where " +
                "sa.chargeType in (:chargeType) " +
                "and sa.session = :academicSession " +
                "and sa.metadata.state = :state");
        query.setParameterList("chargeType", chargeType);
        query.setEntity("academicSession", academicSession);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return query.list();
    }

    @Override
    public List<AcAccountCharge> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountCharge s where " +
                "(upper(s.referenceNo) like upper(:filter) " +
                "or upper(s.sourceNo) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcAccountCharge>) query.list();
    }

    @Override
    public List<AcAccountCharge> find(AcActor actor, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountCharge s where " +
                "s.account.actor = :actor " +
                "and s.metadata.state = :state ");
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setEntity("actor", actor);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcAccountCharge>) query.list();
    }

    @Override
    public List<AcAccountCharge> find(AcActor actor, String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountCharge s where " +
                "(upper(s.referenceNo) like upper(:filter) " +
                "or upper(s.sourceNo) like upper(:filter)) " +
                "and s.account.actor = :actor " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setEntity("actor", actor);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcAccountCharge>) query.list();
    }


    @Override
    public List<AcAccountCharge> find(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountCharge s where " +
                "s.account= :account " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcAccountCharge>) query.list();
    }

    @Override
    public List<AcAccountCharge> find(AcAcademicSession academicSession, AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountCharge s where " +
                "s.account= :account " +
                "and s.session = :academicSession " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setEntity("academicSession", academicSession);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcAccountCharge>) query.list();
    }

    @Override
    public List<AcAccountCharge> find(AcAcademicSession academicSession, AcAccount account, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountCharge s where " +
                "s.account= :account " +
                "and s.session = :academicSession " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setEntity("academicSession", academicSession);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcAccountCharge>) query.list();
    }

    @Override
    public List<AcAccountCharge> find(AcAccount account, AcAccountChargeType... chargeTypes) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountCharge s where " +
                "s.account= :account " +
                "and s.chargeType in (:chargeTypes) " +
                "and s.metadata.state = :state ");
        query.setParameterList("chargeTypes", chargeTypes);
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcAccountCharge>) query.list();
    }

    @Override
    public List<AcAccountCharge> find(AcAccount account, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountCharge s where " +
                "s.account= :account " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcAccountCharge>) query.list();
    }
    
    @Override
    public List<AcAccountCharge> find(boolean paid, AcAccount account, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountCharge s where " +
        		"s.account = :account " +
                "and s.paid = :paid " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setBoolean("paid", paid);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<AcAccountCharge>) query.list();
    }

    @Override
    public List<AcAccountCharge> findAttached(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountCharge s where " +
                "s.account= :account " +
                "and s.invoice is not null " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcAccountCharge>) query.list();
    }

    @Override
    public List<AcAccountCharge> findAttached(AcAcademicSession academicSession, AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountCharge s where " +
                "s.account= :account " +
                "and s.session = :academicSession " +
                "and s.invoice is not null " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setEntity("academicSession", academicSession);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcAccountCharge>) query.list();
    }

    @Override
    public List<AcAccountCharge> findDetached(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountCharge s where " +
                "s.account= :account " +
                "and s.invoice is null " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcAccountCharge>) query.list();
    }

    @Override
    public List<AcAccountCharge> findDetached(AcAcademicSession academicSession, AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccountCharge s where " +
                "s.account= :account " +
                "and s.session = :academicSession " +
                "and s.invoice is null " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setEntity("academicSession", academicSession);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcAccountCharge>) query.list();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccountCharge s where " +
                "(upper(s.referenceNo) like upper(:filter) " +
                "or upper(s.sourceNo) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(AcActor actor) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccountCharge s where " +
                "s.account.actor = :actor " +
                "and s.metadata.state = :state ");
        query.setEntity("actor", actor);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccountCharge s where " +
                "s.account = :account " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(AcAcademicSession academicSession, AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccountCharge s where " +
                "s.account = :account " +
                "and s.session = :academicSession " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setEntity("academicSession", academicSession);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countAttached(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccountCharge s where " +
                "s.account = :account " +
                "and s.invoice is not null " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countAttached(AcAcademicSession academicSession, AcAccount account) {
        return null;
    }

    @Override
    public Integer countDetached(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccountCharge s where " +
                "s.account = :account " +
                "and s.invoice is null " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countDetached(AcAcademicSession academicSession, AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccountCharge s where " +
                "s.account = :account " +
                "and s.session = :academicSession " +
                "and s.invoice is null " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setEntity("academicSession", academicSession);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isChargeExists(AcAccount account, String sourceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccountCharge s where " +
                "s.account = :account " +
                "and s.sourceNo = :sourceNo " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setString("sourceNo", sourceNo);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue() > 0;
    }

    @Override
    public boolean isChargeExists(AcAccount account, AcAcademicSession academicSession, AcAccountChargeType chargeType) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccountCharge s where " +
                "s.account = :account " +
                "and s.session = :academicSession " +
                "and s.chargeType = :chargeType " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setEntity("academicSession", academicSession);
        query.setInteger("chargeType", chargeType.ordinal());
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue() > 0;
    }
}
