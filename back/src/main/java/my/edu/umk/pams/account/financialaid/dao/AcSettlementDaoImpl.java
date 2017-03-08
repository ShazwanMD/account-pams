package my.edu.umk.pams.account.financialaid.dao;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.financialaid.model.AcSettlementImpl;
import my.edu.umk.pams.account.financialaid.model.AcSettlementItem;
import my.edu.umk.pams.account.financialaid.model.AcSettlementItemImpl;
import my.edu.umk.pams.account.identity.model.AcUser;
import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author PAMS
 */
@Repository("acSettlementDao")
public class AcSettlementDaoImpl extends GenericDaoSupport<Long, AcSettlement> implements AcSettlementDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcSettlementDaoImpl.class);

    public AcSettlementDaoImpl() {
        super(AcSettlementImpl.class);
    }

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    @Override
    public AcSettlement findByReferenceNo(String referenceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcSettlement a where " +
                "a.referenceNo = :referenceNo " +
                "and a.metadata.state = :state");
        query.setString("referenceNo", referenceNo);
        query.setCacheable(true);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (AcSettlement) query.uniqueResult();
    }

    @Override
    public AcSettlementItem findItemById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (AcSettlementItem) session.get(AcSettlementItemImpl.class, id);
    }

    @Override
    public List<AcSettlement> find(AcAcademicSession academicSession, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcSettlement a where " +
                "a.session = :academicSession "+
                "and a.metadata.state = :state");
        query.setEntity("academicSession", academicSession);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (List<AcSettlement>) query.list();
    }

    @Override
    public List<AcSettlementItem> findItems(AcSettlement settlement) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcSettlementItem a where " +
                "a.settlement = :settlement "+
                "and a.metadata.state = :state");
        query.setEntity("settlement", settlement);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (List<AcSettlementItem>) query.list();
    }

    @Override
    public List<AcSettlementItem> findItems(AcSettlement settlement, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcSettlementItem a where " +
                "a.settlement = :settlement " +
                "and a.metadata.state = :metaState");
        query.setEntity("settlement", settlement);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        return (List<AcSettlementItem>) query.list();
    }

    // ====================================================================================================
    // HELPER
    // ====================================================================================================


    @Override
    public Integer count(AcAcademicSession academicSession) {
        org.hibernate.Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(a) from AcSettlement a where " +
                "a.session=:academicSession " +
                "and a.metadata.state = :metaState ");
        query.setEntity("academicSession", academicSession);
        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countItem(AcSettlement settlement) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(a) from AcSettlementItem a where " +
                "a.account =:settlement " +
                "and a.metadata.state = :state ");
        query.setEntity("settlement", settlement);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countItem(String filter, AcSettlement settlement) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(a) from AcSettlementItem a where " +
                "a.account = :settlement " +
                "and a.metadata.state = :state ");
        query.setEntity("settlement", settlement);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countSource(AcSettlement settlement) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(a) from AcSettlementSource a where " +
                "a.settlement =:settlement " +
                "and a.metadata.state = :state ");
        query.setEntity("settlement", settlement);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countSource(String filter, AcSettlement settlement) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(a) from AcSettlementSource a where " +
                "a.settlement = :settlement " +
                "and a.metadata.state = :state ");
        query.setEntity("settlement", settlement);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isSettlementSourceExists(String credentialNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcSettlementSource s where " +
                "upper(s.credentialNo) = upper(:credentialNo) " +
                "and s.metadata.state = :state ");
        query.setString("credentialNo", credentialNo);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue() > 0;
    }

    @Override
    public boolean isSettlementItemExists(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcSettlementItem s where " +
                "s.account =:account " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue() > 0;
    }


    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    @Override
    public void addItem(AcSettlement settlement, AcSettlementItem item, AcUser user) {
        Validate.notNull(settlement, "Batch should not be null");
        Validate.notNull(item, "Item member should not be null");

        Session session = sessionFactory.getCurrentSession();
        item.setSettlement(settlement);

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        item.setMetadata(metadata);
        session.save(item);
    }

    @Override
    public void updateItem(AcSettlement settlement, AcSettlementItem item, AcUser user) {
        Validate.notNull(settlement, "Batch should not be null");
        Validate.notNull(item, "Item member should not be null");

        Session session = sessionFactory.getCurrentSession();
        item.setSettlement(settlement);

        AcMetadata metadata = item.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        item.setMetadata(metadata);
        session.update(item);
    }

    @Override
    public void deleteItem(AcSettlement settlement, AcSettlementItem item, AcUser user) {
        Validate.notNull(settlement, "Batch should not be null");
        Validate.notNull(item, "Item member should not be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
    }

}
