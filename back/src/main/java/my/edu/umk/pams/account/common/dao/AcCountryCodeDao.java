package my.edu.umk.pams.account.common.dao;

import my.edu.umk.pams.account.common.model.AcCountryCode;
import my.edu.umk.pams.account.core.GenericDao;

import java.util.List;

public interface AcCountryCodeDao extends GenericDao<Long, AcCountryCode> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================
    AcCountryCode findByCode(String code);

    List<AcCountryCode> find(String filter, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(String filter);

    boolean isExists(String code);
}
