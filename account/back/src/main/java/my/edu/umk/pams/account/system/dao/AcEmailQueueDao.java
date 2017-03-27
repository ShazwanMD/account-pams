package my.edu.umk.pams.account.system.dao;

import my.edu.umk.pams.account.core.GenericDao;

import java.util.List;

/**
 * @author canang technologies
 * @since 1/30/14
 */
public interface AcEmailQueueDao extends GenericDao<Long, my.edu.umk.pams.account.system.model.AcEmailQueue> {

    List<my.edu.umk.pams.account.system.model.AcEmailQueue> find(my.edu.umk.pams.account.system.model.AcEmailQueueStatus status);

    List<my.edu.umk.pams.account.system.model.AcEmailQueue> find(my.edu.umk.pams.account.system.model.AcEmailQueueStatus... statuses);

}
