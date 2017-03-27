package my.edu.umk.pams.account.common.dao;

import my.edu.umk.pams.account.common.model.AcFacultyCode;
import my.edu.umk.pams.account.common.model.AcProgramCode;
import my.edu.umk.pams.account.core.GenericDao;

import java.util.List;

public interface AcProgramCodeDao extends GenericDao<Long, AcProgramCode> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================
    AcProgramCode findByCode(String code);

    AcProgramCode findByUpuCode(String upuCode);

    List<AcProgramCode> find(String filter, Integer offset, Integer limit);
    
    List<AcProgramCode> findProgramCodes(AcFacultyCode facultyCode );

    // ====================================================================================================
    // HELPER
    // ====================================================================================================
    Integer count(String filter);

    boolean isExists(String code);

}
