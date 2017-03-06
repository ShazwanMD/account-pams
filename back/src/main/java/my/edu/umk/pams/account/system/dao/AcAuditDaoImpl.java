package my.edu.umk.pams.account.system.dao;

import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.system.model.AcAudit;
import my.edu.umk.pams.account.system.model.AcAuditImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author canang technologies
 * @since 3/8/14
 */
@SuppressWarnings({"unchecked"})
@Repository("auditDao")
public final class AcAuditDaoImpl extends GenericDaoSupport<Long, AcAudit> implements AcAuditDao {

    public AcAuditDaoImpl() {
        super(AcAuditImpl.class);
    }

    @Override
    public List<AcAudit> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AdAudit s where " +
                "(upper(s.message) like upper(:filter)  " +
                "or upper(s.className) like upper(:filter))  " +
                "and s.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setCacheable(true);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AdAudit s where " +
                "(upper(s.message) like upper(:filter)  " +
                "or upper(s.className) like upper(:filter))  " +
                "and s.metadata.state = :state");
        query.setCacheable(true);
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }
}

