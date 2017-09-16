package my.edu.umk.pams.account.billing.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcKnockoff;
import my.edu.umk.pams.account.billing.model.AcKnockoffInvoice;
import my.edu.umk.pams.account.billing.model.AcReceipt;
import my.edu.umk.pams.account.billing.model.AcReceiptInvoice;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcUser;

public interface AcKnockoffDao extends GenericDao<Long, AcKnockoff> {

	AcKnockoff findByReferenceNo(String referenceNo);
	
	List<AcKnockoff> find(String filter, Integer offset, Integer limit);
	
    List<AcKnockoff> findByFlowState(AcFlowState flowState);

    List<AcKnockoff> findByFlowStates(AcFlowState... flowStates);
    
    List<AcKnockoffInvoice> find(AcKnockoff knockoff);
	
	boolean hasKnockoff(AcKnockoff knockoff);
	
    //void addKnockoff(AcKnockoff knockoff, AcUser user);

    void updateKnockoff(AcKnockoff knockoff, AcUser user);

    void removeKnockoff(AcKnockoff knockoff, AcUser user);
    
    void addKnockoffInvoice(AcKnockoff knockoff, AcInvoice invoice, AcUser user);
}
