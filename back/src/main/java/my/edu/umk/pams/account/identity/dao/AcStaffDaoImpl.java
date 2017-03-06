package my.edu.umk.pams.account.identity.dao;

import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.model.AcStaff;
import my.edu.umk.pams.account.identity.model.AcStaffImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author team canang
 * @since 6/6/2015.
 */
@Repository("staffDao")
public class AcStaffDaoImpl extends GenericDaoSupport<Long, AcStaff> implements AcStaffDao {

    public AcStaffDaoImpl() {
        super(AcStaffImpl.class);
    }

    @Override
    public AcStaff findByStaffNo(String staffNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from AcStaff p where " +
                "p.identityNo = :identityNo ");
        query.setString("identityNo", staffNo);
        return (AcStaff) query.uniqueResult();
    }

    @Override
    public AcStaff findByNricNo(String nricNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from AcStaff p where " +
                "p.identityNo= :nricNo "); // TODO: fix this
        query.setString("nricNo", nricNo);
        return (AcStaff) query.uniqueResult();
    }

    @Override
    public AcStaff findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from AcStaff p where " +
                "p.email = :email ");
        query.setString("email", email);
        return (AcStaff) query.uniqueResult();
    }

    @Override
    public AcStaff findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from AcStaff p where " +
                "p.name = :name ");
        query.setString("name", name);
        return (AcStaff) query.uniqueResult();
    }

    @Override
    public List<AcStaff> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcStaff s where " +
                "(upper(s.identityNo) like upper(:filter) " +
                "or upper(s.name) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return (List<AcStaff>) query.list();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcStaff s where " +
                "(upper(s.identityNo) like upper(:filter) " +
                "or upper(s.name) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isExists(String staffNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcStaff s where " +
                "s.identityNo = :staffNo " +
                "and s.metadata.state = :state ");
        query.setString("staffNo", staffNo);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return ((Integer) query.uniqueResult() > 0);
    }

    @Override
    public boolean isEmailExists(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcStaff s where " +
                "s.email = :email " +
                "and s.metadata.state = :state ");
        query.setString("email", email);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return ((Integer) query.uniqueResult() > 0);
    }
}
