package my.edu.umk.pams.account.common.dao;

import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcStudent;

import java.util.List;

public interface AcCohortCodeDao extends GenericDao<Long, AcCohortCode> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    AcCohortCode findByCode(String code);

    List<AcCohortCode> find(String filter, Integer offset, Integer limit);

    List<AcCohortCode> find(AcStudent student);
    
    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(String filter);

    boolean isExists(String code);
}
