package my.edu.umk.pams.account.billing.dao;

import static my.edu.umk.pams.account.core.AcMetaState.ACTIVE;

import java.sql.Timestamp;

import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.billing.model.AcAdvancePayment;
import my.edu.umk.pams.account.billing.model.AcAdvancePaymentImpl;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceImpl;
import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.model.AcUser;

@Repository("acAdvancePaymentDao")
public class AcAdvancePaymentDaoImpl extends GenericDaoSupport<Long, AcAdvancePayment> implements AcAdvancePaymentDao{


    private static final Logger LOG = LoggerFactory.getLogger(AcAdvancePaymentDaoImpl.class);

    public AcAdvancePaymentDaoImpl() {
        super(AcAdvancePaymentImpl.class);
    }

    @Override
    public AcAdvancePayment findByReferenceNo(String referenceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAdvancePayment s where s.referenceNo = :referenceNo and  " +
                " s.metadata.state = :state");
        query.setString("referenceNo", referenceNo);
        query.setCacheable(true);
        query.setInteger("state", ACTIVE.ordinal());
        return (AcAdvancePayment) query.uniqueResult();
    }
    
    @Override
    public boolean hasAdvancePayment(AcAdvancePayment advancePayment) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from AdvancePayment a " +
                "where a.advancePayment = :advancePayment " +
                "and a.metadata.state = :state ");
        query.setEntity("advancePayment", advancePayment);
        query.setInteger("state", ACTIVE.ordinal());
        Long count = (Long) query.uniqueResult();
        return count.intValue() > 0; // > 0 = true, <=0  false
    }
    
    @Override
    public void addAdvancePayment(AcAdvancePayment advancePayment, AcUser user) {
        Validate.notNull(advancePayment, "Advance Payment should not be null");

        Session session = sessionFactory.getCurrentSession();

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(ACTIVE);
        advancePayment.setMetadata(metadata);

        session.save(advancePayment);

    }

    @Override
    public void updateAdvancePayment(AcAdvancePayment advancePayment, AcUser user) {
    	Validate.notNull(advancePayment, "Advance Payment should not be null");

        Session session = sessionFactory.getCurrentSession();

        AcMetadata metadata = advancePayment.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(ACTIVE);
        advancePayment.setMetadata(metadata);

        session.update(advancePayment);
    }

    @Override
    public void removeAdvancePayment(AcAdvancePayment advancePayment, AcUser user) {
    	Validate.notNull(advancePayment, "Advance Payment should not be null");

        Session session = sessionFactory.getCurrentSession();

        AcMetadata metadata = advancePayment.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(AcMetaState.INACTIVE);
        advancePayment.setMetadata(metadata);

        session.update(advancePayment);
    }
}
