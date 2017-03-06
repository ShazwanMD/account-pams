package my.edu.umk.pams.account.common.dao;

import my.edu.umk.pams.account.common.model.AcCityCode;
import my.edu.umk.pams.account.core.GenericDao;

import java.util.List;

public interface AcCityCodeDao extends GenericDao<Long, AcCityCode> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    AcCityCode findByCode(String code);

    List<AcCityCode> find(my.edu.umk.pams.account.common.model.AcStateCode stateCode);

    List<AcCityCode> find(my.edu.umk.pams.account.common.model.AcStateCode stateCode, Integer offset, Integer limit);

    List<AcCityCode> find(String filter, my.edu.umk.pams.account.common.model.AcStateCode stateCode, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(my.edu.umk.pams.account.common.model.AcStateCode stateCode);

    Integer count(String filter, my.edu.umk.pams.account.common.model.AcStateCode stateCode);

    boolean isExists(String code);
}
