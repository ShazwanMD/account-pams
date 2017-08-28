package my.edu.umk.pams.account.billing.dao;

import java.util.List;

import my.edu.umk.pams.account.billing.model.AcKnockoff;
import my.edu.umk.pams.account.billing.model.AcRefundPayment;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcUser;

public interface AcRefundPaymentDao extends GenericDao<Long, AcRefundPayment> {

	List<AcRefundPayment> find(String filter, Integer offset, Integer limit);
	
    List<AcRefundPayment> findByFlowState(AcFlowState flowState);

    List<AcRefundPayment> findByFlowStates(AcFlowState... flowStates);
	
	AcRefundPayment findByReferenceNo(String referenceNo);
	
	boolean hasRefundPayment(AcRefundPayment refundPayment);
	
    void addRefundPayment(AcRefundPayment refundPayment, AcUser user);

    void updateRefundPayment(AcRefundPayment refundPayment, AcUser user);

    void removeRefundPayment(AcRefundPayment refundPayment, AcUser user);
}
