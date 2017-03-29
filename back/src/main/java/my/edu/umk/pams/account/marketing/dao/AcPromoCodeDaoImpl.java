package my.edu.umk.pams.account.marketing.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.marketing.model.AcPromoCode;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeImpl;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeItem;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeType;

@Repository("acPromoCodeDao")
public class AcPromoCodeDaoImpl extends GenericDaoSupport<Long, AcPromoCode> implements AcPromoCodeDao {

	public AcPromoCodeDaoImpl() {
		super(AcPromoCodeImpl.class);
	}

	@Override
	public AcPromoCode findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (AcPromoCode) session.get(AcPromoCodeImpl.class, id);
	}

	@Override
	public AcPromoCode findByCode(String code) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select a from AcPromoCode a where " + 
				"a.code = :code " + 
				"and a.metadata.state = :state");
		query.setString("code", code);
		query.setCacheable(true);
		query.setInteger("state", AcMetaState.ACTIVE.ordinal());
		return (AcPromoCode) query.uniqueResult();
	}
	
	@Override
	public AcPromoCodeItem findBySourceNo(String sourceNo) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select a from AcPromoCodeItem a where " + 
				"a.sourceNo = :sourceNo " + 
				"and a.metadata.state = :state");
		query.setString("sourceNo", sourceNo);
		query.setCacheable(true);
		query.setInteger("state", AcMetaState.ACTIVE.ordinal());
		return (AcPromoCodeItem) query.uniqueResult();
	}

	@Override
	public List<AcPromoCode> find(AcPromoCodeType promoCodeType, Integer offset, Integer limit) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select a from AcPromoCode a where " + 
				"a.promoCodeType = :promoCodeType " + 
				"order by a.code");
		query.setInteger("promoCodeType", promoCodeType.ordinal());
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}

	@Override
	public List<AcPromoCodeItem> findItems(AcPromoCode promoCode) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select a from AcPromoCodeItem a where " + 
				"a.promoCode = :promoCode " + 
				"and a.metadata.state = :metaState");
		query.setEntity("promoCode", promoCode);
		query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
		return (List<AcPromoCodeItem>) query.list();
	}

	@Override
	public List<AcPromoCodeItem> findItems(AcAccount account) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select a from AcPromoCodeItem a where " + 
				"a.account = :account " + 
				"and a.metadata.state = :metaState");
		query.setEntity("account", account);
		query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
		return (List<AcPromoCodeItem>) query.list();
	}
	
	@Override
	public boolean isExpired(Date now) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(a) from AcPromoCode a where " + 
				"a.expiryDate > :now " + 
				"and s.metadata.state = :state ");
		query.setDate("now", now);
		query.setInteger("state", AcMetaState.ACTIVE.ordinal());
		return ((Long) query.uniqueResult()).intValue() > 0;
	}

	@Override
	public void addItem(AcPromoCode promoCode, AcPromoCodeItem detail, AcUser user) {
		Validate.notNull(promoCode, "Batch should not be null");
		Validate.notNull(detail, "Detail member should not be null");

		Session session = sessionFactory.getCurrentSession();
		detail.setPromoCode(promoCode);

		AcMetadata metadata = new AcMetadata();
		metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		metadata.setCreatorId(user.getId());
		metadata.setState(AcMetaState.ACTIVE);
		detail.setMetadata(metadata);
		session.save(detail);

	}

	@Override
	public void updateItem(AcPromoCode promoCode, AcPromoCodeItem detail, AcUser user) {
		Validate.notNull(promoCode, "Batch should not be null");
		Validate.notNull(detail, "Detail member should not be null");

		Session session = sessionFactory.getCurrentSession();
		detail.setPromoCode(promoCode);

		AcMetadata metadata = detail.getMetadata();
		metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		metadata.setModifierId(user.getId());
		metadata.setState(AcMetaState.ACTIVE);
		detail.setMetadata(metadata);
		session.update(detail);
	}

	@Override
	public void deleteItem(AcPromoCode promoCode, AcPromoCodeItem detail, AcUser user) {
		Validate.notNull(promoCode, "Batch should not be null");
		Validate.notNull(detail, "Detail member should not be null");

		Session session = sessionFactory.getCurrentSession();
		session.delete(detail);
	}

}
