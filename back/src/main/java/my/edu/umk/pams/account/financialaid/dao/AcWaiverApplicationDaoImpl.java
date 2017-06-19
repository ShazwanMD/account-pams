package my.edu.umk.pams.account.financialaid.dao;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplicationImpl;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import static my.edu.umk.pams.account.core.AcMetaState.ACTIVE;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PAMS
 */
@Repository("acWaiverApplicationDao")
public class AcWaiverApplicationDaoImpl extends GenericDaoSupport<Long, AcWaiverApplication> implements AcWaiverApplicationDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcWaiverApplicationDaoImpl.class);

    public AcWaiverApplicationDaoImpl() {
        super(AcWaiverApplicationImpl.class);
    }

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    @Override
    public AcWaiverApplication findByReferenceNo(String referenceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcWaiverApplication a where " +
                "a.referenceNo = :referenceNo " +
                "and a.metadata.state = :state");
        query.setString("referenceNo", referenceNo);
        query.setCacheable(true);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (AcWaiverApplication) query.uniqueResult();
    }

    @Override
    public List<AcWaiverApplication> find(AcAcademicSession academicSession, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcWaiverApplication a where " +
                "a.session = :academicSession "+
                "and a.metadata.state = :state");
        query.setEntity("academicSession", academicSession);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (List<AcWaiverApplication>) query.list();
    }

    @Override
    public List<AcWaiverApplication> findByFlowState(AcFlowState acFlowState) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcWaiverApplication a where " +
                "a.metadata.state = :state " +
                "and a.flowdata.state = :flowState");
        query.setCacheable(true);
        query.setInteger("state", ACTIVE.ordinal());
        query.setInteger("flowState", acFlowState.ordinal());
        return (List<AcWaiverApplication>) query.list();
    }

    @Override
    public List<AcWaiverApplication> findByFlowStates(AcFlowState... flowState) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcWaiverApplication a where " +
                "a.metadata.state = :state " +
                "and a.flowdata.state in (:flowStates)");
        query.setCacheable(true);
        query.setInteger("state", ACTIVE.ordinal());
        query.setParameterList("flowStates", flowState);
        return (List<AcWaiverApplication>) query.list();
    }

    // ====================================================================================================
    // HELPER
    // ====================================================================================================


    @Override
    public Integer count(AcAcademicSession academicSession) {
        org.hibernate.Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(a) from AcWaiverApplication a where " +
                "a.session=:academicSession " +
                "and a.metadata.state = :metaState ");
        query.setEntity("academicSession", academicSession);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }


}
