package my.edu.umk.pams.account.identity.dao;

import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.model.*;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.identity.model.AcUserImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author canang technologies
 * @since 1/31/14
 */
@SuppressWarnings({"unchecked"})
@Repository("userDao")
public final class AcUserDaoImpl extends GenericDaoSupport<Long, AcUser> implements AcUserDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcUserDaoImpl.class);

    public AcUserDaoImpl() {
        super(AcUserImpl.class);
    }

    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public List<AcGroup> findGroups(AcUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from AcGroup r inner join r.members m where m.id = :id");
        query.setLong("id", user.getId());
        return (List<AcGroup>) query.list();
    }

    @Override
    public boolean isExists(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from AcUser u where " +
                "upper(u.name) = upper(:username) ");
        query.setString("username", username);
        return 0 < ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean hasUser(AcActor actor) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from AcUser u where " +
                "u.actor = :actor");
        query.setEntity("actor", actor);
        return 0 < ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public AcUser findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select u from AcUser u where u.email = :email ");
        query.setString("email", email);
        return (AcUser) query.uniqueResult();
    }

    @Override
    public AcUser findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select u from AcUser u where u.name = :username ");
        query.setString("username", username);
        return (AcUser) query.uniqueResult();
    }

    @Override
    public AcUser findByActor(AcActor actor) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select u from AcUser u where u.actor = :actor ");
        query.setEntity("actor", actor);
        return (AcUser) query.uniqueResult();
    }

    @Override
    public List<AcUser> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcUser s where (" +
                "upper(s.name) like upper(:filter) " +
                "or upper(s.firstName) like upper(:filter) " +
                "or upper(s.lastName) like upper(:filter)) " +
                "order by s.firstName, s.lastName, s.name");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<AcUser>) query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(u) from AcUser u");
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcUser s where " +
                "upper(s.name) like upper(:filter) " +
                "or upper(s.firstName) like upper(:filter) " +
                "or upper(s.lastName) like upper(:filter)");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        return ((Long) query.uniqueResult()).intValue();
    }
}
