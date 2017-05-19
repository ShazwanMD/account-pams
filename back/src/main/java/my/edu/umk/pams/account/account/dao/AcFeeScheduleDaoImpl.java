package my.edu.umk.pams.account.account.dao;

import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcStudyMode;
import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.core.GenericDaoSupport;
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
@Repository("acFeeScheduleDao")
public class AcFeeScheduleDaoImpl extends GenericDaoSupport<Long, AcFeeSchedule> implements AcFeeScheduleDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcAccountDaoImpl.class);

    public AcFeeScheduleDaoImpl() {
        super(AcFeeScheduleImpl.class);
    }

    @Override
    public AcFeeSchedule findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sa from AcFeeSchedule sa where sa.code = :code and  " +
                " sa.metadata.state = :state");
        query.setString("code", code);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (AcFeeSchedule) query.uniqueResult();
    }

    @Override
    public AcFeeSchedule findByCohortCodeAndStudyMode(AcCohortCode cohortCode,AcStudyMode studyMode) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sa from AcFeeSchedule sa where " +
                "sa.cohortCode = :cohortCode " +
                "and sa.studyMode = :studyMode " +
                "and sa.metadata.state = :state");
        query.setEntity("cohortCode", cohortCode);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (AcFeeSchedule) query.uniqueResult();
    }

    @Override
    public AcFeeScheduleItem findItemById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (AcFeeScheduleItem) session.get(AcFeeScheduleItemImpl.class, id);
    }

    @Override
    public List<AcFeeSchedule> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcFeeSchedule s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)" +
                "or upper(s.cohortCode.code) like upper(:filter)" +
                "or upper(s.cohortCode.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcFeeSchedule>) query.list();
    }

    @Override
    public List<AcFeeScheduleItem> findItems(AcFeeSchedule schedule) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcFeeScheduleItem s where " +
                "s.schedule = :schedule " +
                "and s.metadata.state = :state ");
        query.setEntity("schedule", schedule);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcFeeScheduleItem>) query.list();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccount s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)" +
                "or upper(s.cohortCode.code) like upper(:filter)" +
                "or upper(s.cohortCode.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countItem(AcFeeSchedule schedule) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcFeeScheduleItem s where " +
                "s.schedule=:schedule ");
        query.setEntity("schedule", schedule);
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(AcCohortCode cohortCode) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcFeeSchedule s where " +
                "s.cohortCode=:cohortCode " +
                "and s.metadata.state = :state ");
        query.setEntity("cohortCode", cohortCode);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean hasSchedule(AcCohortCode cohortCode) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from AcFeeSchedule u where " +
                "u.cohortCode = :cohortCode");
        query.setEntity("cohortCode", cohortCode);
        return 0 < ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public void addItem(AcFeeSchedule schedule, AcFeeScheduleItem item, AcUser user) {
        Validate.notNull(schedule, "Account should not be null");
        Validate.notNull(item, "Charge should not be null");

        Session session = sessionFactory.getCurrentSession();
        item.setSchedule(schedule);

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        item.setMetadata(metadata);
        session.save(item);
    }

    @Override
    public void updateItem(AcFeeSchedule schedule, AcFeeScheduleItem item, AcUser user) {
        Validate.notNull(schedule, "Schedule should not be null");
        Validate.notNull(item, "Charge should not be null");

        Session session = sessionFactory.getCurrentSession();
        item.setSchedule(schedule);

        AcMetadata metadata = item.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        item.setMetadata(metadata);
        session.update(item);
    }

    @Override
    public void deleteItem(AcFeeSchedule schedule, AcFeeScheduleItem item, AcUser user) {
        Validate.notNull(schedule, "Schedule should not be null");
        Validate.notNull(item, "Item should not be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
    }
}
