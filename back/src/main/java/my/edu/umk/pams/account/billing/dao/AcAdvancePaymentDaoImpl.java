package my.edu.umk.pams.account.billing.dao;

import static my.edu.umk.pams.account.core.AcMetaState.ACTIVE;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.billing.model.AcAdvancePayment;
import my.edu.umk.pams.account.billing.model.AcAdvancePaymentImpl;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.core.GenericDaoSupport;

@Repository("acAdvancePaymentDao")
public class AcAdvancePaymentDaoImpl extends GenericDaoSupport<Long, AcAdvancePayment> implements AcAdvancePaymentDao {

    public AcAdvancePaymentDaoImpl() {
        super(AcAdvancePaymentImpl.class);
    }
    
    @Override
    public List<AcAdvancePayment> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from AcAdvancePayment i where " +
                "i.metadata.state = :state ");
        query.setInteger("state", ACTIVE.ordinal());
        query.setCacheable(true);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<AcAdvancePayment>) query.list();
    }
    
    @Override
    public List<AcAdvancePayment> find(boolean paid, AcAccount account, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from AcAdvancePayment i where " +
                "i.account = :account " +
                "and i.paid = :paid " +
                "and i.metadata.state = :state ");
        query.setEntity("account", account);
        query.setBoolean("paid", paid);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<AcAdvancePayment>) query.list();
    }
}
