package my.edu.umk.pams.account.financialaid.dao;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import java.util.List;

/**
 * @author PAMS
 */
public interface AcWaiverApplicationDao extends GenericDao<Long, AcWaiverApplication> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    AcWaiverApplication findByReferenceNo(String referenceNo);
    
    List<AcWaiverApplication> findByFlowState(AcFlowState acFlowState);

    List<AcWaiverApplication> findByFlowStates(AcFlowState... acFlowState);

    List<AcWaiverApplication> find(AcAcademicSession academicSession, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(AcAcademicSession academicSession);

}
