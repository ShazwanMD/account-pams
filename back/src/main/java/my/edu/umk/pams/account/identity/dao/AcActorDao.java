package my.edu.umk.pams.account.identity.dao;

import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcActorType;

import java.util.List;

/**
 * @author canang technologies
 * @since 1/30/14
 */
public interface AcActorDao extends GenericDao<Long, AcActor> {

    AcActor findByCode(String code);

    AcActor findByIdentityNo(String identityNo);

    AcActor findByEmail(String email);

    List<AcActor> find(String filter, Integer offset, Integer limit);

    List<AcActor> find(AcActorType type, Integer offset, Integer limit);

    List<AcActor> find(String filter, AcActorType type, Integer offset, Integer limit);

    Integer count(String filter);

    Integer count(String filter, AcActorType type);

    Integer count(AcActorType type);

    boolean isEmailExists(String email);

}
