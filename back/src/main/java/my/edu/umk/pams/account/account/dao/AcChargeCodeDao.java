package my.utm.acad.sa.core.das.dao;

import my.utm.acad.sa.core.cmn.dao.GenericDao;
import my.utm.acad.sa.core.das.model.SaChargeCode;

import java.util.List;

/**
 * @author team utmacad
 * @since 9/6/2015.
 */
public interface SaChargeCodeDao extends GenericDao<Long, SaChargeCode> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    SaChargeCode findByCode(String code);

    SaChargeCode findByDescription(String description);

    List<SaChargeCode> find(String filter, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(String filter);

}
