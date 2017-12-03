package my.edu.umk.pams.account.identity.dao;

import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcGroup;
import my.edu.umk.pams.account.identity.model.AcUser;

import java.util.List;

/**
 * @author canang technologies
 * @since 1/30/14
 */
public interface AcUserDao extends GenericDao<Long, AcUser> {

    AcUser findByEmail(String email);

    AcUser findByUsername(String username);

    AcUser findByActor(my.edu.umk.pams.account.identity.model.AcActor actor);

    List<AcUser> find(String filter, Integer offset, Integer limit);

    List<AcGroup> findGroups(AcUser user);
    
    AcGroup findGroupByUser(AcUser user);

    Integer count(String filter);

    boolean isExists(String username);

    boolean hasUser(my.edu.umk.pams.account.identity.model.AcActor actor);

}
