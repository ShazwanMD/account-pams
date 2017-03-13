package my.edu.umk.pams.account.identity.dao;

import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcActorImpl;
import my.edu.umk.pams.account.identity.model.AcActorType;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static my.edu.umk.pams.account.core.AcMetaState.ACTIVE;

/**
 * @author canang technologies
 * @since 1/31/14
 */
@SuppressWarnings({"unchecked"})
@Repository("actorDao")
public class AcActorDaoImpl extends GenericDaoSupport<Long, AcActor> implements AcActorDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcActorDaoImpl.class);

    public AcActorDaoImpl() {
        super(AcActorImpl.class);
    }

    @Override
    public AcActor findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcActor a where " +
                "a.code = :code");
        query.setString("code", code);
        return (AcActor) query.uniqueResult();
    }

    @Override
    public AcActor findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcActor a where " +
                "a.email = :email");
        query.setString("email", email);
        return (AcActor) query.uniqueResult();
    }

    @Override
    public AcActor findByIdentityNo(String identityNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcActor a where " +
                "a.identityNo = :identityNo");
        query.setString("identityNo", identityNo);
        return (AcActor) query.uniqueResult();
    }

    @Override
    public List<AcActor> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcActor a where " +
                "(a.firsName like upper(:filter)" +
                "or a.lastName like uppoer(:filter)) " +
                "order by a.name");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        return query.list();
    }

    @Override
    public List<AcActor> find(AcActorType type, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcActor a where " +
                "a.actorType = :actorType " +
                "order by a.name");
        query.setInteger("actorType", type.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public List<AcActor> find(String filter, AcActorType type, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcActor a where " +
                "(a.firsName like upper(:filter)" +
                "or a.lastName like uppoer(:filter)) " +
                "and a.actorType = :actorType " +
                "order by a.name");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("actorType", type);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(a) from AcActor a where " +
                "(upper(a.firstName) like upper(:filter)  " +
                "or upper(a.lastName) like upper(:filter))  " +
                "and a.metAcAta.state = :state");
        query.setCacheable(true);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter, AcActorType type) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(a) from AcActor a where " +
                "(upper(a.firstName) like upper(:filter)  " +
                "or upper(a.lastName) like upper(:filter))  " +
                "and a.actorType = :actorType " +
                "and a.metAcAta.state = :state");
        query.setCacheable(true);
        query.setInteger("state", ACTIVE.ordinal());
        query.setInteger("actorType", type.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(AcActorType type) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(a) from AcActor a where " +
                "a.actorType = :actorType " +
                "and a.metAcAta.state = :state");
        query.setCacheable(true);
        query.setInteger("state", ACTIVE.ordinal());
        query.setInteger("actorType", type.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }
}
