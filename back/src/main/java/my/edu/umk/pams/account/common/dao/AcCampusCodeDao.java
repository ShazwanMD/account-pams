package my.edu.umk.pams.account.common.dao;

import my.edu.umk.pams.account.common.model.AcCampusCode;
import my.edu.umk.pams.account.core.GenericDao;

import java.util.List;

public interface AcCampusCodeDao extends GenericDao<Long, AcCampusCode> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    AcCampusCode findByCode(String code);

    List<AcCampusCode> find(String filter, Integer offset, Integer limit);


    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(String filter);

    boolean isExists(String code);
}
