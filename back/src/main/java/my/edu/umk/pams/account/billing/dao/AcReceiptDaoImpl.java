package my.edu.umk.pams.account.billing.dao;

import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.billing.model.*;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.model.AcUser;
import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import static my.edu.umk.pams.account.core.AcMetaState.ACTIVE;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Repository("acReceiptDao")
public class AcReceiptDaoImpl extends GenericDaoSupport<Long, AcReceipt> implements AcReceiptDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcReceiptDaoImpl.class);

    public AcReceiptDaoImpl() {
        super(AcReceiptImpl.class);
    }

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    @Override
    public AcReceiptItem findItemById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (AcReceiptItem) session.get(AcReceiptItemImpl.class, id);
    }

    @Override
    public AcReceipt findByReferenceNo(String referenceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from AcReceipt r where " +
                "r.referenceNo = :referenceNo");
        query.setString("referenceNo", referenceNo);
        query.setCacheable(true);
        return (AcReceipt) query.uniqueResult();
    }

    @Override
    public AcReceipt findBySourceNo(String sourceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from AcReceipt r where " +
                "r.sourceNo = :sourceNo");
        query.setString("sourceNo", sourceNo);
        return (AcReceipt) query.uniqueResult();
    }

    @Override
    public AcReceipt findByReceiptNo(String receiptNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from AcReceipt r where " +
                "r.receiptNo = :receiptNo");
        query.setString("receiptNo", receiptNo);
        return (AcReceipt) query.uniqueResult();
    }
    
    @Override
	public AcReceiptItem findReceiptItemByChargeCode(AcChargeCode chargeCode, AcInvoice invoice) {
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery("select ri from AcReceiptItem ri where " +
                "ri.chargeCode = :chargeCode " +
    			"and ri.invoice = :invoice " +
                "and ri.metadata.state = :metaState");
        query.setEntity("chargeCode", chargeCode);
        query.setEntity("invoice", invoice);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        return (AcReceiptItem) query.uniqueResult();
	}

    @Override
    public List<AcReceipt> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from AcReceipt r where " +
                // todo(uda): add filter
                "r.metadata.state = :state " +
                "order by r.id desc");
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcReceipt>) query.list();
    }

    @Override
    public List<AcReceipt> find(AcReceiptType type, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from AcReceipt r where " +
                "r.receiptType = :type " +
                "and r.metadata.state = :state " +
                "order by r.id desc");
        query.setInteger("type", type.ordinal());
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcReceipt>) query.list();
    }

    @Override
    public List<AcReceipt> find(AcReceiptType type, String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from AcReceipt r where " +
                "(upper(r.referenceNo) like upper(:filter) " +
                "or upper(r.sourceNo) like upper(:filter)) " +
                "and r.receiptType = :type " +
                "and r.metadata.state = :metaState " +
                "order by r.id desc");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("type", type.ordinal());
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcReceipt>) query.list();
    }

    @Override
    public List<AcReceipt> findByFlowState(AcFlowState flowState) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from AcReceipt r where " +
                "and r.flowdata.state = :flowState " +
                "and r.metadata.state = :metaState " +
                "order by r.id desc");
        query.setInteger("flowState", flowState.ordinal());
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcReceipt>) query.list();
    }
    
    @Override
    public List<AcReceipt> findByFlowStates(AcFlowState... flowStates) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcReceipt s where " +
                "s.metadata.state = :state " +
                "and s.flowdata.state in (:flowStates)");
        query.setCacheable(true);
        query.setInteger("state", ACTIVE.ordinal());
        query.setParameterList("flowStates", flowStates);
        return (List<AcReceipt>) query.list();
    }

    @Override
    public List<AcReceiptItem> findItems(AcReceipt receipt) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from AcReceiptItem ri where " +
                "ri.receipt = :receipt " +
                "and ri.metadata.state = :metaState");
        query.setEntity("receipt", receipt);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcReceiptItem>) query.list();
    }
    
    @Override
    public List<AcReceiptItem> findItems(AcReceipt receipt, AcInvoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from AcReceiptItem ri where " +
                "ri.receipt = :receipt " +
        		"and ri.invoice = :invoice " +
                "and ri.metadata.state = :metaState");
        query.setEntity("receipt", receipt);
        query.setEntity("invoice", invoice);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcReceiptItem>) query.list();
    }

    @Override
    public List<AcReceiptItem> findItems(AcReceipt receipt, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from AcReceiptItem ri where " +
                "ri.receipt = :receipt " +
                "and ri.metadata.state = :metaState " +
                "order by ri.id desc");
        query.setEntity("receipt", receipt);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcReceiptItem>) query.list();
    }
    
    @Override
    public List<AcReceiptInvoice> find(AcReceipt receipt) {
    	Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from AcReceiptInvoice ri where " +
                "ri.receipt = :receipt " +
                "and ri.metadata.state = :metaState");
        query.setEntity("receipt", receipt);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcReceiptInvoice>) query.list();
    }
    
    @Override
    public List<AcReceiptAccountCharge> findReceiptAccountCharge(AcReceipt receipt) {
    	Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from AcReceiptAccountCharge ri where " +
                "ri.receipt = :receipt " +
                "and ri.metadata.state = :metaState");
        query.setEntity("receipt", receipt);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcReceiptAccountCharge>) query.list();
    }

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    @Override
    public Integer count(AcReceiptType type) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(r) from AcReceipt r where " +
                "r.receiptType = :type " +
                "and r.metadata.state = :state");
        query.setInteger("type", type.ordinal());
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(AcReceiptType type, String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(r) from AcReceipt r where " +
                "(upper(r.referenceNo) like upper(:filter) " +
                "or upper(r.sourceNo) like upper(:filter))" +
                "and r.receiptType = :type " +
                "and r.metadata.state = :metaState");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("type", type.ordinal());
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countItem(AcReceipt receipt) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(ri) from AcReceiptItem ri where " +
                "ri.receipt = :receipt");
        query.setEntity("receipt", receipt);
        return ((Long) query.uniqueResult()).intValue();
    }

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    @Override
    public void addItem(AcReceipt receipt, AcReceiptItem item, AcUser user) {
        LOG.info("Receipt id : " + receipt.getId());
        LOG.info("User : " + user.getRealName());

        Validate.notNull(receipt, "Receipt cannot be null");
        Validate.notNull(item, "Item cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        item.setReceipt(receipt);

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        item.setMetadata(metadata);
        session.save(item);
    }

    @Override
    public void updateItem(AcReceipt receipt, AcReceiptItem item, AcUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();
        item.setReceipt(receipt);

        AcMetadata metadata = item.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        item.setMetadata(metadata);
        session.update(item);
    }

    @Override
    public void removeItem(AcReceipt receipt, AcReceiptItem item, AcUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();
        item.setReceipt(receipt);

        AcMetadata metadata = item.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(AcMetaState.INACTIVE);
        item.setMetadata(metadata);
        session.update(item);
    }

    @Override
    public void deleteItem(AcReceipt receipt, AcReceiptItem item, AcUser user) {
        Validate.notNull(receipt, "receipt cannot be null");
        Validate.notNull(item, "payment cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
    }
    
    @Override
    public void addReceiptInvoice(AcReceipt receipt, AcInvoice invoice, AcUser user) {
        LOG.info("Receipt id : " + receipt.getId());
        LOG.info("User : " + user.getRealName());

        Validate.notNull(receipt, "Receipt cannot be null");
        Validate.notNull(invoice, "Invoice cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        AcReceiptInvoice receiptItem = new AcReceiptInvoiceImpl();
        receiptItem.setReceipt(receipt);
        receiptItem.setInvoice(invoice);

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        receiptItem.setMetadata(metadata);
        session.saveOrUpdate(receiptItem);
    }
    
    @Override
    public BigDecimal sumAppliedAmount(AcReceipt receipt, AcUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(a.appliedAmount) from AcReceiptItem a where " +
                "a.receipt = :receipt " +
                "and a.metadata.state = :state ");
        query.setEntity("receipt", receipt);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else return (BigDecimal) result;
    }
    
    @Override
    public BigDecimal sumAmount(AcInvoice invoice, AcUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(a.appliedAmount) from AcReceiptItem a where " +
                "a.invoice = :invoice " +
                "and a.metadata.state = :state ");
        query.setEntity("invoice", invoice);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else return (BigDecimal) result;
    }

}
