package my.edu.umk.pams.account.billing.dao;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcFeeSchedule;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceImpl;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcInvoiceItemImpl;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.util.DaoQuery;

import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import static my.edu.umk.pams.account.core.AcMetaState.ACTIVE;

/**
 * @author PAMS
 */
@Repository("acInvoiceDao")
public class AcInvoiceDaoImpl extends GenericDaoSupport<Long, AcInvoice> implements AcInvoiceDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcInvoiceDaoImpl.class);

    public AcInvoiceDaoImpl() {
        super(AcInvoiceImpl.class);
    }

    @Override
    public AcInvoice findByReferenceNo(String referenceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcInvoice s where s.referenceNo = :referenceNo and  " +
                " s.metadata.state = :state");
        query.setString("referenceNo", referenceNo);
        query.setCacheable(true);
        query.setInteger("state", ACTIVE.ordinal());
        return (AcInvoice) query.uniqueResult();
    }

    @Override
    public AcInvoiceItem findItemById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (AcInvoiceItem) session.get(AcInvoiceItemImpl.class, id);
    }

    @Override
    public List<AcInvoice> findBySourceNo(String sourceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcInvoice s where " +
                "upper(s.sourceNo) = upper(:sourceNo) " +
                "and s.metadata.state = :state");
        query.setString("sourceNo", sourceNo);
        query.setCacheable(true);
        query.setInteger("state", ACTIVE.ordinal());
        return (List<AcInvoice>) query.list();
    }

    @Override
    public List<AcInvoice> findByFlowState(AcFlowState acFlowState) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcInvoice s where " +
                "s.metadata.state = :state " +
                "and s.flowdata.state = :flowState");
        query.setCacheable(true);
        query.setInteger("state", ACTIVE.ordinal());
        query.setInteger("flowState", acFlowState.ordinal());
        return (List<AcInvoice>) query.list();
    }

    @Override
    public List<AcInvoice> findByFlowStates(AcFlowState... flowStates) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcInvoice s where " +
                "s.metadata.state = :state " +
                "and s.flowdata.state in (:flowStates)");
        query.setCacheable(true);
        query.setInteger("state", ACTIVE.ordinal());
        query.setParameterList("flowStates", flowStates);
        return (List<AcInvoice>) query.list();
    }

    @Override
    public List<AcInvoice> findManyInvoiceBySourceNo(String sourceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcInvoice s where " +
                "(upper(s.sourceNo) like upper(:sourceNo)) " +
                "and s.metadata.state = :state ");
        query.setString("sourceNo", WILDCARD + sourceNo + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcInvoice>) query.list();
    }

    @Override
    public List<AcInvoice> find(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from AcInvoice i where " +
                "i.account = :account " +
                "and i.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcInvoice>) query.list();
    }

    @Override
    public List<AcInvoice> find(boolean paid, AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from AcInvoice i where " +
                "i.account = :account " +
                "and i.paid = :paid " +
                "and i.metadata.state = :state ");
        query.setEntity("account", account);
        query.setBoolean("paid", paid);
        query.setInteger("state", ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcInvoice>) query.list();
    }

    @Override
    public List<AcInvoice> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from AcInvoice i where " +
                // todo(uda): filter
                "i.metadata.state = :state ");
        query.setInteger("state", ACTIVE.ordinal());
        query.setCacheable(true);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<AcInvoice>) query.list();
    }

    public List<AcInvoice> find(String term, Integer offset, Integer limit, List<String> columns) {
    	Session session = sessionFactory.getCurrentSession();
    	
    	String sql = "select i from AcInvoice i ";
    	sql += DaoQuery.buildFilteringSql(columns, "like");
    	sql += "and i.metadata.state = :state";
    	
        Query query = session.createQuery(sql);
        
        DaoQuery.setFilteringTerm(query, columns, "%" + term + "%");
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        
        return (List<AcInvoice>) query.list();
    }

    @Override
    public List<AcInvoice> find(AcAccount account, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from AcInvoice i where " +
                "i.account = :account " +
                "and i.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<AcInvoice>) query.list();
    }

    @Override
    public List<AcInvoice> find(boolean paid, AcAccount account, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from AcInvoice i where " +
                "i.account = :account " +
                "and i.paid = :paid " +
                "and i.metadata.state = :state " +
                "and i.flowdata.state = :flowState ");
        query.setEntity("account", account);
        query.setBoolean("paid", paid);
        query.setInteger("state", ACTIVE.ordinal());
        query.setInteger("flowState", AcFlowState.COMPLETED.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<AcInvoice>) query.list();
    }

    @Override
    public List<AcInvoiceItem> findItems(AcInvoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ii from AcInvoiceItem ii where " +
                "ii.invoice = :invoice " +
                "and ii.metadata.state = :state ");
        query.setEntity("invoice", invoice);
        query.setInteger("state", ACTIVE.ordinal());
        return (List<AcInvoiceItem>) query.list();
    }

    @Override
    public List<AcInvoiceItem> findItems(AcInvoice invoice, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ii from AcInvoiceItem ii where " +
                "ii.invoice = :invoice " +
                "and ii.metadata.state = :state ");
        query.setEntity("invoice", invoice);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<AcInvoiceItem>) query.list();
    }

    @Override
    public List<AcInvoiceItem> findSortedItems(AcInvoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ii from AcInvoiceItem ii where " +
                "ii.invoice = :invoice " +
                "and ii.metadata.state = :state " +
                "order by ii.chargeCode.priority asc");
        query.setEntity("invoice", invoice);
        query.setInteger("state", ACTIVE.ordinal());
        return (List<AcInvoiceItem>) query.list();
    }

//    @Override
//    public List<AcInvoiceTransaction> findTransactions(AcInvoice invoice) {
//        Session session = sessionFactory.getCurrentSession();
//        Query query = session.createQuery("select tx from AcInvoiceTransaction tx where " +
//                "tx.invoice = :invoice " +
//                "and tx.metadata.state = :state ");
//        query.setEntity("invoice", invoice);
//        query.setInteger("state", ACTIVE.ordinal());
//        return (List<AcInvoiceTransaction>) query.list();
//    }
//
//    @Override
//    public List<AcInvoiceTransaction> findTransactions(AcInvoice invoice, Integer offset, Integer limit) {
//        Session session = sessionFactory.getCurrentSession();
//        Query query = session.createQuery("select tx.invoice from AcInvoiceTransaction tx where " +
//                "tx.invoice = :invoice " +
//                "and tx.metadata.state = :state ");
//        query.setEntity("invoice", invoice);
//        query.setInteger("state", ACTIVE.ordinal());
//        query.setFirstResult(offset);
//        query.setMaxResults(limit);
//        return (List<AcInvoiceTransaction>) query.list();
//    }

    @Override
    public Integer count(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(i) from AcInvoice i where " +
                "i.account=:account " +
                "and i.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countItem(AcInvoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(ii) from AcInvoiceItem ii where " +
                "ii.invoice=:invoice " +
                "and ii.metadata.state = :state ");
        query.setEntity("invoice", invoice);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

//    @Override
//    public Integer countTransaction(AcInvoice invoice) {
//        Session session = sessionFactory.getCurrentSession();
//        Query query = session.createQuery("select count(tx) from AcInvoiceTransaction tx where " +
//                "tx.invoice=:invoice " +
//                "and tx.metadata.state = :state ");
//        query.setEntity("invoice", invoice);
//        query.setInteger("state", ACTIVE.ordinal());
//        return ((Long) query.uniqueResult()).intValue();
//    }

    @Override
    public Integer countPaid(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(i) from AcInvoice i where " +
                "i.account = :account " +
                "and i.paid = true " +
                "and i.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countUnpaid(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(i) from AcInvoice i where " +
                "i.account = :account " +
                "and i.paid = false " +
                "and i.metadata.state = :state " +
                "and i.flowdata.state = :flowState ");
        query.setEntity("account", account);
        query.setInteger("state", ACTIVE.ordinal());
        query.setInteger("flowState", AcFlowState.COMPLETED.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isOverdue(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from AcInvoice a " +
                "where a.account = :account " +
                "and a.paid = false " +
                "and a.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", ACTIVE.ordinal());
        Long count = (Long) query.uniqueResult();
        return count.intValue() > 0; // > 0 = true, <=0  false
    }

    @Override
    public boolean hasInvoice(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from AcInvoice a " +
                "where a.account = :account " +
                "and a.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", ACTIVE.ordinal());
        Long count = (Long) query.uniqueResult();
        return count.intValue() > 0; // > 0 = true, <=0  false
    }

    @Override
    public boolean hasInvoice(boolean paid, AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from AcInvoice a " +
                "where a.account = :account " +
                "and a.paid = :paid " +
                "and a.metadata.state = :state ");
        query.setBoolean("paid", paid);
        query.setEntity("account", account);
        query.setInteger("state", ACTIVE.ordinal());
        Long count = (Long) query.uniqueResult();
        return count.intValue() > 0; // > 0 = true, <=0  false
    }

    // TODO: check if this is working
    @Override
    public boolean hasInvoice(boolean paid, Integer days, AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from AcInvoice a " +
                "where a.account = :account " +
                "and a.paid = :paid " +
                "and a.issuedDate - :now > :days " +
                "and a.metadata.state = :state ");
        query.setBoolean("paid", paid);
        query.setEntity("account", account);
        query.setDate("now", new Date());
        query.setInteger("days", days);
        query.setInteger("state", ACTIVE.ordinal());
        Long count = (Long) query.uniqueResult();
        return count.intValue() > 0; // > 0 = true, <=0  false
    }

    @Override
    public void addCharge(AcInvoice invoice, AcAccountCharge charge, AcUser user) {
        Validate.notNull(invoice, "Invoice should not be null");
        Validate.notNull(charge, "Charge member should not be null");

        Session session = sessionFactory.getCurrentSession();
        charge.setInvoice(invoice);

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(ACTIVE);
        charge.setMetadata(metadata);

        session.save(charge);

    }

    @Override
    public void updateCharge(AcInvoice invoice, AcAccountCharge charge, AcUser user) {
        Validate.notNull(invoice, "Invoice should not be null");
        Validate.notNull(charge, "Charge member should not be null");

        Session session = sessionFactory.getCurrentSession();
        charge.setInvoice(invoice);

        AcMetadata metadata = invoice.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(ACTIVE);
        charge.setMetadata(metadata);

        session.update(charge);
    }

    @Override
    public void removeCharge(AcInvoice invoice, AcAccountCharge charge, AcUser user) {
        Validate.notNull(invoice, "Invoice should not be null");
        Validate.notNull(charge, "Charge member should not be null");

        Session session = sessionFactory.getCurrentSession();
        charge.setInvoice(invoice);

        AcMetadata metadata = invoice.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(AcMetaState.INACTIVE);
        charge.setMetadata(metadata);

        session.update(charge);
    }


    @Override
    public void addItem(AcInvoice invoice, AcInvoiceItem item, AcUser user) {
        Validate.notNull(invoice, "Invoice should not be null");
        Validate.notNull(item, "Item member should not be null");

        Session session = sessionFactory.getCurrentSession();
        item.setInvoice(invoice);

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(ACTIVE);
        item.setMetadata(metadata);

        session.save(item);
        
        invoice.setTotalAmount(sumTotalAmount(invoice, user));
        session.update(invoice);
    }

    @Override
    public void updateItem(AcInvoice invoice, AcInvoiceItem item, AcUser user) {
        Validate.notNull(invoice, "Invoice should not be null");
        Validate.notNull(item, "Item member should not be null");

        Session session = sessionFactory.getCurrentSession();
        item.setInvoice(invoice);

        AcMetadata metadata = invoice.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(ACTIVE);
        item.setMetadata(metadata);

        session.update(item);
    }

    @Override
    public void removeItem(AcInvoice invoice, AcInvoiceItem item, AcUser user) {
        Validate.notNull(invoice, "Invoice should not be null");
        Validate.notNull(item, "Item member should not be null");

        Session session = sessionFactory.getCurrentSession();
        AcMetadata metadata = invoice.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(AcMetaState.INACTIVE);
        item.setMetadata(metadata);

        session.update(item);
    }

    @Override
    public void deleteItem(AcInvoice invoice, AcInvoiceItem item, AcUser user) {
        Validate.notNull(invoice, "Invoice should not be null");
        Validate.notNull(item, "Invoice Item should not be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
    }
    
    @Override
    public BigDecimal sumTotalAmount(AcInvoice invoice, AcUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(a.amount) from AcInvoiceItem a where " +
                "a.invoice = :invoice " +
                "and a.metadata.state = :state ");
        query.setEntity("invoice", invoice);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else return (BigDecimal) result;
    }
//    @Override
//    public void addTransaction(AcInvoice invoice, AcInvoiceTransaction transaction, AcUser user) {
//        Validate.notNull(invoice, "Invoice should not be null");
//        Validate.notNull(transaction, "Transaction member should not be null");
//
//        Session session = sessionFactory.getCurrentSession();
//        transaction.setInvoice(invoice);
//
//        AcMetadata metadata = new AcMetadata();
//        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
//        metadata.setCreatorId(user.getId());
//        metadata.setState(ACTIVE);
//        transaction.setMetadata(metadata);
//
//        session.save(transaction);
//
//    }
//
//    @Override
//    public void updateTransaction(AcInvoice invoice, AcInvoiceTransaction transaction, AcUser user) {
//        Validate.notNull(invoice, "Invoice should not be null");
//        Validate.notNull(transaction, "Transaction member should not be null");
//
//        Session session = sessionFactory.getCurrentSession();
//        transaction.setInvoice(invoice);
//
//        AcMetadata metadata = invoice.getMetadata();
//        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
//        metadata.setModifierId(user.getId());
//        metadata.setState(ACTIVE);
//        transaction.setMetadata(metadata);
//
//        session.update(transaction);
//    }
//
//    @Override
//    public void removeTransaction(AcInvoice invoice, AcInvoiceTransaction transaction, AcUser user) {
//        Validate.notNull(invoice, "Invoice should not be null");
//        Validate.notNull(transaction, "Transaction member should not be null");
//
//        Session session = sessionFactory.getCurrentSession();
//        transaction.setInvoice(invoice);
//
//        AcMetadata metadata = invoice.getMetadata();
//        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
//        metadata.setModifierId(user.getId());
//        metadata.setState(AcMetaState.INACTIVE);
//        transaction.setMetadata(metadata);
//
//        session.update(transaction);
//    }
}
