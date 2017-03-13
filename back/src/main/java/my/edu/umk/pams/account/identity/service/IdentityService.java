package my.edu.umk.pams.account.identity.service;

import my.edu.umk.pams.account.identity.dao.RecursiveGroupException;
import my.edu.umk.pams.account.identity.model.*;

import java.util.List;
import java.util.Set;

/**
 * @author canang technologies
 * @since 1/30/14
 */
public interface IdentityService {

	// ====================================================================================================
	// PRINCIPAL
	// ====================================================================================================

	AcPrincipal findPrincipalById(Long id);

	AcPrincipal findPrincipalByName(String name);

	List<AcPrincipal> findPrincipals(String filter, Integer offset, Integer limit);

	Set<String> findSids(AcPrincipal principal);

	Integer countPrincipal(String filter);

	void addPrincipalRole(AcPrincipal principal, AcPrincipalRole principalRole);

	void deletePrincipalRole(AcPrincipal principal, AcPrincipalRole principalRole);

	// ====================================================================================================
	// USER
	// ====================================================================================================

	AcUser findUserByEmail(String email);

	AcUser findUserByUsername(String username);

	AcUser findUserById(Long id);

	AcUser findUserByActor(AcActor actor);

	List<AcUser> findUsers(Integer offset, Integer limit);

	List<AcUser> findUsers(String filter, Integer offset, Integer limit);

	Integer countUser();

	Integer countUser(String filter);

	boolean hasUser(AcActor actor);

	boolean isUserExists(String username);

	void saveUser(AcUser user);

	void updateUser(AcUser user);

	void removeUser(AcUser user);

	void changePassword(AcUser user, String newPassword);

	// ====================================================================================================
	// GROUP
	// ====================================================================================================

	AcGroup getRootGroup();

	AcGroup findGroupByName(String name);

	AcGroup findOrCreateGroupByName(String name);

	AcGroup findGroupById(Long id);

	List<AcGroup> findGroups(Integer offset, Integer limit);

	List<AcGroup> findImmediateGroups(AcPrincipal principal);

	List<AcGroup> findImmediateGroups(AcPrincipal principal, Integer offset, Integer limit);

	Set<AcGroup> findEffectiveGroups(AcPrincipal principal);

	Set<String> findEffectiveGroupsAsString(AcPrincipal principal);

	List<AcGroup> findAvailableUserGroups(AcUser user);

	List<AcPrincipal> findAvailableGroupMembers(AcGroup group);

	List<AcPrincipal> findGroupMembers(AcGroup group);

	List<AcPrincipal> findGroupMembers(AcGroup group, Integer offset, Integer limit);

	Integer countGroup();

	Integer countGroupMember(AcGroup group);

	boolean isGroupExists(String name);

	boolean hasMembership(AcGroup group, AcPrincipal principal);

	AcGroup createGroupWithRole(String groupName, AcRoleType... types);

	void saveGroup(AcGroup group);

	void updateGroup(AcGroup group);

	void removeGroup(AcGroup group);

	void addGroupMember(AcGroup group, AcPrincipal principal) throws RecursiveGroupException;

	void deleteGroupMember(AcGroup group, AcPrincipal principal);

	// ====================================================================================================
	// ACTOR
	// ====================================================================================================

	AcActor findActorById(Long id);

	AcActor findActorByIdentityNo(String identityNo);

	List<AcActor> findActors(Integer offset, Integer limit);

	List<AcActor> findActors(AcActorType type, Integer offset, Integer limit);

	List<AcActor> findActors(String filter, Integer offset, Integer limit);

	List<AcActor> findActors(String filter, AcActorType type, Integer offset, Integer limit);

	Integer countActor();

	Integer countActor(AcActorType type);

	Integer countActor(String filter);

	Integer countActor(String filter, AcActorType type);

	// ====================================================================================================
	// STAFF
	// ====================================================================================================

	AcStaff findStaffById(Long id);

	AcStaff findStaffByStaffNo(String StaffNo);

	AcStaff findStaffByNricNo(String nricNo);

	List<AcStaff> findStaffs(Integer offset, Integer limit);

	List<AcStaff> findStaffs(String filter, Integer offset, Integer limit);

	Integer countStaff();

	Integer countStaff(String filter);

	boolean isStaffEmailExists(String email);

	boolean isStaffNoExists(String StaffNo);

	void saveStaff(AcStaff Staff);

	void updateStaff(AcStaff Staff);

	void deleteStaff(AcStaff Staff);

	void broadcastCreated(AcStaff Staff);

	void broadcastUpdated(AcStaff Staff);

	// ====================================================================================================
	// STUDENT
	// ====================================================================================================

	AcStudent findStudentById(Long id);

	AcStudent findStudentByStudentNo(String StudentNo);

	List<AcStudent> findStudents(Integer offset, Integer limit);

	List<AcStudent> findStudents(String filter, Integer offset, Integer limit);

	Integer countStudent();

	Integer countStudent(String filter);

	void saveStudent(AcStudent Student);

	void updateStudent(AcStudent Student);

	void deleteStudent(AcStudent Student);

	void broadcastCreated(AcStudent Student);

	void broadcastUpdated(AcStudent Student);
	
	// ====================================================================================================
	// SPONSOR
	// ====================================================================================================

	void saveSponsor(AcSponsor sponsor);
	
	AcSponsor findSponsorById(Long id);
	
	AcSponsor findBySponsorNo(String sponsorNo);

}
