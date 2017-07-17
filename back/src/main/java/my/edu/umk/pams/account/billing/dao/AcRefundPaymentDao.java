package my.edu.umk.pams.account.billing.dao;

import my.edu.umk.pams.account.billing.model.AcRefundPayment;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcUser;

public interface AcRefundPaymentDao extends GenericDao<Long, AcRefundPayment> {

	AcRefundPayment findByReferenceNo(String referenceNo);
	
	boolean hasRefundPayment(AcRefundPayment refundPayment);
	
    void addRefundPayment(AcRefundPayment refundPayment, AcUser user);

    void updateRefundPayment(AcRefundPayment refundPayment, AcUser user);

    void removeRefundPayment(AcRefundPayment refundPayment, AcUser user);
}
