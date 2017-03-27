package my.edu.umk.pams.account.common.dao;


import my.edu.umk.pams.account.common.model.AcBankCode;
import my.edu.umk.pams.account.core.GenericDao;

import java.util.List;

public interface AcBankCodeDao extends GenericDao<Long, AcBankCode> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================
    AcBankCode findByCode(String code);

    List<AcBankCode> find(String filter, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================
    Integer count(String filter);

    boolean isExists(String code);
}
