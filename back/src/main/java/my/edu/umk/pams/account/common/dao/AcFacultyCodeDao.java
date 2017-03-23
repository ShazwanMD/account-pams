package my.edu.umk.pams.account.common.dao;

import my.edu.umk.pams.account.common.model.AcFacultyCode;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcStudent;

import java.util.List;

public interface AcFacultyCodeDao extends GenericDao<Long, AcFacultyCode> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    AcFacultyCode findByCode(String code);

    List<AcFacultyCode> find(String filter, Integer offset, Integer limit);

    List<AcFacultyCode> find(AcStudent student);
    
    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(String filter);

    boolean isExists(String code);
}
