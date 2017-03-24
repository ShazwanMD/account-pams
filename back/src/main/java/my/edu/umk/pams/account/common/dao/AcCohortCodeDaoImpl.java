package my.edu.umk.pams.account.common.dao;

import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcCohortCodeImpl;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.model.AcStudent;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static my.edu.umk.pams.account.core.AcMetaState.ACTIVE;

@Repository("acCohortCodeDao")
public class AcCohortCodeDaoImpl extends GenericDaoSupport<Long, AcCohortCode> implements AcCohortCodeDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcCohortCodeDaoImpl.class);

    public AcCohortCodeDaoImpl() {
        super(AcCohortCodeImpl.class);
    }

    @Override
    public AcCohortCode findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcCohortCode s where " +
                "s.code = :code " +
                "and s.metadata.state = :state");
        query.setString("code", code);
        query.setCacheable(true);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return (AcCohortCode) query.uniqueResult();
    }

    @Override
    public List<AcCohortCode> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcCohortCode s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcCohortCode>) query.list();
    }

	@Override
	public List<AcCohortCode> find(AcStudent student) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select s from AcCohortCode s where " +
                "s.student = :s.student " +
                "and s.metadata.state = :state");
        query.setEntity("student", student);
        query.setInteger("state", ACTIVE.ordinal());
        return (List<AcCohortCode>) query.list();
	}
    
    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcCohortCode s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isExists(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from AcCohortCode s where " +
                "s.code = :code " +
                "and s.metadata.state = :state ");
        query.setString("code", code);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return 0 < ((Long) query.uniqueResult()).intValue();
    }
}
