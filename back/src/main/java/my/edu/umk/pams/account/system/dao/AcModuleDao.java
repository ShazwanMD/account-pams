package my.edu.umk.pams.account.system.dao;


import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.system.model.AcModule;
import my.edu.umk.pams.account.system.model.AcSubModule;

import java.util.List;

/**
 * @author canang technologies
 * @since 4/18/14
 */
public interface AcModuleDao extends GenericDao<Long, AcModule> {

    AcModule findByCode(String code);

    List<AcModule> find();

    Integer count(String filter);

    boolean isExists(String code);

    boolean isSubModuleExists(String code);

    void addSubModule(AcModule module, AcSubModule subModule, AcUser user);

    void updateSubModule(AcModule module, AcSubModule subModule, AcUser user);

    void removeSubModule(AcModule module, AcSubModule subModule, AcUser user);
}
