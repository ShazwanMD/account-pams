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

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static my.edu.umk.pams.account.core.AcMetaState.ACTIVE;

/**
 * @author PAMS
 */
@Repository("acDebitNoteDao")
public class AcDebitNoteDaoImpl extends GenericDaoSupport<Long, AcDebitNote> implements AcDebitNoteDao {

    public AcDebitNoteDaoImpl() {
        super(AcDebitNoteImpl.class);
    }

    @Override
    public AcDebitNote findByReferenceNo(String referenceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcDebitNote s where s.referenceNo = :referenceNo and  " +
                " s.metadata.state = :state");
        query.setString("referenceNo", referenceNo);
        query.setCacheable(true);
        query.setInteger("state", ACTIVE.ordinal());
        return (AcDebitNote) query.uniqueResult();
    }

    @Override
    public AcDebitNoteItem findItemById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (AcDebitNoteItem) session.get(AcDebitNoteItemImpl.class, id);
    }

    @Override
    public List<AcDebitNote> find(AcInvoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from AcDebitNote i where " +
                "i.invoice = :invoice " +
                "and i.metadata.state = :state ");
        query.setEntity("invoice", invoice);
        query.setInteger("state", ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcDebitNote>) query.list();
    }

    @Override
    public List<AcDebitNote> findByFlowState(AcFlowState flowState) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from AcDebitNote i where " +
                "i.flowdata.state = :flowState " +
                "and i.metadata.state = :metaState ");
        query.setInteger("flowState", flowState.ordinal());
        query.setInteger("metaState", ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcDebitNote>) query.list();
    }

    @Override
    public List<AcDebitNote> findByFlowStates(AcFlowState... flowStates) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from AcDebitNote i where " +
                "i.flowdata.state in (:flowStates) " +
                "and i.metadata.state = :metaState ");
        query.setParameterList("flowStates", flowStates);
        query.setInteger("metaState", ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcDebitNote>) query.list();
    }

    @Override
    public List<AcDebitNoteItem> findItems(AcDebitNote debitNote) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ii from AcDebitNoteItem ii where " +
                "ii.debitNote = :debitNote " +
                "and ii.metadata.state = :state ");
        query.setEntity("debitNote", debitNote);
        query.setInteger("state", ACTIVE.ordinal());
        return (List<AcDebitNoteItem>) query.list();
    }

    @Override
    public List<AcDebitNoteItem> findItems(AcDebitNote debitNote, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ii from AcDebitNoteItem ii where " +
                "ii.debitNote = :debitNote " +
                "and ii.metadata.state = :state ");
        query.setEntity("debitNote", debitNote);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<AcDebitNoteItem>) query.list();
    }

    @Override
    public Integer count(AcInvoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(i) from AcDebitNote i where " +
                "i.invoice=:invoice " +
                "and i.metadata.state = :state ");
        query.setEntity("invoice", invoice);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countItem(AcDebitNote debitNote) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(ii) from AcDebitNoteItem ii where " +
                "ii.debitNote=:debitNote " +
                "and ii.metadata.state = :state ");
        query.setEntity("debitNote", debitNote);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean hasDebitNote(AcInvoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from AcDebitNote a " +
                "where a.invoice = :invoice " +
                "and a.metadata.state = :state ");
        query.setEntity("invoice", invoice);
        query.setInteger("state", ACTIVE.ordinal());
        Long count = (Long) query.uniqueResult();
        return count.intValue() > 0; // > 0 = true, <=0  false
    }

    @Override
    public void addItem(AcDebitNote debitNote, AcDebitNoteItem item, AcUser user) {
        Validate.notNull(debitNote, "DebitNote should not be null");
        Validate.notNull(item, "Item member should not be null");

        Session session = sessionFactory.getCurrentSession();
        item.setDebitNote(debitNote);

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(ACTIVE);
        item.setMetadata(metadata);

        session.save(item);
    }

    @Override
    public void updateItem(AcDebitNote debitNote, AcDebitNoteItem item, AcUser user) {
        Validate.notNull(debitNote, "DebitNote should not be null");
        Validate.notNull(item, "Item member should not be null");

        Session session = sessionFactory.getCurrentSession();
        item.setDebitNote(debitNote);

        AcMetadata metadata = debitNote.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(ACTIVE);
        item.setMetadata(metadata);

        session.update(item);
    }

    @Override
    public void removeItem(AcDebitNote debitNote, AcDebitNoteItem item, AcUser user) {
        Validate.notNull(debitNote, "DebitNote should not be null");
        Validate.notNull(item, "Item member should not be null");

        Session session = sessionFactory.getCurrentSession();
        AcMetadata metadata = debitNote.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(AcMetaState.INACTIVE);
        item.setMetadata(metadata);

        session.update(item);
    }

    @Override
    public void deleteItem(AcDebitNote debitNote, AcDebitNoteItem item, AcUser user) {
        Validate.notNull(debitNote, "DebitNote should not be null");
        Validate.notNull(item, "DebitNote Item should not be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
    }
    
    @Override
    public List<AcDebitNote> find(boolean paid, AcAccount account, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from AcDebitNote i where " +
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
        return (List<AcDebitNote>) query.list();
    }
}
