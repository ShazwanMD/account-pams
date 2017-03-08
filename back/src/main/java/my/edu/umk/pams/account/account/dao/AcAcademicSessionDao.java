package my.edu.umk.pams.account.account.dao;


import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.core.GenericDao;

import java.util.List;

/**
 * @author PAMS
 */
public interface AcAcademicSessionDao extends GenericDao<Long, AcAcademicSession> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    AcAcademicSession findByCode(String code);

    AcAcademicSession findCurrentSession();

    List<AcAcademicSession> find(String filter, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(String filter);

    boolean isCodeExists(String code);
}
