package my.edu.umk.pams.account.billing.dao;

import my.edu.umk.pams.account.billing.model.AcCreditNote;
import my.edu.umk.pams.account.billing.model.AcDebitNote;
import my.edu.umk.pams.account.billing.model.AcDebitNoteImpl;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

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

}
