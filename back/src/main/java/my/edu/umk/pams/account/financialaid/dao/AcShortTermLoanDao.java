package my.edu.umk.pams.account.financialaid.dao;

import java.util.List;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.financialaid.model.AcSettlementItem;
import my.edu.umk.pams.account.financialaid.model.AcShortTermLoan;
import my.edu.umk.pams.account.identity.model.AcUser;

public interface AcShortTermLoanDao {
	
    // ====================================================================================================
    // FINDER
    // ====================================================================================================

	AcShortTermLoan findByReferenceNo(String referenceNo);
	
	List<AcShortTermLoan> find(AcAcademicSession academicSession, Integer offset, Integer limit);
	
    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void addShortTermLoan(AcShortTermLoan shortTermLoan, AcUser user);

    void updateShortTermLoan(AcShortTermLoan shortTermLoan, AcUser user);

    void deleteShortTermLoan(AcShortTermLoan shortTermLoan, AcUser user);
}
