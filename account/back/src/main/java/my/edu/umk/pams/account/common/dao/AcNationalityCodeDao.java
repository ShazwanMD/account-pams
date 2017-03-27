package my.edu.umk.pams.account.common.dao;

import my.edu.umk.pams.account.common.model.AcNationalityCode;
import my.edu.umk.pams.account.core.GenericDao;

import java.util.List;

public interface AcNationalityCodeDao extends GenericDao<Long, AcNationalityCode> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================
    AcNationalityCode findByCode(String code);

    List<AcNationalityCode> find(String filter, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================
    Integer count(String filter);

    boolean isExists(String code);
}
