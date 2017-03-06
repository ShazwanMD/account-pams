package my.edu.umk.pams.account.system.dao;

import my.edu.umk.pams.account.core.AcMetaObject;

/**
 * @author canang technologies
 * @since 5/25/14
 */
public interface AcMetaObjectDao {

    AcMetaObject findMetaObjectById(Class clazz, Long id);
}
