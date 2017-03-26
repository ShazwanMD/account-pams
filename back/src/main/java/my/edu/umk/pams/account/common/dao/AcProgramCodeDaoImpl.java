package my.edu.umk.pams.account.common.dao;

import my.edu.umk.pams.account.common.model.AcFacultyCode;
import my.edu.umk.pams.account.common.model.AcProgramCode;
import my.edu.umk.pams.account.common.model.AcProgramCodeImpl;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import static my.edu.umk.pams.account.core.AcMetaState.ACTIVE;

import java.util.List;

@Repository("programCodeDao")
public class AcProgramCodeDaoImpl extends GenericDaoSupport<Long, AcProgramCode> implements AcProgramCodeDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcProgramCodeDaoImpl.class);

    public AcProgramCodeDaoImpl() {
        super(AcProgramCodeImpl.class);
    }

    @Override
    public AcProgramCode findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcProgramCode s where s.code = :code and  " +
                " s.metadata.state = :state");
        query.setString("code", code);
        query.setCacheable(true);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return (AcProgramCode) query.uniqueResult();
    }

    @Override
    public AcProgramCode findByUpuCode(String upuCode) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcProgramCode s where s.upuCode = :upuCode and  " +
                " s.metadata.state = :state");
        query.setString("upuCode", upuCode);
        query.setCacheable(true);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return (AcProgramCode) query.uniqueResult();
    }

    @Override
    public List<AcProgramCode> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcProgramCode s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcProgramCode>) query.list();

    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcProgramCode s where " +
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
        Query query = session.createQuery("select count(*) from AcProgramCode s where " +
                "s.code = :code " +
                "and s.metadata.state = :state ");
        query.setString("code", code);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return 0 < ((Long) query.uniqueResult()).intValue();
    }

	@Override
	public List<AcProgramCode> findProgramCodes(AcFacultyCode facultyCode) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select s from AcProgramCode s where " + "s.facultyCode = :facultyCode "
				+ "and s.metadata.state = :state ");
		query.setEntity("facultyCode", facultyCode);
		query.setInteger("state", ACTIVE.ordinal());
		return (List<AcProgramCode>) query.list();
	}
}
