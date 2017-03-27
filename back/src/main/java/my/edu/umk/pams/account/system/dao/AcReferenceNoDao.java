package my.edu.umk.pams.account.system.dao;

import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.system.model.AcReferenceNo;

import java.util.List;

/**
 * @author canang technologies
 * @since 1/30/14
 */
public interface AcReferenceNoDao extends GenericDao<Long, AcReferenceNo> {

    AcReferenceNo findByCode(String code);

    List<AcReferenceNo> find(String filter, Integer offset, Integer limit);

    Integer count(String filter);
}
