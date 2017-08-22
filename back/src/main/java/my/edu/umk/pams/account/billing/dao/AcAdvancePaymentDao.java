package my.edu.umk.pams.account.billing.dao;

import java.util.List;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.billing.model.AcAdvancePayment;
import my.edu.umk.pams.account.core.GenericDao;

public interface AcAdvancePaymentDao extends GenericDao<Long, AcAdvancePayment> {
	
	AcAdvancePayment findByReferenceNo(String referenceNo);

	List<AcAdvancePayment> find(boolean status, String filter, Integer offset, Integer limit);
	
	List<AcAdvancePayment> find(boolean paid, AcAccount account, Integer offset, Integer limit);
}
