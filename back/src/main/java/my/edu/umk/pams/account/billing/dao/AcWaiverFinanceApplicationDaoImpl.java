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
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.billing.model.AcDebitNote;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcKnockoff;
import my.edu.umk.pams.account.billing.model.AcKnockoffAccountCharge;
import my.edu.umk.pams.account.billing.model.AcKnockoffAccountChargeImpl;
import my.edu.umk.pams.account.billing.model.AcKnockoffDebitNote;
import my.edu.umk.pams.account.billing.model.AcKnockoffDebitNoteImpl;
import my.edu.umk.pams.account.billing.model.AcKnockoffInvoice;
import my.edu.umk.pams.account.billing.model.AcKnockoffInvoiceImpl;
import my.edu.umk.pams.account.billing.model.AcKnockoffItem;
import my.edu.umk.pams.account.billing.model.AcKnockoffItemImpl;
import my.edu.umk.pams.account.billing.model.AcReceipt;
import my.edu.umk.pams.account.billing.model.AcReceiptInvoice;
import my.edu.umk.pams.account.billing.model.AcReceiptItem;
import my.edu.umk.pams.account.billing.model.AcWaiverAccountCharge;
import my.edu.umk.pams.account.billing.model.AcWaiverAccountChargeImpl;
import my.edu.umk.pams.account.billing.model.AcWaiverDebitNote;
import my.edu.umk.pams.account.billing.model.AcWaiverDebitNoteImpl;
import my.edu.umk.pams.account.billing.model.AcWaiverFinanceApplication;
import my.edu.umk.pams.account.billing.model.AcWaiverFinanceApplicationImpl;
import my.edu.umk.pams.account.billing.model.AcWaiverInvoice;
import my.edu.umk.pams.account.billing.model.AcWaiverInvoiceImpl;
import my.edu.umk.pams.account.billing.model.AcWaiverItem;
import my.edu.umk.pams.account.billing.model.AcWaiverItemImpl;
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
    public AcWaiverItem findItemById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (AcWaiverItem) session.get(AcWaiverItemImpl.class, id);
    }
    
    @Override
    public AcWaiverInvoice findWaiverInvoiceById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (AcWaiverInvoice) session.get(AcWaiverInvoiceImpl.class, id);
    }
    
    @Override
    public AcWaiverAccountCharge findWaiverAccChargeById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (AcWaiverAccountCharge) session.get(AcWaiverAccountChargeImpl.class, id);
    }
    
    @Override
    public AcWaiverDebitNote findWaiverDebitNoteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (AcWaiverDebitNote) session.get(AcWaiverDebitNoteImpl.class, id);
    }
    
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
    public AcWaiverItem findWaiverItemByChargeCode(AcChargeCode chargeCode, AcInvoice invoice, AcWaiverFinanceApplication waiver) {
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery("select ri from AcWaiverItem ri where " +
                "ri.chargeCode = :chargeCode " +
    			"and ri.invoice = :invoice " +
                "and ri.waiverFinanceApplication = :waiver " +
                "and ri.metadata.state = :metaState");
        query.setEntity("chargeCode", chargeCode);
        query.setEntity("invoice", invoice);
        query.setEntity("waiver", waiver);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        return (AcWaiverItem) query.uniqueResult();
    }
	
    @Override
    public AcWaiverItem findWaiverItemByCharge(AcAccountCharge accountCharge, AcWaiverFinanceApplication waiver) {
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery("select ri from AcWaiverItem ri where " +
                "ri.accountCharge = :accountCharge " +
                "and ri.waiverFinanceApplication = :waiver " +
                "and ri.metadata.state = :metaState");
        query.setEntity("accountCharge", accountCharge);
        query.setEntity("waiver", waiver);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        return (AcWaiverItem) query.uniqueResult();
    }
    
    @Override
    public AcWaiverItem findWaiverItemByDebitNote(AcDebitNote debitNote, AcWaiverFinanceApplication waiver) {
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery("select ri from AcWaiverItem ri where " +
                "ri.debitNote = :debitNote " +
                "and ri.waiverFinanceApplication = :waiver " +
                "and ri.metadata.state = :metaState");
        query.setEntity("debitNote", debitNote);
        query.setEntity("waiver", waiver);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        return (AcWaiverItem) query.uniqueResult();
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
    public List<AcWaiverItem> findItems(AcWaiverFinanceApplication waiver) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from AcWaiverItem ri where " +
                "ri.waiverFinanceApplication = :waiver " +
                "and ri.metadata.state = :metaState");
        query.setEntity("waiver", waiver);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcWaiverItem>) query.list();
    }
    
    @Override
    public List<AcWaiverItem> findItems(AcWaiverFinanceApplication waiver, AcInvoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from AcWaiverItem ri where " +
                "ri.waiverFinanceApplication = :waiver " +
        		"and ri.invoice = :invoice " +
                "and ri.metadata.state = :metaState");
        query.setEntity("waiver", waiver);
        query.setEntity("invoice", invoice);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcWaiverItem>) query.list();
    }
    
    @Override
    public List<AcWaiverInvoice> findWaivers(AcWaiverFinanceApplication waiver) {
    	Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from AcWaiverInvoice ri where " +
                "ri.waiverFinanceApplication = :waiver " +
                "and ri.metadata.state = :metaState");
        query.setEntity("waiver", waiver);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcWaiverInvoice>) query.list();    	
    }
    
    @Override
    public List<AcWaiverAccountCharge> findWaiverAccountCharge(AcWaiverFinanceApplication waiver) {
    	Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from AcWaiverAccountCharge ri where " +
                "ri.waiverFinanceApplication = :waiver " +
                "and ri.metadata.state = :metaState");
        query.setEntity("waiver", waiver);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcWaiverAccountCharge>) query.list();  
    }
    
    @Override
    public List<AcWaiverDebitNote> findWaiverDebitNote(AcWaiverFinanceApplication waiver) {
    	Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from AcWaiverDebitNote ri where " +
                "ri.waiverFinanceApplication = :waiver " +
                "and ri.metadata.state = :metaState");
        query.setEntity("waiver", waiver);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcWaiverDebitNote>) query.list(); 
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
    public void addWaiverAccountCharge(AcWaiverFinanceApplication waiver, AcAccountCharge accountCharge, AcUser user) {
    	LOG.info("waiver id : " + waiver.getId());
        LOG.info("User : " + user.getRealName());

        Validate.notNull(waiver, "waiver cannot be null");
        Validate.notNull(accountCharge, "accountCharge cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        AcWaiverAccountCharge waiverInvc = new AcWaiverAccountChargeImpl();
        waiverInvc.setAccountCharge(accountCharge);
        waiverInvc.setWaiverFinanceApplication(waiver);;

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        waiverInvc.setMetadata(metadata);
        session.saveOrUpdate(waiverInvc);
    }
    
    @Override
    public void addWaiverDebitNote(AcWaiverFinanceApplication waiver, AcDebitNote debitNote, AcUser user) {
    	LOG.info("waiver id : " + waiver.getId());
        LOG.info("User : " + user.getRealName());

        Validate.notNull(waiver, "waiver cannot be null");
        Validate.notNull(debitNote, "debitNote cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        AcWaiverDebitNote waiverInvc = new AcWaiverDebitNoteImpl();
        waiverInvc.setDebitNote(debitNote);
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
    public void updateItem(AcWaiverFinanceApplication waiver, AcWaiverItem waiverItem, AcUser user) {
        Validate.notNull(waiver, "Invoice should not be null");
        Validate.notNull(waiverItem, "Item member should not be null");

        Session session = sessionFactory.getCurrentSession();
        waiverItem.setWaiverFinanceApplication(waiver);;

        AcMetadata metadata = waiver.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(ACTIVE);
        waiverItem.setMetadata(metadata);

        session.update(waiverItem);
    }
    
    @Override
    public void deleteWaiverInvoice(AcWaiverInvoice waiverInvoice, AcUser user) {
        Validate.notNull(waiverInvoice, "waiver Invoice cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(waiverInvoice);
    }
    
    @Override
    public void deleteWaiverAccountCharge(AcWaiverAccountCharge waiverAccCharge, AcUser user) {
    	Validate.notNull(waiverAccCharge, "waiver Acc Charge cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(waiverAccCharge);
    }
    
    @Override
    public void deleteWaiverDebitNote(AcWaiverDebitNote waiverDebitNote, AcUser user) {
    	Validate.notNull(waiverDebitNote, "waiver debit note cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(waiverDebitNote);
    }
    
    @Override
    public void deleteWaiverItem(AcWaiverFinanceApplication waiver, AcWaiverItem item, AcUser user) {
        Validate.notNull(waiver, "waiver cannot be null");
        Validate.notNull(item, "item cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
    }
    
    @Override
    public BigDecimal sumAppliedAmount(AcWaiverFinanceApplication waiver, AcUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(a.appliedAmount) from AcWaiverItem a where " +
                "a.waiverFinanceApplication = :waiver " +
                "and a.metadata.state = :state ");
        query.setEntity("waiver", waiver);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else return (BigDecimal) result;
    }
    
    @Override
    public BigDecimal sumAppliedAmount(AcInvoice invoice, AcWaiverFinanceApplication waiver, AcUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(a.appliedAmount) from AcWaiverItem a where " +
                "a.waiverFinanceApplication = :waiver " +
        		"and a.invoice = :invoice " +
                "and a.metadata.state = :state ");
        query.setEntity("waiver", waiver);
        query.setEntity("invoice", invoice);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else return (BigDecimal) result;
    }
    
    @Override
    public boolean hasWaiver(AcWaiverFinanceApplication waiver, AcInvoice invoice) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from AcWaiverItem a " + "where a.waiverFinanceApplication = :waiver "
				+ "and a.invoice = :invoice "
				+ "and a.metadata.state = :state ");
		query.setEntity("waiver", waiver);
		query.setEntity("invoice", invoice);
		query.setInteger("state", ACTIVE.ordinal());
		Long count = (Long) query.uniqueResult();
		return count.intValue() > 0; // > 0 = true, <=0 false
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
    
    @Override
    public BigDecimal sumAmount(AcInvoice invoice, AcWaiverFinanceApplication waiver, AcUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(a.appliedAmount) from AcWaiverItem a where " +
                "a.invoice = :invoice " +
        		"and a.waiverFinanceApplication = :waiver " +
                "and a.metadata.state = :state ");
        query.setEntity("invoice", invoice);
        query.setEntity("waiver", waiver);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else return (BigDecimal) result;
    }
    
    @Override
    public BigDecimal sumTotalAmount(AcWaiverFinanceApplication waiver, AcAccountCharge accountCharge, AcUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(a.appliedAmount) from AcWaiverItem a where " +
                "a.accountCharge = :accountCharge " +
        		"and a.waiverFinanceApplication = :waiver " +
                "and a.metadata.state = :state ");
        query.setEntity("accountCharge", accountCharge);
        query.setEntity("waiver", waiver);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else return (BigDecimal) result;
    }
    
    @Override
    public BigDecimal sumTotalAmount(AcWaiverFinanceApplication waiver, AcDebitNote debitNote, AcInvoice invoice, AcUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(a.appliedAmount) from AcWaiverItem a where " +
                "a.debitNote = :debitNote " +
        		"and a.waiverFinanceApplication = :waiver " +
                "and a.metadata.state = :state ");
        query.setEntity("debitNote", debitNote);
        query.setEntity("waiver", waiver);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else return (BigDecimal) result;
    }
    
    @Override
    public BigDecimal sumTotalAmount(AcWaiverFinanceApplication waiver, AcDebitNote debitNote, AcUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(a.appliedAmount) from AcWaiverItem a where " +
                "a.debitNote = :debitNote " +
        		"and a.waiverFinanceApplication = :waiver " +
                "and a.metadata.state = :state ");
        query.setEntity("debitNote", debitNote);
        query.setEntity("waiver", waiver);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else return (BigDecimal) result;
    }
    
    @Override
    public boolean hasChargeWaiverItem(AcAccountCharge accountCharge, AcWaiverFinanceApplication waiverFinanceApplication) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from AcWaiverItem a " + "where a.waiverFinanceApplication = :waiverFinanceApplication "
				+ "and a.accountCharge = :accountCharge "
				+ "and a.metadata.state = :state ");
		query.setEntity("waiverFinanceApplication", waiverFinanceApplication);
		query.setEntity("accountCharge", accountCharge);
		query.setInteger("state", ACTIVE.ordinal());
		Long count = (Long) query.uniqueResult();
		return count.intValue() > 0; // > 0 = true, <=0 false
    }  
    
    @Override
    public boolean hasDebitWaiverItem(AcDebitNote debitNote, AcWaiverFinanceApplication waiverFinanceApplication) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from AcWaiverItem a " + "where a.waiverFinanceApplication = :waiverFinanceApplication "
				+ "and a.debitNote = :debitNote "
				+ "and a.metadata.state = :state ");
		query.setEntity("waiverFinanceApplication", waiverFinanceApplication);
		query.setEntity("debitNote", debitNote);
		query.setInteger("state", ACTIVE.ordinal());
		Long count = (Long) query.uniqueResult();
		return count.intValue() > 0; // > 0 = true, <=0 false
    } 

}

