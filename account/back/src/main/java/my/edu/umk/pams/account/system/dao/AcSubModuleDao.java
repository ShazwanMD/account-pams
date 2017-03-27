package my.edu.umk.pams.account.system.dao;


import my.edu.umk.pams.account.core.GenericDao;

import java.util.List;

/**
 * @author canang technologies
 * @since 4/18/14
 */
public interface AcSubModuleDao extends GenericDao<Long, my.edu.umk.pams.account.system.model.AcSubModule> {

    my.edu.umk.pams.account.system.model.AcSubModule findByCode(String code);

    List<my.edu.umk.pams.account.system.model.AcSubModule> find();

    List<my.edu.umk.pams.account.system.model.AcSubModule> find(my.edu.umk.pams.account.system.model.AcModule module, Integer offset, Integer limit);

    Integer count();

    Integer count(my.edu.umk.pams.account.system.model.AcModule module);

    Integer count(String filter);

    boolean isExists(String code);
}
