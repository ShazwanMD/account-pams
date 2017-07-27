package my.edu.umk.pams.account.system.dao;

import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.system.model.AcEmailQueue;
import my.edu.umk.pams.account.system.model.AcEmailQueueImpl;
import my.edu.umk.pams.account.system.model.AcEmailQueueStatus;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author canang technologies
 * @since 1/31/14
 */
@Repository("emailQueueDao")
public class AcEmailQueueDaoImpl extends GenericDaoSupport<Long, AcEmailQueue> implements AcEmailQueueDao {

    public AcEmailQueueDaoImpl() {
        super(AcEmailQueueImpl.class);
    }

    @Override
    public List<AcEmailQueue> find(AcEmailQueueStatus status) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select eq from AcEmailQueue eq where " +
                "eq.queueStatus = (:status) ");
        query.setInteger("status", status.ordinal());
        return query.list();
    }

    @Override
    public List<AcEmailQueue> find(AcEmailQueueStatus... statuses) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select eq from AcEmailQueue eq where " +
                "eq.queueStatus in (:statuses) ");
        query.setParameterList("statuses", statuses);
        return query.list();
    }
}
