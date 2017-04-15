package my.edu.umk.pams.account.system.dao;

import my.edu.umk.pams.account.core.GenericDaoSupport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author canang technologies
 * @since 1/31/14
 */
@Repository("referenceNoDao")
public class AcReferenceNoDaoImpl extends GenericDaoSupport<Long, my.edu.umk.pams.account.system.model.AcReferenceNo> implements AcReferenceNoDao {

    public AcReferenceNoDaoImpl() {
        super(my.edu.umk.pams.account.system.model.AcReferenceNoImpl.class);
    }

    @Override
    public my.edu.umk.pams.account.system.model.AcReferenceNo findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcReferenceNo s where " +
                "s.code = :code and  " +
                " s.metadata.state = :state");
        query.setString("code", code);
        query.setCacheable(true);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return (my.edu.umk.pams.account.system.model.AcReferenceNo) query.uniqueResult();
    }

    @Override
    public List<my.edu.umk.pams.account.system.model.AcReferenceNo> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcReferenceNo s where " +
                "(upper(s.code) like upper(:filter)  " +
                "or upper(s.description) like upper(:filter))  " +
                "and s.metadata.state = :state");
        query.setString("filter", filter);
        query.setCacheable(true);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcReferenceNo s where " +
                "(upper(s.code) like upper(:filter)  " +
                "or upper(s.description) like upper(:filter))  " +
                "and s.metadata.state = :state");
        query.setCacheable(true);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }
}
