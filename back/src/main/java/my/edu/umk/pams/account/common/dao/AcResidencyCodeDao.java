package my.edu.umk.pams.account.common.dao;

import my.edu.umk.pams.account.common.model.AcResidencyCode;
import my.edu.umk.pams.account.core.GenericDao;

import java.util.List;

public interface AcResidencyCodeDao extends GenericDao<Long, AcResidencyCode> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================
    AcResidencyCode findByCode(String code);

    List<AcResidencyCode> find(String filter, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================
    Integer count(String filter);

    boolean isExists(String code);
}
