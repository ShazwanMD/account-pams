package my.utm.acad.sa.core.das.dao;

import my.utm.acad.sa.core.cmn.dao.GenericDao;
import my.utm.acad.sa.core.das.model.SaAcademicSession;

import java.util.List;

/**
 * @author team utmacad
 * @since 20/4/2015
 */
public interface SaAcademicSessionDao extends GenericDao<Long, SaAcademicSession> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    SaAcademicSession findByCode(String code);

    SaAcademicSession findCurrentSession();

    SaAcademicSession findNextSession(SaAcademicSession current);

    SaAcademicSession findPreviousSession();

    List<SaAcademicSession> find(String filter, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(String filter);

    boolean isCodeExists(String code);
}
