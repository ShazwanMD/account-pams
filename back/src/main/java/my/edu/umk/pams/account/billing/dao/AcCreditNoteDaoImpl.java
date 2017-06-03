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
}
