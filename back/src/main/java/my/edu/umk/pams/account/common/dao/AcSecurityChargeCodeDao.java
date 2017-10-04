package my.edu.umk.pams.account.common.dao;

import java.util.List;

import my.edu.umk.pams.account.common.model.AcSecurityChargeCode;
import my.edu.umk.pams.account.core.GenericDao;

public interface AcSecurityChargeCodeDao extends GenericDao<Long, AcSecurityChargeCode> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================
    AcSecurityChargeCode findByCode(String code);

    List<AcSecurityChargeCode> find(String filter, Integer offset, Integer limit);
    
	List<AcSecurityChargeCode> findAcSecurityChargeCodesByActive(Boolean current);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================
    Integer count(String filter);

    boolean isExists(String code);


}
