package my.edu.umk.pams.account.system.dao;


import my.edu.umk.pams.account.core.GenericDao;

import java.util.List;

/**
 * @author canang technologies
 * @since 1/30/14
 */
public interface AcConfigurationDao extends GenericDao<Long, my.edu.umk.pams.account.system.model.AcConfiguration> {

    my.edu.umk.pams.account.system.model.AcConfiguration findByKey(String key);

    List<my.edu.umk.pams.account.system.model.AcConfiguration> findByPrefix(String prefix);

    List<my.edu.umk.pams.account.system.model.AcConfiguration> find(String filter, Integer offset, Integer limit);

    List<my.edu.umk.pams.account.system.model.AcConfiguration> find(String filter);

    List<my.edu.umk.pams.account.system.model.AcConfiguration> findInverse(String filter);

    Integer count(String filter);

}
