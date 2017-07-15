package my.edu.umk.pams.account.billing.dao;

import static my.edu.umk.pams.account.core.AcMetaState.ACTIVE;

import java.sql.Timestamp;

import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import my.edu.umk.pams.account.billing.model.AcRefundPayment;
import my.edu.umk.pams.account.billing.model.AcRefundPaymentImpl;
import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.model.AcUser;

public class AcRefundPaymentDaoImpl extends GenericDaoSupport<Long, AcRefundPayment> implements AcRefundPaymentDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcRefundPaymentDaoImpl.class);

    public AcRefundPaymentDaoImpl() {
        super(AcRefundPaymentImpl.class);
    }

    @Override
    public AcRefundPayment findByReferenceNo(String referenceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcRefundPayment s where s.referenceNo = :referenceNo and  " +
                " s.metadata.state = :state");
        query.setString("referenceNo", referenceNo);
        query.setCacheable(true);
        query.setInteger("state", ACTIVE.ordinal());
        return (AcRefundPayment) query.uniqueResult();
    }
    
    @Override
    public boolean hasRefundPayment(AcRefundPayment refundPayment) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from AcRefundPayment a " +
                "where a.refundPayment = :refundPayment " +
                "and a.metadata.state = :state ");
        query.setEntity("refundPayment", refundPayment);
        query.setInteger("state", ACTIVE.ordinal());
        Long count = (Long) query.uniqueResult();
        return count.intValue() > 0; // > 0 = true, <=0  false
    }
    
    @Override
    public void addRefundPayment(AcRefundPayment refundPayment, AcUser user) {
        Validate.notNull(refundPayment, "refundPayment should not be null");

        Session session = sessionFactory.getCurrentSession();

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(ACTIVE);
        refundPayment.setMetadata(metadata);

        session.save(refundPayment);

    }

    @Override
    public void updateRefundPayment(AcRefundPayment refundPayment, AcUser user) {
    	Validate.notNull(refundPayment, "refundPayment should not be null");

        Session session = sessionFactory.getCurrentSession();

        AcMetadata metadata = refundPayment.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(ACTIVE);
        refundPayment.setMetadata(metadata);

        session.update(refundPayment);
    }

    @Override
    public void removeRefundPayment(AcRefundPayment refundPayment, AcUser user) {
    	Validate.notNull(refundPayment, "refundPayment should not be null");

        Session session = sessionFactory.getCurrentSession();

        AcMetadata metadata = refundPayment.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(AcMetaState.INACTIVE);
        refundPayment.setMetadata(metadata);

        session.update(refundPayment);
    }
}
