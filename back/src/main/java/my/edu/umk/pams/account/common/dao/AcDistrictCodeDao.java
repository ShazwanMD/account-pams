package my.edu.umk.pams.account.common.dao;

import my.edu.umk.pams.account.common.model.AcDistrictCode;
import my.edu.umk.pams.account.core.GenericDao;

import java.util.List;

public interface AcDistrictCodeDao extends GenericDao<Long, AcDistrictCode> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================
    AcDistrictCode findByCode(String code);

    List<AcDistrictCode> find(String filter, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(String filter);

    boolean isExists(String code);
}
