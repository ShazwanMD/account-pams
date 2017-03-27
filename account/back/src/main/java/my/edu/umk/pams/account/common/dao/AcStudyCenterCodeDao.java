package my.edu.umk.pams.account.common.dao;

import my.edu.umk.pams.account.common.model.AcStudyCenterCode;
import my.edu.umk.pams.account.core.GenericDao;

import java.util.List;

public interface AcStudyCenterCodeDao extends GenericDao<Long, AcStudyCenterCode> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    AcStudyCenterCode findByCode(String code);

    List<AcStudyCenterCode> find(String filter, Integer offset, Integer limit);


    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(String filter);

    boolean isExists(String code);
}
