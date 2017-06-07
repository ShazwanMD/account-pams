package my.edu.umk.pams.account.common.dao;

import java.util.List;
import my.edu.umk.pams.account.common.model.AcTaxCode;
import my.edu.umk.pams.account.core.GenericDao;

public interface AcTaxCodeDao extends GenericDao<Long, AcTaxCode> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================
    AcTaxCode findByCode(String code);

    List<AcTaxCode> find(String filter, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================
    Integer count(String filter);

    boolean isExists(String code);
}
