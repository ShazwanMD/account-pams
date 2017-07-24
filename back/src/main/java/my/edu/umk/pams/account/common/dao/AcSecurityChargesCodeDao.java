package my.edu.umk.pams.account.common.dao;

import java.util.List;

import my.edu.umk.pams.account.common.model.AcSecurityChargesCode;
import my.edu.umk.pams.account.core.GenericDao;

public interface AcSecurityChargesCodeDao extends GenericDao<Long, AcSecurityChargesCode> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================
    AcSecurityChargesCode findByCode(String code);

    List<AcSecurityChargesCode> find(String filter, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================
    Integer count(String filter);

    boolean isExists(String code);
}
