package my.edu.umk.pams.account.identity.dao;


import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcGroup;
import my.edu.umk.pams.account.identity.model.AcPrincipal;
import my.edu.umk.pams.account.identity.model.AcPrincipalType;
import my.edu.umk.pams.account.identity.model.AcUser;

import java.util.List;
import java.util.Set;

/**
 * @author canang technologies
 * @since 1/30/14
 */
public interface AcGroupDao extends GenericDao<Long, AcGroup> {

    // ====================================================================================================
    // HELPERS
    // ====================================================================================================
    boolean hasMembership(AcGroup group, AcPrincipal principal);

    boolean isExists(String name);

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    AcGroup findByName(String name);

    List<AcGroup> findAll();

    List<AcGroup> findImmediate(AcPrincipal principal);

    List<AcGroup> findImmediate(AcPrincipal principal, Integer offset, Integer limit);

    Set<AcGroup> findEffectiveAsNative(AcPrincipal principal);

    List<AcGroup> findAvailableGroups(AcUser user);

    List<AcPrincipal> findAvailableMembers(AcGroup group);

    List<AcPrincipal> findMembers(AcGroup group, AcPrincipalType principalType);

    List<AcPrincipal> findMembers(AcGroup group);

    List<AcPrincipal> findMembers(AcGroup group, Integer offset, Integer limit);

    List<AcGroup> find(String filter, Integer offset, Integer limit);

    List<AcGroup> findMemberships(AcPrincipal principal);

    Integer count(String filter);

    Integer countMember(AcGroup group);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void addMember(AcGroup group, AcPrincipal member, AcUser user) throws RecursiveGroupException;

    void addMembers(AcGroup group, List<AcPrincipal> members, AcUser user) throws RecursiveGroupException;

    void deleteMember(AcGroup group, AcPrincipal member);

}
