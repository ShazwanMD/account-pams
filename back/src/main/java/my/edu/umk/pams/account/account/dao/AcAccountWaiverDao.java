package my.edu.umk.pams.account.account.dao;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountWaiver;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcUser;

import java.util.List;

/**
 * @author PAMS
 */
public interface AcAccountWaiverDao extends GenericDao<Long, AcAccountWaiver> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    AcAccountWaiver findByReferenceNo(String refNo);

    AcAccountWaiver findBySourceNo(String sourceNo);

    List<AcAccountWaiver> find(String filter, Integer offset, Integer limit);

    List<AcAccountWaiver> find(AcAcademicSession academicSession, AcAccount account);

    List<AcAccountWaiver> find(AcAcademicSession academicSession, AcAccount account, Integer offset, Integer limit);

    List<AcAccountWaiver> find(AcAccount account);

    List<AcAccountWaiver> find(AcAccount account, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(AcAccount account);

    Integer count(AcAcademicSession academicSession, AcAccount account);

    boolean isWaiverExists(AcAccount account, AcAcademicSession academicSession);

    boolean isWaiverExists(AcAccount account, String sourceNo);
    
    void update(AcAccountWaiver waiver, AcUser user);
}
