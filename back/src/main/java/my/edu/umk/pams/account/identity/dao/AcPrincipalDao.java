package my.edu.umk.pams.account.identity.dao;

import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.*;
import my.edu.umk.pams.account.identity.model.AcUser;

import java.util.List;

/**
 * @author canang technologies
 * @since 1/30/14
 */
public interface AcPrincipalDao extends GenericDao<Long, AcPrincipal> {

    // ====================================================================================================
    // HELPERS
    // ====================================================================================================

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    AcPrincipal findByName(String name);

    List<AcPrincipal> findAllPrincipals();

    List<AcPrincipal> find(String filter);

    List<AcPrincipal> find(String filter, AcPrincipalType type);

    List<AcPrincipal> find(String filter, Integer offset, Integer limit);

    void addRole(AcPrincipal principal, AcPrincipalRole principalRole, AcUser user);

    void deleteRole(AcPrincipal principal, AcPrincipalRole principalRole, AcUser user);

    Integer count(String filter);


    // ====================================================================================================
    // CRUD
    // ====================================================================================================

}
