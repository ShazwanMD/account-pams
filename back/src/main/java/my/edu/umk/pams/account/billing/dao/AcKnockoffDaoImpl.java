package my.edu.umk.pams.account.billing.dao;

import static my.edu.umk.pams.account.core.AcMetaState.ACTIVE;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.billing.model.AcCreditNote;
import my.edu.umk.pams.account.billing.model.AcDebitNote;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcKnockoff;
import my.edu.umk.pams.account.billing.model.AcKnockoffAccountCharge;
import my.edu.umk.pams.account.billing.model.AcKnockoffAccountChargeImpl;
import my.edu.umk.pams.account.billing.model.AcKnockoffDebitNote;
import my.edu.umk.pams.account.billing.model.AcKnockoffDebitNoteImpl;
import my.edu.umk.pams.account.billing.model.AcKnockoffImpl;
import my.edu.umk.pams.account.billing.model.AcKnockoffInvoice;
import my.edu.umk.pams.account.billing.model.AcKnockoffInvoiceImpl;
import my.edu.umk.pams.account.billing.model.AcKnockoffItem;
import my.edu.umk.pams.account.billing.model.AcKnockoffItemImpl;
import my.edu.umk.pams.account.billing.model.AcReceipt;
import my.edu.umk.pams.account.billing.model.AcReceiptInvoice;
import my.edu.umk.pams.account.billing.model.AcReceiptInvoiceImpl;
import my.edu.umk.pams.account.billing.model.AcReceiptItem;
import my.edu.umk.pams.account.billing.model.AcReceiptItemImpl;
import my.edu.umk.pams.account.billing.model.AcWaiverFinanceApplication;
import my.edu.umk.pams.account.billing.model.AcWaiverItem;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.identity.model.AcUser;

@Repository("acKnockoffDao")
public class AcKnockoffDaoImpl extends GenericDaoSupport<Long, AcKnockoff> implements AcKnockoffDao {

	private static final Logger LOG = LoggerFactory.getLogger(AcKnockoffDaoImpl.class);

	public AcKnockoffDaoImpl() {
		super(AcKnockoffImpl.class);
	}
	
    @Override
    public AcKnockoffItem findItemById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (AcKnockoffItem) session.get(AcKnockoffItemImpl.class, id);
    }
    
    @Override
    public AcKnockoffInvoice findItemInvoiceById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (AcKnockoffInvoice) session.get(AcKnockoffInvoiceImpl.class, id);
    }
    
    @Override
    public AcKnockoffAccountCharge findItemAccChargeById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (AcKnockoffAccountCharge) session.get(AcKnockoffAccountChargeImpl.class, id);
    }
    
    @Override
    public AcKnockoffDebitNote findItemDebitNoteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (AcKnockoffDebitNote) session.get(AcKnockoffDebitNoteImpl.class, id);
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
	public AcKnockoffItem findKnockoffItemByChargeCode(AcChargeCode chargeCode, AcInvoice invoice, AcKnockoff knockoff) {
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery("select ri from AcKnockoffItem ri where " +
                "ri.chargeCode = :chargeCode " +
    			"and ri.invoice = :invoice " +
                "and ri.knockoff = :knockoff " +
                "and ri.metadata.state = :metaState");
        query.setEntity("chargeCode", chargeCode);
        query.setEntity("invoice", invoice);
        query.setEntity("knockoff", knockoff);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        return (AcKnockoffItem) query.uniqueResult();
	}
	
	@Override
	public AcKnockoffItem findKnockoffItemByChargeCode(AcChargeCode chargeCode, AcInvoice invoice, AcDebitNote debitNote, AcKnockoff knockoff) {
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery("select ri from AcKnockoffItem ri where " +
                "ri.chargeCode = :chargeCode " +
    			"and ri.invoice = :invoice " +
    			"and ri.debitNote = :debitNote " +
                "and ri.knockoff = :knockoff " +
                "and ri.metadata.state = :metaState");
        query.setEntity("chargeCode", chargeCode);
        query.setEntity("invoice", invoice);
        query.setEntity("debitNote", debitNote);
        query.setEntity("knockoff", knockoff);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        return (AcKnockoffItem) query.uniqueResult();
	}
	
	@Override
	public AcKnockoffItem findKnockoffItemByChare(AcAccountCharge charge, AcKnockoff knockoff) {
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery("select ri from AcKnockoffItem ri where " +
                "ri.accountCharge = :charge " +
                "and ri.knockoff = :knockoff " +
                "and ri.metadata.state = :metaState");
        query.setEntity("charge", charge);
        query.setEntity("knockoff", knockoff);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        return (AcKnockoffItem) query.uniqueResult();
	}
	
	@Override
	public AcKnockoffItem findKnockoffItemByDebitNote(AcDebitNote debitNote, AcKnockoff knockoff) {
		Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery("select ri from AcKnockoffItem ri where " +
                "ri.debitNote = :debitNote " +
                "and ri.knockoff = :knockoff " +
                "and ri.metadata.state = :metaState");
        query.setEntity("debitNote", debitNote);
        query.setEntity("knockoff", knockoff);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        return (AcKnockoffItem) query.uniqueResult();
	}
	
	@Override
	public List<AcKnockoffItem> findInvoiceKnockoffItem(AcInvoice invoice, AcKnockoff knockoff) {
		Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery("select ri from AcKnockoffItem ri where " +
                "ri.invoice = :invoice " +
                "and ri.knockoff = :knockoff " +
                "and ri.metadata.state = :metaState");
        query.setEntity("invoice", invoice);
        query.setEntity("knockoff", knockoff);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcKnockoffItem>) query.list();		
	}
	
	@Override
	public List<AcKnockoffItem> findDebitKnockoffItem(AcDebitNote debitNote, AcKnockoff knockoff) {
		Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery("select ri from AcKnockoffItem ri where " +
                "ri.debitNote = :debitNote " +
                "and ri.knockoff = :knockoff " +
                "and ri.metadata.state = :metaState");
        query.setEntity("debitNote", debitNote);
        query.setEntity("knockoff", knockoff);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcKnockoffItem>) query.list();		
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
    public List<AcKnockoffItem> findItems(AcKnockoff knockoff) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from AcKnockoffItem ri where " +
                "ri.knockoff = :knockoff " +
                "and ri.metadata.state = :metaState");
        query.setEntity("knockoff", knockoff);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcKnockoffItem>) query.list();
    }
    
    @Override
    public List<AcKnockoffItem> findItems(AcKnockoff knockoff, AcInvoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from AcKnockoffItem ri where " +
                "ri.knockoff = :knockoff " +
        		"and ri.invoice = :invoice " +
                "and ri.metadata.state = :metaState");
        query.setEntity("knockoff", knockoff);
        query.setEntity("invoice", invoice);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcKnockoffItem>) query.list();
    }
    
    @Override
    public List<AcKnockoffItem> findItems(AcKnockoff knockoff, AcDebitNote debitNote ) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from AcKnockoffItem ri where " +
                "ri.knockoff = :knockoff " +
        		"and ri.debitNote = :debitNote " +
                "and ri.metadata.state = :metaState");
        query.setEntity("knockoff", knockoff);
        query.setEntity("debitNote", debitNote);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcKnockoffItem>) query.list();
    }
    
    @Override
    public List<AcKnockoffInvoice> find(AcKnockoff knockoff) {
    	Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from AcKnockoffInvoice ri where " +
                "ri.knockoff = :knockoff " +
                "and ri.metadata.state = :metaState");
        query.setEntity("knockoff", knockoff);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcKnockoffInvoice>) query.list();
    }
    
    @Override
    public List<AcKnockoffAccountCharge> findAccountCharge(AcKnockoff knockoff) {
    	Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from AcKnockoffAccountCharge ri where " +
                "ri.knockoff = :knockoff " +
                "and ri.metadata.state = :metaState");
        query.setEntity("knockoff", knockoff);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcKnockoffAccountCharge>) query.list();
    }
    
    @Override
    public List<AcKnockoffDebitNote> findAccountDebitNote(AcKnockoff knockoff) {
    	Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from AcKnockoffDebitNote ri where " +
                "ri.knockoff = :knockoff " +
                "and ri.metadata.state = :metaState");
        query.setEntity("knockoff", knockoff);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcKnockoffDebitNote>) query.list();
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

	@Override
	public boolean hasKnockoff(AcKnockoff knockoff, AcInvoice invoice) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from AcKnockoffItem a " + "where a.knockoff = :knockoff "
				+ "and a.invoice = :invoice "
				+ "and a.metadata.state = :state ");
		query.setEntity("knockoff", knockoff);
		query.setEntity("invoice", invoice);
		query.setInteger("state", ACTIVE.ordinal());
		Long count = (Long) query.uniqueResult();
		return count.intValue() > 0; // > 0 = true, <=0 false
	}
	
    @Override
    public void addItem(AcKnockoff knockoff, AcKnockoffItem item, AcUser user) {
        LOG.info("knockoff id : " + knockoff.getId());
        LOG.info("User : " + user.getRealName());

        Validate.notNull(knockoff, "knockoff cannot be null");
        Validate.notNull(item, "Item cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        item.setKnockoff(knockoff);

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        item.setMetadata(metadata);
        session.save(item);
    }

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
	
    @Override
    public void addKnockoffInvoice(AcKnockoff knockoff, AcInvoice invoice, AcUser user) {
        LOG.info("knockoff id : " + knockoff.getId());
        LOG.info("User : " + user.getRealName());

        Validate.notNull(knockoff, "knockoff cannot be null");
        Validate.notNull(invoice, "Invoice cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        AcKnockoffInvoice knockoffInvc = new AcKnockoffInvoiceImpl();
        knockoffInvc.setInvoice(invoice);
        knockoffInvc.setKnockoff(knockoff);

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        knockoffInvc.setMetadata(metadata);
        session.saveOrUpdate(knockoffInvc);
    }
    
    @Override
    public void addKnockoffAccountCharge(AcKnockoff knockoff, AcAccountCharge accountCharge, AcUser user) {
        LOG.info("knockoff id : " + knockoff.getId());
        LOG.info("User : " + user.getRealName());

        Validate.notNull(knockoff, "knockoff cannot be null");
        Validate.notNull(accountCharge, "Invoice cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        AcKnockoffAccountCharge knockoffChrg = new AcKnockoffAccountChargeImpl();
        knockoffChrg.setAccountCharge(accountCharge);
        knockoffChrg.setKnockoff(knockoff);

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        knockoffChrg.setMetadata(metadata);
        session.saveOrUpdate(knockoffChrg);
    }
    
    @Override
    public void addKnockoffDebitNote(AcKnockoff knockoff, AcDebitNote debitNote, AcUser user) {
    	LOG.info("knockoff id : " + knockoff.getId());
        LOG.info("User : " + user.getRealName());

        Validate.notNull(knockoff, "knockoff cannot be null");
        Validate.notNull(debitNote, "debitNote cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        AcKnockoffDebitNote knockoffdebt = new AcKnockoffDebitNoteImpl();
        knockoffdebt.setDebitNote(debitNote);
        knockoffdebt.setKnockoff(knockoff);

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        knockoffdebt.setMetadata(metadata);
        session.saveOrUpdate(knockoffdebt);
    }
    
    @Override
    public void updateItem(AcKnockoff knockoff, AcKnockoffItem item, AcUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();
        item.setKnockoff(knockoff);

        AcMetadata metadata = item.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        item.setMetadata(metadata);
        session.update(item);
    }
    
    @Override
    public void deleteKnockoffInvoice(AcKnockoffInvoice knockoffInvoice, AcUser user) {
        Validate.notNull(knockoffInvoice, "knockoff invoice cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(knockoffInvoice);
    }
    
    @Override
    public void deleteKnockoffAccCharge(AcKnockoffAccountCharge knockoffAccCharge, AcUser user) {
    	Validate.notNull(knockoffAccCharge, "knockoff acc charge cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(knockoffAccCharge);
    }
    
    @Override
    public void deleteKnockoffDebitNote(AcKnockoffDebitNote knockoffDebitNote, AcUser user) {
    	Validate.notNull(knockoffDebitNote, "knockoff debit note cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(knockoffDebitNote);
    }
    
    @Override
    public void deleteItem(AcKnockoff knockoff, AcKnockoffItem item, AcUser user) {
        Validate.notNull(knockoff, "knockoff cannot be null");
        Validate.notNull(item, "payment cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
    }
    
    @Override
    public BigDecimal sumAppliedAmount(AcKnockoff knockoff, AcUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(a.appliedAmount) from AcKnockoffItem a where " +
                "a.knockoff = :knockoff " +
                "and a.metadata.state = :state ");
        query.setEntity("knockoff", knockoff);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else return (BigDecimal) result;
    }
    
    @Override
    public BigDecimal sumAmount(AcInvoice invoice, AcKnockoff knockoff, AcUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(a.appliedAmount) from AcKnockoffItem a where " +
                "a.invoice = :invoice " +
        		"and a.knockoff = :knockoff " +
                "and a.metadata.state = :state ");
        query.setEntity("invoice", invoice);
        query.setEntity("knockoff", knockoff);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else return (BigDecimal) result;
    }
    
    @Override
    public BigDecimal sumAmount(AcDebitNote debitNote, AcKnockoff knockoff, AcUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(a.appliedAmount) from AcKnockoffItem a where " +
                "a.debitNote = :debitNote " +
        		"and a.knockoff = :knockoff " +
                "and a.metadata.state = :state ");
        query.setEntity("debitNote", debitNote);
        query.setEntity("knockoff", knockoff);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else return (BigDecimal) result;
    }
    
    @Override
    public BigDecimal sumTotalAmount(AcKnockoff knockoff, AcAccountCharge accountCharge, AcUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(a.appliedAmount) from AcKnockoffItem a where " +
                "a.accountCharge = :accountCharge " +
                "and a.knockoff = :knockoff " +
                "and a.metadata.state = :state ");
        query.setEntity("accountCharge", accountCharge);
        query.setEntity("knockoff", knockoff);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else return (BigDecimal) result;
    }
    
    @Override
    public BigDecimal sumTotalAmount(AcDebitNote debitNote, AcKnockoff knockoff, AcUser user) {
    	Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(a.appliedAmount) from AcKnockoffItem a where " +
                "a.knockoff = :knockoff " +
        		"and a.debitNote = :debitNote " +
                "and a.metadata.state = :state ");
        query.setEntity("debitNote", debitNote);
        query.setEntity("knockoff", knockoff);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else return (BigDecimal) result;
    }
    
    @Override
    public boolean hasChargeKnockoffItem(AcAccountCharge accountCharge, AcKnockoff knockoff) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from AcKnockoffItem a " + "where a.knockoff = :knockoff "
				+ "and a.accountCharge = :accountCharge "
				+ "and a.metadata.state = :state ");
		query.setEntity("knockoff", knockoff);
		query.setEntity("accountCharge", accountCharge);
		query.setInteger("state", ACTIVE.ordinal());
		Long count = (Long) query.uniqueResult();
		return count.intValue() > 0; // > 0 = true, <=0 false
    }  
    
    @Override
    public boolean hasDebitKnockoffItem(AcDebitNote debitNote, AcKnockoff knockoff) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from AcKnockoffItem a " + "where a.knockoff = :knockoff "
				+ "and a.debitNote = :debitNote "
				+ "and a.metadata.state = :state ");
		query.setEntity("knockoff", knockoff);
		query.setEntity("debitNote", debitNote);
		query.setInteger("state", ACTIVE.ordinal());
		Long count = (Long) query.uniqueResult();
		return count.intValue() > 0; // > 0 = true, <=0 false
    }   
    
    @Override
    public Integer countItem(AcKnockoff knockoff) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(ri) from AcKnockoffItem ri where " +
                "ri.knockoff = :knockoff");
        query.setEntity("knockoff", knockoff);
        return ((Long) query.uniqueResult()).intValue();
    }
    
}
