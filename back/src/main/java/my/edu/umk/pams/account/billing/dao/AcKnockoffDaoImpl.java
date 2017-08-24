package my.edu.umk.pams.account.billing.dao;

import static my.edu.umk.pams.account.core.AcMetaState.ACTIVE;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import my.edu.umk.pams.account.billing.model.AcCreditNote;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcKnockoff;
import my.edu.umk.pams.account.billing.model.AcKnockoffImpl;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.model.AcUser;

@Repository("acKnockoffDao")
public class AcKnockoffDaoImpl extends GenericDaoSupport<Long, AcKnockoff> implements AcKnockoffDao {

	private static final Logger LOG = LoggerFactory.getLogger(AcKnockoffDaoImpl.class);

	public AcKnockoffDaoImpl() {
		super(AcKnockoffImpl.class);
	}

	@Override
	public AcKnockoff findByReferenceNo(String referenceNo) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select s from AcKnockoff s where s.referenceNo = :referenceNo and  " + " s.metadata.state = :state");
		query.setString("referenceNo", referenceNo);
		query.setCacheable(true);
		query.setInteger("state", ACTIVE.ordinal());
		return (AcKnockoff) query.uniqueResult();
	}

	@Override
	public List<AcKnockoff> find(String filter, Integer offset, Integer limit) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select i from AcKnockoff i where " + "i.metadata.state = :state ");
		query.setInteger("state", ACTIVE.ordinal());
		query.setCacheable(true);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return (List<AcKnockoff>) query.list();
	}
	
    @Override
    public List<AcKnockoff> findByFlowState(AcFlowState flowState) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from AcKnockoff i where " +
                "i.flowdata.state = :flowState " +
                "and i.metadata.state = :metaState ");
        query.setInteger("flowState", flowState.ordinal());
        query.setInteger("metaState", ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcKnockoff>) query.list();
    }

    @Override
    public List<AcKnockoff> findByFlowStates(AcFlowState... flowStates) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from AcKnockoff i where " +
                "i.flowdata.state in (:flowStates) " +
                "and i.metadata.state = :metaState ");
        query.setParameterList("flowStates", flowStates);
        query.setInteger("metaState", ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcKnockoff>) query.list();
    }

	@Override
	public boolean hasKnockoff(AcKnockoff knockoff) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from AcKnockoff a " + "where a.knockoff = :knockoff "
				+ "and a.metadata.state = :state ");
		query.setEntity("knockoff", knockoff);
		query.setInteger("state", ACTIVE.ordinal());
		Long count = (Long) query.uniqueResult();
		return count.intValue() > 0; // > 0 = true, <=0 false
	}

//	@Override
//	public void addKnockoff(AcKnockoff knockoff, AcUser user) {
//
//		Validate.notNull(knockoff, "knockoff should not be null");
//
//		Session session = sessionFactory.getCurrentSession();
//
//		AcMetadata metadata = new AcMetadata();
//		metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
//		metadata.setCreatorId(user.getId());
//		metadata.setState(ACTIVE);
//		knockoff.setMetadata(metadata);
//
//		session.save(knockoff);
//
//	}

	@Override
	public void updateKnockoff(AcKnockoff knockoff, AcUser user) {
		Validate.notNull(knockoff, "Knockoff should not be null");

		Session session = sessionFactory.getCurrentSession();

		AcMetadata metadata = knockoff.getMetadata();
		metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		metadata.setModifierId(user.getId());
		metadata.setState(ACTIVE);
		knockoff.setMetadata(metadata);

		session.update(knockoff);
	}

	@Override
	public void removeKnockoff(AcKnockoff knockoff, AcUser user) {
		Validate.notNull(knockoff, "Knockoff should not be null");

		Session session = sessionFactory.getCurrentSession();

		AcMetadata metadata = knockoff.getMetadata();
		metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		metadata.setModifierId(user.getId());
		metadata.setState(AcMetaState.INACTIVE);
		knockoff.setMetadata(metadata);

		session.update(knockoff);
	}
}
