package my.edu.umk.pams.account.account.dao;

import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.core.GenericDao;

import java.util.List;

/**
 * @author PAMS
 */
public interface AcChargeCodeDao extends GenericDao<Long, AcChargeCode> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    AcChargeCode findByCode(String code);

    AcChargeCode findByDescription(String description);

    List<AcChargeCode> find(String filter, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(String filter);

}
