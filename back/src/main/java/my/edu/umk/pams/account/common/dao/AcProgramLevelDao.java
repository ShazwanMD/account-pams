package my.edu.umk.pams.account.common.dao;

import java.util.List;

import my.edu.umk.pams.account.common.model.AcProgramLevel;
import my.edu.umk.pams.account.core.GenericDao;


public interface AcProgramLevelDao extends GenericDao<Long, AcProgramLevel> {
	
    // ===================================================================
    // FINDER
    // ===================================================================

    AcProgramLevel findByCode(String code);

    List<AcProgramLevel> find(String filter, Integer offset, Integer limit);

    // ===================================================================
    // HELPER
    // ===================================================================

    Integer count(String filter);

}
