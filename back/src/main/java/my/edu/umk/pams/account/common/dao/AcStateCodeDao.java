package my.edu.umk.pams.account.common.dao;

import my.edu.umk.pams.account.common.model.AcStateCode;
import my.edu.umk.pams.account.core.GenericDao;

import java.util.List;

public interface AcStateCodeDao extends GenericDao<Long, AcStateCode> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================
    AcStateCode findByCode(String code);

    List<AcStateCode> find(String filter, Integer offset, Integer limit);

    List<AcStateCode> find(my.edu.umk.pams.account.common.model.AcCountryCode countryCode, Integer offset, Integer limit);

    List<AcStateCode> find(String filter, my.edu.umk.pams.account.common.model.AcCountryCode countryCode, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================
    Integer count(String filter);

    Integer count(my.edu.umk.pams.account.common.model.AcCountryCode countryCode);

    Integer count(String filter, my.edu.umk.pams.account.common.model.AcCountryCode countryCode);

    boolean isExists(String code);


}
