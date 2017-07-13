package my.edu.umk.pams.account.billing.dao;

import my.edu.umk.pams.account.billing.model.AcAdvancePayment;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcUser;

public interface AcAdvancePaymentDao extends GenericDao<Long, AcAdvancePayment> {

	AcAdvancePayment findByReferenceNo(String referenceNo);
	
	boolean hasAdvancePayment(AcAdvancePayment advancePayment);
	
    void addAdvancePayment(AcAdvancePayment advancePayment, AcUser user);

    void updateAdvancePayment(AcAdvancePayment advancePayment, AcUser user);

    void removeAdvancePayment(AcAdvancePayment advancePayment, AcUser user);
	
}
