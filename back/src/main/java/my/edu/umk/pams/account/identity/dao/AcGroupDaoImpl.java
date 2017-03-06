package my.edu.umk.pams.account.identity.dao;

import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.model.*;
import org.apache.commons.lang3.Validate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.LongType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static my.edu.umk.pams.account.core.AcMetaState.ACTIVE;

/**
 * @author canang technologies
 * @since 1/31/14
 */
@SuppressWarnings({"unchecked"})
@Repository("groupDao")
public final class AcGroupDaoImpl extends GenericDaoSupport<Long, AcGroup> implements AcGroupDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcGroupDaoImpl.class);

    public AcGroupDaoImpl() {
        super(AcGroupImpl.class);
    }
    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public List<AcGroup> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select g from AcGroup g order by g.name");
        return (List<AcGroup>) query.list();
    }

    @Override
    public AcGroup findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select g from AcGroup g where g.name = :name");
        query.setString("name", name);
        return (AcGroup) query.uniqueResult();
    }


    @Override
    public List<AcGroup> findImmediate(AcPrincipal principal) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select gm.group from AcGroupMember gm inner join gm.principal where " +
                "gm.principal = :principal");
        query.setEntity("principal", principal);
        return (List<AcGroup>) query.list();
    }


    @Override
    public List<AcGroup> findImmediate(AcPrincipal principal, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select gm.group from AcGroupMember gm inner join gm.principal where " +
                "gm.principal = :principal");
        query.setEntity("principal", principal);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<AcGroup>) query.list();
    }

    @Override
    public Set<AcGroup> findEffectiveAsNative(AcPrincipal principal) {
        Set<AcGroup> groups = new HashSet<AcGroup>();
        Session session = sessionFactory.getCurrentSession();
        SQLQuery query = session.createSQLQuery("WITH RECURSIVE " +
                "Q AS " +
                "( SELECT  GROUP_ID, PRINCIPAL_ID " +
                "    FROM    AC_GROP_MMBR " +
                "    WHERE   PRINCIPAL_ID = " + principal.getId() +
                "    UNION ALL " +
                "    SELECT  GM.GROUP_ID, GM.PRINCIPAL_ID " +
                "    FROM    AC_GROP_MMBR GM " +
                "    JOIN    Q " +
                "    ON      GM.PRINCIPAL_ID = Q.GROUP_ID " +
                ") " +
                "SELECT  GROUP_ID FROM  Q");
        query.addScalar("GROUP_ID", LongType.INSTANCE);
        List<Long> results = (List<Long>) query.list();
        for (Long result : results) {
            groups.add(findById(result));
        }
        return groups;
    }

    @Override
    public List<AcGroup> findAvailableGroups(AcUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from AcGroup p where " +
                "p not in (select gm.group from AcGroupMember gm where gm.principal = :user) " +
                "and p <> :user " +
                "and p.metadata.state = :state " +
                "order by p.name asc");
        query.setInteger("state", ACTIVE.ordinal());
        query.setEntity("user", user);
        return (List<AcGroup>) query.list();
    }

    @Override
    public List<AcPrincipal> findAvailableMembers(AcGroup group) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from AcPrincipal p where " +
                "p not in (select gm.principal from AcGroupMember gm where gm.group = :group) " +
                "and p <> :group " +
                "and p.metadata.state = :state " +
                "order by p.name asc");
        query.setInteger("state", ACTIVE.ordinal());
        query.setEntity("group", group);
        return (List<AcPrincipal>) query.list();
    }

    @Override
    public List<AcPrincipal> findMembers(AcGroup group, AcPrincipalType principalType) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select gm.group from AcGroupMember gm inner join gm.principal where " +
                "gm.group = :group " +
                "and gm.principal.principalType= :principalType " +
                "and gm.principal.metadata.state = :state ");
        query.setEntity("group", group);
        query.setInteger("principalType", principalType.ordinal());
        return (List<AcPrincipal>) query.list();
    }

    @Override
    public List<AcPrincipal> findMembers(AcGroup group) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select gm.principal from AcGroupMember gm where " +
                "gm.group = :group " +
                "and gm.principal.metadata.state = :state " +
                "order by gm.principal.name");
        query.setEntity("group", group);
        query.setInteger("state", ACTIVE.ordinal());
        return (List<AcPrincipal>) query.list();
    }

    @Override
    public List<AcPrincipal> findMembers(AcGroup group, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select gm.principal from AcGroupMember gm where " +
                "gm.group = :group " +
                "and gm.principal.metadata.state = :state " +
                "order by gm.principal.name");
        query.setEntity("group", group);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<AcPrincipal>) query.list();
    }

    @Override
    public List<AcGroup> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct g from AcGroup g where " +
                "g.name like upper(:filter) ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<AcGroup>) query.list();
    }

    @Override
    public List<AcGroup> findMemberships(AcPrincipal principal) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct gm.group from AcGroupMember gm where " +
                "gm.principal = :principal ");
        query.setEntity("principal", principal);
        return (List<AcGroup>) query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(g) from AcGroup g");
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(g) from AcGroup g where " +
                "g.name like upper(:filter)");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countMember(AcGroup group) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(gm.principal) from AcGroupMember gm where " +
                "gm.group = :group " +
                "and gm.principal.metadata.state = :state");
        query.setEntity("group", group);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isExists(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(g) from AcGroup g where " +
                "g.name = :name");
        query.setString("name", name);
        return ((Long) query.uniqueResult()).intValue() >= 1;
    }

    @Override
    public boolean hasMembership(AcGroup group, AcPrincipal principal) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(gm) from AcGroupMember gm where " +
                "gm.group = :group " +
                "and gm.principal = :principal");
        query.setEntity("group", group);
        query.setEntity("principal", principal);
        return ((Long) query.uniqueResult()).intValue() >= 1;
    }

// =============================================================================
    // CRUD METHODS
    // =============================================================================

    @Override
    public void addMember(AcGroup group, AcPrincipal member, AcUser user) throws RecursiveGroupException {
        Validate.notNull(group, "Group should not be null");
        Validate.notNull(member, "Group member should not be null");

        if (member instanceof AcGroup) {
            if (checkRecursive(group, (AcGroup) member))
                throw new RecursiveGroupException("Recursive user group " + group.getName() + " > " + member.getName());
        }

        Session session = sessionFactory.getCurrentSession();
        AcGroupMember groupMember = new AcGroupMemberImpl();
        groupMember.setGroup(group);
        groupMember.setPrincipal(member);
        session.save(groupMember);
    }

    @Override
    public void addMembers(AcGroup group, List<AcPrincipal> members, AcUser user) throws RecursiveGroupException {
        List<AcPrincipal> currentMembers = findMembers(group);
        List<AcPrincipal> newMembers = new ArrayList<AcPrincipal>();

        for (AcPrincipal currentMember : currentMembers) {
            if (!newMembers.contains(currentMember)) {
                deleteMember(group, currentMember);
            }
        }
        for (AcPrincipal newMember : newMembers) {
            if (!currentMembers.contains(newMember)) {
                addMember(group, newMember, user);
            }
        }
    }

    @Override
    public void deleteMember(AcGroup group, AcPrincipal member) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select g from AcGroupMember g where " +
                "g.group = :group " +
                "and g.principal = :principal");
        query.setEntity("group", group);
        query.setEntity("principal", member);
        AcGroupMember groupMember = (AcGroupMember) query.uniqueResult();
        session.delete(groupMember);
    }

    private boolean checkRecursive(AcGroup parent, AcGroup child) {
        Set<AcGroup> hierarchicalGroup = findEffectiveAsNative(parent);
        return !hierarchicalGroup.add(child);
    }

}
