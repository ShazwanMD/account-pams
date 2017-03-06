package my.edu.umk.pams.account.system.dao;

import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.system.model.AcAudit;

import java.util.List;

/**
 * @author canang technologies
 * @since 1/30/14
 */
public interface AcAuditDao extends GenericDao<Long, AcAudit> {

    List<AcAudit> find(String filter, Integer offset, Integer limit);

    Integer count(String filter);
}
