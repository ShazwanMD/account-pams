package my.edu.umk.pams.account.billing.dao;

import org.springframework.stereotype.Repository;

import my.edu.umk.pams.account.billing.model.AcAdvancePayment;
import my.edu.umk.pams.account.billing.model.AcAdvancePaymentImpl;
import my.edu.umk.pams.account.core.GenericDaoSupport;

@Repository("acAdvancePaymentDao")
public class AcAdvancePaymentDaoImpl extends GenericDaoSupport<Long, AcAdvancePayment> implements AcAdvancePaymentDao {

    public AcAdvancePaymentDaoImpl() {
        super(AcAdvancePaymentImpl.class);
    }
}
