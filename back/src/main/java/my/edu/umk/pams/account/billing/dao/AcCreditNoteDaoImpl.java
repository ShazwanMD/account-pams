package my.edu.umk.pams.account.billing.dao;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.billing.model.*;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.model.AcUser;
import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static my.edu.umk.pams.account.core.AcMetaState.ACTIVE;

/**
 * @author PAMS
 */
@Repository("acCreditNoteDao")
public class AcCreditNoteDaoImpl extends GenericDaoSupport<Long, AcCreditNote> implements AcCreditNoteDao {

    public AcCreditNoteDaoImpl() {
        super(AcCreditNoteImpl.class);
    }

    @Override
    public AcCreditNote findByReferenceNo(String referenceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcCreditNote s where s.referenceNo = :referenceNo and  " +
                " s.metadata.state = :state");
        query.setString("referenceNo", referenceNo);
        query.setCacheable(true);
        query.setInteger("state", ACTIVE.ordinal());
        return (AcCreditNote) query.uniqueResult();
    }

    @Override
    public AcCreditNoteItem findItemById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (AcCreditNoteItem) session.get(AcCreditNoteItemImpl.class, id);
    }

    @Override
    public List<AcCreditNote> find(AcInvoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from AcCreditNote i where " +
                "i.invoice = :invoice " +
                "and i.metadata.state = :state ");
        query.setEntity("invoice", invoice);
        query.setInteger("state", ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcCreditNote>) query.list();
    }

    @Override
    public List<AcCreditNote> findByFlowState(AcFlowState flowState) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from AcCreditNote i where " +
                "i.flowdata.state = :flowState " +
                "and i.metadata.state = :metaState ");
        query.setInteger("flowState", flowState.ordinal());
        query.setInteger("metaState", ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcCreditNote>) query.list();
    }

    @Override
    public List<AcCreditNote> findByFlowStates(AcFlowState... flowStates) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from AcCreditNote i where " +
                "i.flowdata.state in (:flowStates) " +
                "and i.metadata.state = :metaState ");
        query.setParameterList("flowStates", flowStates);
        query.setInteger("metaState", ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcCreditNote>) query.list();
    }

    @Override
    public List<AcCreditNoteItem> findItems(AcCreditNote creditNote) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ii from AcCreditNoteItem ii where " +
                "ii.creditNote = :creditNote " +
                "and ii.metadata.state = :state ");
        query.setEntity("creditNote", creditNote);
        query.setInteger("state", ACTIVE.ordinal());
        return (List<AcCreditNoteItem>) query.list();
    }

    @Override
    public List<AcCreditNoteItem> findItems(AcCreditNote creditNote, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ii from AcCreditNoteItem ii where " +
                "ii.creditNote = :creditNote " +
                "and ii.metadata.state = :state ");
        query.setEntity("creditNote", creditNote);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<AcCreditNoteItem>) query.list();
    }

    @Override
    public Integer count(AcInvoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(i) from AcCreditNote i where " +
                "i.invoice=:invoice " +
                "and i.metadata.state = :state ");
        query.setEntity("invoice", invoice);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countItem(AcCreditNote creditNote) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(ii) from AcCreditNoteItem ii where " +
                "ii.creditNote=:creditNote " +
                "and ii.metadata.state = :state ");
        query.setEntity("creditNote", creditNote);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean hasCreditNote(AcInvoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from AcCreditNote a " +
                "where a.invoice = :invoice " +
                "and a.metadata.state = :state ");
        query.setEntity("invoice", invoice);
        query.setInteger("state", ACTIVE.ordinal());
        Long count = (Long) query.uniqueResult();
        return count.intValue() > 0; // > 0 = true, <=0  false
    }

    @Override
    public void addItem(AcCreditNote creditNote, AcCreditNoteItem item, AcUser user) {
        Validate.notNull(creditNote, "CreditNote should not be null");
        Validate.notNull(item, "Item member should not be null");

        Session session = sessionFactory.getCurrentSession();
        item.setCreditNote(creditNote);

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(ACTIVE);
        item.setMetadata(metadata);

        session.save(item);
        
        creditNote.setTotalAmount(sumTotalAmount(creditNote, user));
        session.update(creditNote);
    }

    @Override
    public void updateItem(AcCreditNote creditNote, AcCreditNoteItem item, AcUser user) {
        Validate.notNull(creditNote, "CreditNote should not be null");
        Validate.notNull(item, "Item member should not be null");

        Session session = sessionFactory.getCurrentSession();
        item.setCreditNote(creditNote);

        AcMetadata metadata = creditNote.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(ACTIVE);
        item.setMetadata(metadata);

        session.update(item);
        
        creditNote.setTotalAmount(sumTotalAmount(creditNote, user));
        session.update(creditNote);
    }

    @Override
    public void removeItem(AcCreditNote creditNote, AcCreditNoteItem item, AcUser user) {
        Validate.notNull(creditNote, "CreditNote should not be null");
        Validate.notNull(item, "Item member should not be null");

        Session session = sessionFactory.getCurrentSession();
        AcMetadata metadata = creditNote.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(AcMetaState.INACTIVE);
        item.setMetadata(metadata);

        session.update(item);
        
        creditNote.setTotalAmount(sumTotalAmount(creditNote, user));
        session.update(creditNote);
    }

    @Override
    public void deleteItem(AcCreditNote creditNote, AcCreditNoteItem item, AcUser user) {
        Validate.notNull(creditNote, "CreditNote should not be null");
        Validate.notNull(item, "CreditNote Item should not be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
    }
    
    @Override
    public BigDecimal sumTotalAmount(AcCreditNote creditNote, AcUser user) {
    	Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(a.balanceAmount) from AcCreditNoteItem a where " +
                "a.creditNote = :creditNote " +
                "and a.metadata.state = :state ");
        query.setEntity("creditNote", creditNote);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else return (BigDecimal) result;
    }
    
    @Override
    public BigDecimal sumAmount(AcCreditNote creditNote, AcUser user) {
    	Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(a.amount) from AcCreditNoteItem a where " +
                "a.creditNote = :creditNote " +
                "and a.metadata.state = :state ");
        query.setEntity("creditNote", creditNote);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else return (BigDecimal) result;
    }
}
