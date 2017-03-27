package my.edu.umk.pams.account.common.dao;

import my.edu.umk.pams.account.common.model.AcFacultyCode;
import my.edu.umk.pams.account.core.GenericDao;

import java.util.List;

public interface AcFacultyCodeDao extends GenericDao<Long, AcFacultyCode> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    AcFacultyCode findByCode(String code);

    List<AcFacultyCode> find(String filter, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(String filter);

    boolean isExists(String code);
}
