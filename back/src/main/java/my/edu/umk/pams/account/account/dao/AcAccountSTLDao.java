package my.edu.umk.pams.account.account.dao;

import java.util.List;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountSTL;
import my.edu.umk.pams.account.core.GenericDao;

public interface AcAccountSTLDao extends GenericDao<Long, AcAccountSTL> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

	AcAccountSTL findByReferenceNo(String refNo);

	AcAccountSTL findBySourceNo(String sourceNo);

    List<AcAccountSTL> find(String filter, Integer offset, Integer limit);

    List<AcAccountSTL> find(AcAcademicSession academicSession, AcAccount account);

    List<AcAccountSTL> find(AcAcademicSession academicSession, AcAccount account, Integer offset, Integer limit);

    List<AcAccountSTL> find(AcAccount account);

    List<AcAccountSTL> find(AcAccount account, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(AcAccount account);

    Integer count(AcAcademicSession academicSession, AcAccount account);

    boolean isShortTermlLoanExists(AcAccount account, AcAcademicSession academicSession);

    boolean isShortTermlLoanExists(AcAccount account, String sourceNo);
}
