package my.edu.umk.pams.account.billing.dao;

import static my.edu.umk.pams.account.core.AcMetaState.ACTIVE;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcKnockoff;
import my.edu.umk.pams.account.billing.model.AcKnockoffInvoice;
import my.edu.umk.pams.account.billing.model.AcKnockoffInvoiceImpl;
import my.edu.umk.pams.account.billing.model.AcKnockoffItem;
import my.edu.umk.pams.account.billing.model.AcWaiverFinanceApplication;
import my.edu.umk.pams.account.billing.model.AcWaiverFinanceApplicationImpl;
import my.edu.umk.pams.account.billing.model.AcWaiverInvoice;
import my.edu.umk.pams.account.billing.model.AcWaiverInvoiceImpl;
import my.edu.umk.pams.account.billing.model.AcWaiverItem;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.financialaid.dao.AcWaiverApplicationDao;
import my.edu.umk.pams.account.financialaid.dao.AcWaiverApplicationDaoImpl;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplicationImpl;
import my.edu.umk.pams.account.identity.model.AcUser;

/**
 * @author PAMS
 */
@Repository("acWaiverFinanceApplicationDao")
public class AcWaiverFinanceApplicationDaoImpl extends GenericDaoSupport<Long, AcWaiverFinanceApplication> implements AcWaiverFinanceApplicationDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcWaiverFinanceApplicationDaoImpl.class);

    public AcWaiverFinanceApplicationDaoImpl() {
        super(AcWaiverFinanceApplicationImpl.class);
    }

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    @Override
    public AcWaiverFinanceApplication findByReferenceNo(String referenceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcWaiverFinanceApplication a where " +
                "a.referenceNo = :referenceNo " +
                "and a.metadata.state = :state");
        query.setString("referenceNo", referenceNo);
        query.setCacheable(true);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (AcWaiverFinanceApplication) query.uniqueResult();
    }

    @Override
    public List<AcWaiverFinanceApplication> find(AcAcademicSession academicSession, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcWaiverFinanceApplication a where " +
                "a.session = :academicSession "+
                "and a.metadata.state = :state");
        query.setEntity("academicSession", academicSession);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (List<AcWaiverFinanceApplication>) query.list();
    }

    @Override
    public List<AcWaiverFinanceApplication> findByFlowState(AcFlowState acFlowState) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcWaiverFinanceApplication a where " +
                "a.metadata.state = :state " +
                "and a.flowdata.state = :flowState");
        query.setCacheable(true);
        query.setInteger("state", ACTIVE.ordinal());
        query.setInteger("flowState", acFlowState.ordinal());
        return (List<AcWaiverFinanceApplication>) query.list();
    }

    @Override
    public List<AcWaiverFinanceApplication> findByFlowStates(AcFlowState... flowState) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcWaiverFinanceApplication a where " +
                "a.metadata.state = :state " +
                "and a.flowdata.state in (:flowStates)");
        query.setCacheable(true);
        query.setInteger("state", ACTIVE.ordinal());
        query.setParameterList("flowStates", flowState);
        return (List<AcWaiverFinanceApplication>) query.list();
    }
    
    @Override
    public void addWaiverInvoice(AcWaiverFinanceApplication waiver, AcInvoice invoice, AcUser user) {
        LOG.info("waiver id : " + waiver.getId());
        LOG.info("User : " + user.getRealName());

        Validate.notNull(waiver, "waiver cannot be null");
        Validate.notNull(invoice, "Invoice cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        AcWaiverInvoice waiverInvc = new AcWaiverInvoiceImpl();
        waiverInvc.setInvoice(invoice);
        waiverInvc.setWaiverFinanceApplication(waiver);;

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        waiverInvc.setMetadata(metadata);
        session.saveOrUpdate(waiverInvc);
    }
    
    @Override
    public void addWaiverItem(AcWaiverFinanceApplication waiver, AcWaiverItem item, AcUser user) {
        LOG.info("waiver id : " + waiver.getId());
        LOG.info("User : " + user.getRealName());

        Validate.notNull(waiver, "waiver cannot be null");
        Validate.notNull(item, "Item cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        item.setWaiverFinanceApplication(waiver);

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        item.setMetadata(metadata);
        session.save(item);
    }
    
    @Override
    public BigDecimal sumAppliedAmount(AcWaiverFinanceApplication waiver, AcUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(a.appliedAmount) from AcWaiverItem a where " +
                "a.waiver = :waiver " +
                "and a.metadata.state = :state ");
        query.setEntity("waiver", waiver);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else return (BigDecimal) result;
    }

    // ====================================================================================================
    // HELPER
    // ====================================================================================================


    @Override
    public Integer count(AcAcademicSession academicSession) {
        org.hibernate.Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(a) from AcWaiverFinanceApplication a where " +
                "a.session=:academicSession " +
                "and a.metadata.state = :metaState ");
        query.setEntity("academicSession", academicSession);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }


}

