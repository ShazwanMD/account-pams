package my.edu.umk.pams.account.billing.dao;

import java.util.List;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.billing.model.AcWaiverFinanceApplication;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;

public interface AcWaiverFinanceApplicationDao extends GenericDao<Long, AcWaiverFinanceApplication> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================
	
	AcWaiverFinanceApplication findByReferenceNo(String referenceNo);
	
	List<AcWaiverFinanceApplication> find(AcAcademicSession academicSession, Integer offset, Integer limit);
	
	List<AcWaiverFinanceApplication> findByFlowState(AcFlowState acFlowState);
	
	List<AcWaiverFinanceApplication> findByFlowStates(AcFlowState... acFlowState);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(AcAcademicSession academicSession);

	

	

	

}