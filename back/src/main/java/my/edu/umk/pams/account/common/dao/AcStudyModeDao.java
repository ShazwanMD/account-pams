package my.edu.umk.pams.account.common.dao;

import my.edu.umk.pams.account.common.model.AcStudyMode;
import my.edu.umk.pams.account.core.GenericDao;

import java.util.List;

public interface AcStudyModeDao extends GenericDao<Long, AcStudyMode> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    AcStudyMode findByCode(String code);

    List<AcStudyMode> find(String filter, Integer offset, Integer limit);


    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(String filter);

    boolean isExists(String code);
}
