package my.edu.umk.pams.account.identity.dao;

import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static my.edu.umk.pams.account.core.AcMetaState.ACTIVE;


/**
 * @author canang technologies
 * @since 1/31/14
 */
@Repository("principalDao")
public class AcPrincipalDaoImpl extends GenericDaoSupport<Long, AcPrincipal> implements AcPrincipalDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcPrincipalDaoImpl.class);

    public AcPrincipalDaoImpl() {
        super(AcPrincipalImpl.class);
    }

    @Override
    public List<AcPrincipal> findAllPrincipals() {
        Session session = sessionFactory.getCurrentSession();
        List<AcPrincipal> results = new ArrayList<AcPrincipal>();
        Query query = session.createQuery("select p from AcUser p order by p.name");
        results.addAll((List<AcPrincipal>) query.list());

        Query queryGroup = session.createQuery("select p from AcGroup p order by p.name ");
        results.addAll((List<AcPrincipal>) queryGroup.list());

        return results;
    }

    @Override
    public List<AcPrincipal> find(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from AcPrincipal p where p.name like :filter order by p.name");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        return query.list();
    }

    @Override
    public List<AcPrincipal> find(String filter, AcPrincipalType type) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from AcPrincipal p where " +
                "p.name like :filter " +
                "and p.principalType = :principalType " +
                "order by p.name");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("principalType", type.ordinal());
        return query.list();
    }

    @Override
    public List<AcPrincipal> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from AcPrincipal p where " +
                "p.id in (" +
                "select u.id from AcUser u where " +
                "(upper(u.name) like upper(:filter)" +
                "or upper(u.firstName) like upper(:filter)" +
                "or upper(u.lastName) like upper(:filter))" +
                ") " +
                "or p.id in (select g.id from AcGroup g  where upper(g.name) like upper(:filter)) " +
                "order by p.name");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public void addRole(AcPrincipal principal, AcPrincipalRole role, AcUser user) {
        Session session = sessionFactory.getCurrentSession();
        role.setPrincipal(principal);

        // prepare metadata
        my.edu.umk.pams.account.core.AcMetadata metadata = new my.edu.umk.pams.account.core.AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(ACTIVE);
        role.setMetadata(metadata);
        session.save(role);
    }

    @Override
    public void deleteRole(AcPrincipal principal, AcPrincipalRole role, AcUser user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(role);
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(u) from AcPrincipal u where " +
                "u.name like :filter ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public AcPrincipal findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from AcPrincipal p where " +
                "upper(p.name) = upper(:name) ");
        query.setString("name", name);
        return (AcPrincipal) query.uniqueResult();
    }
}
