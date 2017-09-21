package my.edu.umk.pams.account.billing.dao;

import java.math.BigDecimal;
import java.util.List;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcWaiverFinanceApplication;
import my.edu.umk.pams.account.billing.model.AcWaiverItem;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.identity.model.AcUser;

public interface AcWaiverFinanceApplicationDao extends GenericDao<Long, AcWaiverFinanceApplication> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================
	
	AcWaiverFinanceApplication findByReferenceNo(String referenceNo);
	
	List<AcWaiverFinanceApplication> find(AcAcademicSession academicSession, Integer offset, Integer limit);
	
	List<AcWaiverFinanceApplication> findByFlowState(AcFlowState acFlowState);
	
	List<AcWaiverFinanceApplication> findByFlowStates(AcFlowState... acFlowState);
	
	void addWaiverInvoice(AcWaiverFinanceApplication waiver, AcInvoice invoice, AcUser user);
	
	void addWaiverItem(AcWaiverFinanceApplication waiver, AcWaiverItem item, AcUser user);
	
	BigDecimal sumAppliedAmount(AcWaiverFinanceApplication waiver, AcUser user);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(AcAcademicSession academicSession);

	

	

	

}