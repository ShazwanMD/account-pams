package my.edu.umk.pams.account.identity.service;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcFacultyCode;
import my.edu.umk.pams.account.common.model.AcProgramCode;
import my.edu.umk.pams.account.identity.dao.AcActorDao;
import my.edu.umk.pams.account.identity.dao.AcGroupDao;
import my.edu.umk.pams.account.identity.dao.AcPrincipalDao;
import my.edu.umk.pams.account.identity.dao.AcSponsorDao;
import my.edu.umk.pams.account.identity.dao.AcSponsorshipDao;
import my.edu.umk.pams.account.identity.dao.AcStaffDao;
import my.edu.umk.pams.account.identity.dao.AcStudentDao;
import my.edu.umk.pams.account.identity.dao.AcUserDao;
import my.edu.umk.pams.account.identity.dao.RecursiveGroupException;
import my.edu.umk.pams.account.identity.event.ApplicantUpdatedEvent;
import my.edu.umk.pams.account.identity.event.StaffCreatedEvent;
import my.edu.umk.pams.account.identity.event.StaffUpdatedEvent;
import my.edu.umk.pams.account.identity.event.StudentCreatedEvent;
import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcActorType;
import my.edu.umk.pams.account.identity.model.AcCoverage;
import my.edu.umk.pams.account.identity.model.AcGroup;
import my.edu.umk.pams.account.identity.model.AcGroupImpl;
import my.edu.umk.pams.account.identity.model.AcGroupMember;
import my.edu.umk.pams.account.identity.model.AcGroupMemberImpl;
import my.edu.umk.pams.account.identity.model.AcGuardian;
import my.edu.umk.pams.account.identity.model.AcPrincipal;
import my.edu.umk.pams.account.identity.model.AcPrincipalRole;
import my.edu.umk.pams.account.identity.model.AcPrincipalRoleImpl;
import my.edu.umk.pams.account.identity.model.AcPrincipalType;
import my.edu.umk.pams.account.identity.model.AcRoleType;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcStaff;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.identity.model.AcUserImpl;
import my.edu.umk.pams.account.security.integration.AcAutoLoginToken;
import my.edu.umk.pams.account.security.integration.NonSerializableSecurityContext;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.account.system.service.SystemService;
import my.edu.umk.pams.account.system.service.SystemServiceImpl;

/**
 * @author canang technologies
 * @since 1/30/14
 */
@Transactional
@Service("inIdentityService")
public class IdentityServiceImpl implements IdentityService {

	private static final String GROUP_ROOT = "GRP_ADMN";
	private static final Logger LOG = LoggerFactory.getLogger(SystemServiceImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private AcPrincipalDao principalDao;

	@Autowired
	private AcUserDao userDao;

	@Autowired
	private AcGroupDao groupDao;

	@Autowired
	private AcActorDao actorDao;

	@Autowired
	private AcStaffDao staffDao;

	@Autowired
	private AcSponsorDao sponsorDao;

	@Autowired
	private AcSponsorshipDao sponsorshipDao;

	@Autowired
	private AcStudentDao studentDao;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private SystemService systemService;

	@Autowired
	private AuthenticationManager authenticationManager;

	// ====================================================================================================
	// PRINCIPAL
	// ====================================================================================================

	@Override
	public AcPrincipal findPrincipalById(Long id) {
		return principalDao.findById(id);
	}

	@Override
	public AcPrincipal findPrincipalByName(String name) {
		return principalDao.findByName(name);
	}

	@Override
	public List<AcPrincipal> findPrincipals(String filter, Integer offset, Integer limit) {
		return principalDao.find(filter, offset, limit);
	}

	@Override
	public Set<String> findSids(AcPrincipal principal) {
		Set<AcGroup> groups = null;
		Set<String> principals = new HashSet<String>();
		try {
			groups = groupDao.findEffectiveAsNative(principal);
		} catch (Exception e) {
			LOG.error("Error occured loading principals", e);
		} finally {
			if (null != groups) {
				for (AcGroup group : groups) {
					principals.add(group.getName());
				}
			}
			principals.add(principal.getName());
		}
		return principals;
	}

	@Override
	public Integer countPrincipal(String filter) {
		return principalDao.count(filter);
	}

	@Override
	public void addPrincipalRole(AcPrincipal principal, AcPrincipalRole principalRole) {
		principalDao.addRole(principal, principalRole, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void deletePrincipalRole(AcPrincipal principal, AcPrincipalRole principalRole) {
		principalDao.deleteRole(principal, principalRole, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// USER
	// ====================================================================================================

	@Override
	public AcUser findUserByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public AcUser findUserByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public AcUser findUserById(Long id) {
		return userDao.findById(id);
	}

	@Override
	public AcUser findUserByActor(AcActor actor) {
		return userDao.findByActor(actor);
	}

	@Override
	public List<AcUser> findUsers(Integer offset, Integer limit) {
		return userDao.find(offset, limit);
	}

	@Override
	public List<AcUser> findUsers(String filter, Integer offset, Integer limit) {
		return userDao.find(filter, offset, limit);
	}

	@Override
	public Integer countUser() {
		return userDao.count();
	}

	@Override
	public Integer countUser(String filter) {
		return userDao.count(filter);
	}

	@Override
	public boolean hasUser(AcActor actor) {
		return userDao.hasUser(actor);
	}

	@Override
	public boolean isUserExists(String username) {
		return userDao.isExists(username);
	}

	@Override
	public void saveUser(AcUser user) {
		userDao.save(user, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateUser(AcUser user) {
		SecurityContext sc = loginAsSystem();
		userDao.update(user, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
		logoutAsSystem(sc);
	}

	@Override
	public void removeUser(AcUser user) {
		userDao.remove(user, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void changePassword(AcUser user, String newPassword) {
		SecurityContext sc = loginAsSystem();
		user.setPassword(newPassword);
		userDao.update(user, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
		logoutAsSystem(sc);
	}

	// @Override
	// public void changeEmail(AcUser user, String newEmail) {
	// SecurityContext sc = loginAsSystem();
	// user.setEmail(newEmail);
	// userDao.update(user, securityService.getCurrentUser());
	// sessionFactory.getCurrentSession().flush();
	//
	// if (user == null) LOG.debug("UserB is null");
	// if (user.getEmail() == null) LOG.debug("Email is null");
	//
	// InEmailQueue email= new InEmailQueueImpl();
	// String subject = "Change Email";
	// String body = "Your Email has been changed to : " + newEmail +
	// ". Please Login to continue";
	// email.setTo(newEmail);
	// email.setSubject(subject);
	// email.setBody(body);
	// email.setCode("EQ/" + System.currentTimeMillis());
	// email.setQueueStatus(InEmailQueueStatus.QUEUED);
	// systemService.saveEmailQueue(email);
	// logoutAsSystem(sc);
	// }

	// ====================================================================================================
	// GROUP
	// ====================================================================================================

	@Override
	public AcGroup getRootGroup() {
		return groupDao.findByName(GROUP_ROOT);
	}

	@Override
	public AcGroup findGroupByName(String name) {
		return groupDao.findByName(name);
	}

	@Override
	public AcGroup findOrCreateGroupByName(String name) {
		AcGroup group = null;
		if (groupDao.isExists(name))
			group = groupDao.findByName(name);
		else {
			group = new AcGroupImpl();
			group.setName(name);
			group.setEnabled(true);
			group.setLocked(false);
			groupDao.save(group, securityService.getCurrentUser());
		}
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().refresh(group);
		return group;
	}

	@Override
	public AcGroup findGroupById(Long id) {
		return groupDao.findById(id);
	}

	@Override
	public AcGroup findGroupByUser(AcUser user) {
		return userDao.findGroupByUser(user);
	}

	@Override
	public List<AcGroup> findGroups(Integer offset, Integer limit) {
		return groupDao.find(offset, limit);
	}

	@Override
	public List<AcGroup> findImmediateGroups(AcPrincipal principal) {
		return groupDao.findImmediate(principal);
	}

	@Override
	public List<AcGroup> findImmediateGroups(AcPrincipal principal, Integer offset, Integer limit) {
		return groupDao.findImmediate(principal, offset, limit);
	}

	@Override
	public Set<AcGroup> findEffectiveGroups(AcPrincipal principal) {
		return groupDao.findEffectiveAsNative(principal);
	}

	@Override
	public Set<String> findEffectiveGroupsAsString(AcPrincipal principal) {
		Set<String> groups = new HashSet<>();
		Set<AcGroup> groupSet = groupDao.findEffectiveAsNative(principal);
		for (AcGroup inGroup : groupSet) {
			groups.add(inGroup.getName());
		}
		return groups;
	}

	@Override
	public List<AcGroup> findAvailableUserGroups(AcUser user) {
		return groupDao.findAvailableGroups(user);
	}

	@Override
	public List<AcPrincipal> findAvailableGroupMembers(AcGroup group) {
		return groupDao.findAvailableMembers(group);
	}

	@Override
	public List<AcPrincipal> findGroupMembers(AcGroup group) {
		return groupDao.findMembers(group);
	}

	@Override
	public List<AcPrincipal> findGroupMembers(AcGroup group, Integer offset, Integer limit) {
		return groupDao.findMembers(group, offset, limit);
	}

	@Override
	public Integer countGroup() {
		return groupDao.count();
	}

	@Override
	public Integer countGroupMember(AcGroup group) {
		return groupDao.countMember(group);
	}

	@Override
	public boolean isGroupExists(String name) {
		return groupDao.isExists(name);
	}

	@Override
	public boolean hasMembership(AcGroup group, AcPrincipal principal) {
		return groupDao.hasMembership(group, principal);
	}

	@Override
	public AcGroup createGroupWithRole(String name, AcRoleType... types) {
		AcGroup group = new AcGroupImpl();
		group.setName(name);
		group.setEnabled(true);
		group.setLocked(false);
		groupDao.save(group, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().refresh(group);

		for (AcRoleType type : types) {
			AcPrincipalRole role = new AcPrincipalRoleImpl();
			role.setRole(type);
			principalDao.addRole(group, role, securityService.getCurrentUser());
			sessionFactory.getCurrentSession().flush();
		}
		sessionFactory.getCurrentSession().refresh(group);
		return group;
	}

	@Override
	public void saveGroup(AcGroup group) {
		groupDao.save(group, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateGroup(AcGroup group) {
		groupDao.update(group, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeGroup(AcGroup group) {
		groupDao.remove(group, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void addGroupMember(AcGroup group, AcPrincipal principal) throws RecursiveGroupException {
		groupDao.addMember(group, principal, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void deleteGroupMember(AcGroup group, AcPrincipal principal) {
		groupDao.deleteMember(group, principal);
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// ACTOR
	// ====================================================================================================

	@Override
	public AcActor findActorById(Long id) {
		return actorDao.findById(id);
	}

	@Override
	public AcActor findActorByIdentityNo(String identityNo) {
		return actorDao.findByIdentityNo(identityNo);
	}

	@Override
	public List<AcActor> findActors(Integer offset, Integer limit) {
		return actorDao.find(offset, limit);
	}

	@Override
	public List<AcActor> findActors(AcActorType type, Integer offset, Integer limit) {
		return actorDao.find(type, offset, limit);
	}

	@Override
	public List<AcActor> findActors(String filter, Integer offset, Integer limit) {
		return actorDao.find(filter, offset, limit);
	}

	@Override
	public List<AcActor> findActors(String filter, AcActorType type, Integer offset, Integer limit) {
		return actorDao.find(filter, type, offset, limit);
	}

	@Override
	public Integer countActor() {
		return actorDao.count();
	}

	@Override
	public Integer countActor(AcActorType type) {
		return actorDao.count(type);
	}

	@Override
	public Integer countActor(String filter) {
		return actorDao.count(filter);
	}

	@Override
	public Integer countActor(String filter, AcActorType type) {
		return actorDao.count(filter, type);
	}

	@Override
	public boolean isActorEmailExists(String email) {
		return actorDao.isEmailExists(email);
	}

	// ====================================================================================================
	// STAFF
	// ====================================================================================================

	@Override
	public AcStaff findStaffById(Long id) {
		return staffDao.findById(id);
	}

	@Override
	public AcStaff findStaffByStaffNo(String StaffNo) {
		return staffDao.findByStaffNo(StaffNo);
	}

	@Override
	public AcStaff findStaffByNricNo(String nricNo) {
		return staffDao.findByStaffNo(nricNo);
	}

	@Override
	public List<AcStaff> findStaffs(Integer offset, Integer limit) {
		return staffDao.find(offset, limit);
	}

	@Override
	public List<AcStaff> findStaffs(String filter, Integer offset, Integer limit) {
		return staffDao.find(filter, offset, limit);
	}

	@Override
	public Integer countStaff() {
		return staffDao.count();
	}

	@Override
	public Integer countStaff(String filter) {
		return staffDao.count(filter);
	}

	@Override
	public boolean isStaffEmailExists(String email) {
		return staffDao.isEmailExists(email);
	}

	@Override
	public boolean isStaffNoExists(String staffNo) {
		return staffDao.isExists(staffNo);
	}

	@Override
	public void saveStaff(AcStaff staff) {
		staffDao.save(staff, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void saveStaffIMSNonAcademicActive(AcStaff staff) {
		staffDao.save(staff, securityService.getCurrentUser());
		LOG.info("IdentityService Save Non Academic Active Staff From IMS");

		LOG.debug("Staff Email In Service:{}", staff.getEmail());
		// User
		AcUser user = new AcUserImpl();
		user.setActor(staff);
		user.setEmail(staff.getEmail());
		user.setUsername(staff.getEmail());
		user.setPassword(staff.getStaffNo());
		user.setRealName(staff.getName());
		// Username Principal
		user.setName(staff.getEmail());
		user.setEnabled(true);
		user.setLocked(true);
		user.setPrincipalType(AcPrincipalType.USER);
		saveUser(user);

		// Principal
		AcPrincipal principal = findPrincipalByName(staff.getEmail());
		if (staff.getFacultyCode().getCode().equals("A10") && staff.getStaffCategory().equals("A")) {
			LOG.info("If Faculty A10 MGSEB && Category A");

			// Principal Role
			AcPrincipalRole role = new AcPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(AcRoleType.ROLE_PTJ);
			addPrincipalRole(principal, role);

			try {
				// Group
				AcGroup group = findGroupByName("GRP_PGW_PTJ_MGSEB");
				// GroupMember
				AcGroupMember member = new AcGroupMemberImpl();
				member.setGroup(group);
				member.setPrincipal(principal);
				addGroupMember(group, principal);
			} catch (RecursiveGroupException e) {

				e.printStackTrace();
			}
		} else if (staff.getFacultyCode().getCode().equals("A10") && staff.getStaffCategory().equals("B")) {
			LOG.info("If Faculty A10 MGSEB && Category B");

			// Principal Role
			AcPrincipalRole role = new AcPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(AcRoleType.ROLE_PTJ);
			addPrincipalRole(principal, role);

			try {
				// Group
				AcGroup group = findGroupByName("GRP_PEN_PGW_PTJ_MGSEB");
				// GroupMember
				AcGroupMember member = new AcGroupMemberImpl();
				member.setGroup(group);
				member.setPrincipal(principal);
				addGroupMember(group, principal);
			} catch (RecursiveGroupException e) {

				e.printStackTrace();
			}
		} else if (staff.getFacultyCode().getCode().equals("A10")) {
			LOG.info("If Faculty A10 MGSEB Only");

			// Principal Role
			AcPrincipalRole role = new AcPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(AcRoleType.ROLE_PTJ);
			addPrincipalRole(principal, role);

			try {
				// Group
				AcGroup group = findGroupByName("GRP_KRN_PTJ_MGSEB");
				// GroupMember
				AcGroupMember member = new AcGroupMemberImpl();
				member.setGroup(group);
				member.setPrincipal(principal);
				addGroupMember(group, principal);
			} catch (RecursiveGroupException e) {

				e.printStackTrace();
			}
		} else if (staff.getFacultyCode().getCode().equals("A09") && staff.getStaffCategory().equals("A")) {
			LOG.info("If Faculty A09 CPS && Category A");

			// Principal Role
			AcPrincipalRole role = new AcPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(AcRoleType.ROLE_PTJ);
			addPrincipalRole(principal, role);

			try {
				// Group
				AcGroup group = findGroupByName("GRP_PGW_PTJ_CPS");
				// GroupMember
				AcGroupMember member = new AcGroupMemberImpl();
				member.setGroup(group);
				member.setPrincipal(principal);
				addGroupMember(group, principal);
			} catch (RecursiveGroupException e) {

				e.printStackTrace();
			}
		} else if (staff.getFacultyCode().getCode().equals("A09") && staff.getStaffCategory().equals("B")) {
			LOG.info("If Faculty A09 CPS && Category A");

			// Principal Role
			AcPrincipalRole role = new AcPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(AcRoleType.ROLE_PTJ);
			addPrincipalRole(principal, role);

			try {
				// Group
				AcGroup group = findGroupByName("GRP_PEN_PGW_PTJ_CPS");
				// GroupMember
				AcGroupMember member = new AcGroupMemberImpl();
				member.setGroup(group);
				member.setPrincipal(principal);
				addGroupMember(group, principal);
			} catch (RecursiveGroupException e) {

				e.printStackTrace();
			}
		} else if (staff.getFacultyCode().getCode().equals("A09")) {
			LOG.info("If Faculty A09 CPS Only");

			// Principal Role
			AcPrincipalRole role = new AcPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(AcRoleType.ROLE_ADMINISTRATOR);
			addPrincipalRole(principal, role);

			try {
				// Group
				AcGroup group = findGroupByName("GRP_KRN_PTJ_CPS");
				// GroupMember
				AcGroupMember member = new AcGroupMemberImpl();
				member.setGroup(group);
				member.setPrincipal(principal);
				addGroupMember(group, principal);
			} catch (RecursiveGroupException e) {

				e.printStackTrace();
			}
		} else if (staff.getFacultyCode().getCode().equals("B03") && staff.getStaffCategory().equals("A")) {
			LOG.info("If Faculty B03 && Category A");

			// Principal Role
			AcPrincipalRole role = new AcPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(AcRoleType.ROLE_BRSY);
			addPrincipalRole(principal, role);

			try {
				// Group
				AcGroup group = findGroupByName("GRP_PGW_ADM_BEND");
				// GroupMember
				AcGroupMember member = new AcGroupMemberImpl();
				member.setGroup(group);
				member.setPrincipal(principal);
				addGroupMember(group, principal);
			} catch (RecursiveGroupException e) {

				e.printStackTrace();
			}
		} else if (staff.getFacultyCode().getCode().equals("B03") && staff.getStaffCategory().equals("B")) {
			LOG.info("If Faculty B03 && Category B");

			// Principal Role
			AcPrincipalRole role = new AcPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(AcRoleType.ROLE_BRSY);
			addPrincipalRole(principal, role);

			try {
				// Group
				AcGroup group = findGroupByName("GRP_PEN_PGW_ADM_BEND");
				// GroupMember
				AcGroupMember member = new AcGroupMemberImpl();
				member.setGroup(group);
				member.setPrincipal(principal);
				addGroupMember(group, principal);
			} catch (RecursiveGroupException e) {

				e.printStackTrace();
			}
		} else if (staff.getFacultyCode().getCode().equals("B03")) {
			LOG.info("If Faculty B03");

			// Principal Role
			AcPrincipalRole role = new AcPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(AcRoleType.ROLE_BRSY);
			addPrincipalRole(principal, role);

			try {
				// Group
				AcGroup group = findGroupByName("GRP_KRN_ADM_BEND");
				// GroupMember
				AcGroupMember member = new AcGroupMemberImpl();
				member.setGroup(group);
				member.setPrincipal(principal);
				addGroupMember(group, principal);
			} catch (RecursiveGroupException e) {

				e.printStackTrace();
			}
		} else {
			LOG.debug("Others");
		}
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void saveStaffIMSNonAcademicInActive(AcStaff staff) {
		staffDao.save(staff, securityService.getCurrentUser());
		LOG.info("IdentityService Save Non Academic InActive Staff From IMS");

		LOG.debug("Staff Email In Service:{}", staff.getEmail());

		if (isUserExists(staff.getEmail())) {

			AcUser userInActive = findUserByUsername(staff.getEmail());
			userInActive.setEnabled(false);
			userInActive.setLocked(false);
			updateUser(userInActive);

		} else {
			LOG.info("User Not Exists");
		}

		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void saveStaffNonAcdmcActv(AcStaff staff) {

		AcUser user = new AcUserImpl();
		user.setUsername(staff.getEmail());
		user.setEmail(staff.getEmail());
		user.setRealName(staff.getName());
		user.setPassword(staff.getStaffNo());
		user.setEnabled(false);
		user.setLocked(true);

		identityService.saveUser(user);

		staffDao.save(staff, securityService.getCurrentUser());
		try {
			if (staff.getStaffCategory().equals("A")) {
				AcGroup group = identityService.findGroupByName("GRP_PGW_PTJ_" + staff.getStaffDeptCode());
				AcPrincipal principal = identityService.findPrincipalByName(user.getName());
				System.out.println("group :" + group);
				identityService.addGroupMember(group, principal);
			} else {
				AcGroup group = identityService.findGroupByName("GRP_KRN_PTJ_" + staff.getStaffDeptCode());
				AcPrincipal principal = identityService.findPrincipalByName(user.getName());
				System.out.println("group :" + group);
				identityService.addGroupMember(group, principal);
			}
		} catch (RecursiveGroupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateStaff(AcStaff staff) {
		staffDao.update(staff, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void deleteStaff(AcStaff staff) {
		staffDao.delete(staff, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void broadcastCreated(AcStaff staff) {
		StaffCreatedEvent event = new StaffCreatedEvent(staff);
		applicationContext.publishEvent(event);
	}

	@Override
	public void broadcastUpdated(AcStaff staff) {
		StaffUpdatedEvent event = new StaffUpdatedEvent(staff);
		applicationContext.publishEvent(event);
	}

	// ====================================================================================================
	// STUDENT
	// ====================================================================================================

	@Override
	public AcStudent findStudentById(Long id) {
		return studentDao.findById(id);
	}

	@Override
	public AcStudent findStudentByEmail(String email) {
		return null; // todo: studentDao.findByEmail(email);
	}

	@Override
	public AcStudent findStudentByMatricNo(String matricNo) {
		return studentDao.findByMatricNo(matricNo);
	}

	@Override
	public List<AcStudent> findStudents(Integer offset, Integer limit) {
		return studentDao.find(offset, limit);
	}

	@Override
	public List<AcStudent> findStudents(String filter, Integer offset, Integer limit) {
		return studentDao.find(filter, offset, limit);
	}

	@Override
	public Integer countStudent() {
		return studentDao.count();
	}

	@Override
	public Integer countStudent(String filter) {
		return studentDao.count(filter);
	}

	@Override
	public void saveStudent(AcStudent applicant) {
		studentDao.save(applicant, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateStudent(AcStudent applicant) {
		SecurityContext sc = loginAsSystem();
		studentDao.update(applicant, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
		logoutAsSystem(sc);
	}

	@Override
	public List<AcStudent> findStudentByCohortCode(AcCohortCode cohortCode) {
		return studentDao.findByCohortCode(cohortCode);
	}

	@Override
	public List<AcStudent> findStudentByFacultyCode(AcFacultyCode facultyCode) {
		return studentDao.findByFacultyCode(facultyCode);
	}

	@Override
	public List<AcStudent> findStudentBySponsor(AcSponsor sponsor) {
		return studentDao.findBySponsor(sponsor);
	}

	@Override
	public List<AcSponsorship> findSponsorships(AcStudent student) {
		return studentDao.find(student);
	}

	@Override
	public List<AcSponsorship> findSponsorships(AcProgramCode programCode) {
		return studentDao.findSponsorships(programCode);
	}

	@Override
	public List<AcSponsorship> findSponsorships(AcFacultyCode facultyCode) {
		return studentDao.findSponsorship(facultyCode);
	}

	@Override
	public Integer countSponsorship(AcStudent student) {
		return studentDao.countSponsorship(student);
	}

	@Override
	public void deleteStudent(AcStudent student) {
		studentDao.delete(student, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void addSponsorship(AcStudent student, AcSponsorship sponsorship) {
		studentDao.addSponsorship(student, sponsorship, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeSponsorship(AcStudent student, AcSponsorship sponsorship) {
		studentDao.removeSponsorship(student, sponsorship, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void addGuardian(AcStudent student, AcGuardian guardian) {
		studentDao.addGuardian(student, guardian, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeGuardian(AcStudent student, AcGuardian guardian) {
		studentDao.removeGuardian(student, guardian, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void broadcastCreated(AcStudent student) {
		StudentCreatedEvent event = new StudentCreatedEvent(student);
		applicationContext.publishEvent(event);
	}

	@Override
	public void broadcastUpdated(AcStudent student) {
		ApplicantUpdatedEvent event = new ApplicantUpdatedEvent(student);
		applicationContext.publishEvent(event);
	}

	// ====================================================================================================
	// sponsor
	// ====================================================================================================

	@Override
	public AcSponsor findSponsorById(Long id) {
		return sponsorDao.findById(id);
	}

	@Override
	public AcSponsor findSponsorBySponsorNo(String sponsorNo) {
		return sponsorDao.findBySponsorNo(sponsorNo);
	}

	@Override
	public AcCoverage findCoverageById(Long id) {
		return sponsorDao.findCoverageById(id);
	}

	@Override
	public List<AcSponsor> findSponsors(Integer offset, Integer limit) {
		return sponsorDao.find(offset, limit);
	}

	@Override
	public List<AcSponsor> findSponsors(String filter, Integer offset, Integer limit) {
		return sponsorDao.find(filter, offset, limit);
	}

	@Override
	public List<AcSponsorship> findSponsorships(AcSponsor sponsor) {
		return sponsorDao.findSponsorships(sponsor);
	}

	@Override
	public List<AcCoverage> findCoverages(AcSponsor sponsor) {
		return sponsorDao.findCoverages(sponsor);
	}

	@Override
	public Integer countCoverage(AcSponsor sponsor) {
		return sponsorDao.countCoverage(sponsor);
	}

	@Override
	public Integer countSponsorship(AcSponsor sponsor) {
		return sponsorDao.countSponsorship(sponsor);
	}

	@Override
	public boolean hasCoverage(AcSponsor sponsor) {
		return sponsorDao.hasCoverage(sponsor);
	}

	@Override
	public void saveSponsor(AcSponsor sponsor) {
		sponsorDao.save(sponsor, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void addCoverage(AcSponsor sponsor, AcCoverage coverage) {
		sponsorDao.addCoverage(sponsor, coverage, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void deleteCoverage(AcSponsor sponsor, AcCoverage coverage) {
		sponsorDao.deleteCoverage(sponsor, coverage, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// SPONSORSHIP
	// ====================================================================================================

	@Override
	public AcSponsorship findSponsorshipById(Long id) {
		return sponsorDao.findSponsorshipById(id);
	}

	@Override
	public List<AcSponsorship> findSponsorships(Integer offset, Integer limit) {
		return sponsorshipDao.find(offset, limit);
	}

	@Override
	public void saveSponsorship(AcSponsorship sponsorship) {
		sponsorshipDao.save(sponsorship, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public List<AcSponsorship> findSponsorships(AcAccount account) {
		return sponsorshipDao.find(account);
	}

	@Override
	public void updateSponsorship(AcSponsorship sponsorship) {
		sponsorshipDao.save(sponsorship, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public boolean hasSponsorship(AcStudent student) {
		return studentDao.hasSponsorship(student);
	}

	@Override
	public boolean hasSponsorship(AcSponsor sponsor) {
		return sponsorDao.hasSponsorship(sponsor);
	}

	@Override
	public void addSponsorship(AcSponsor sponsor, AcSponsorship sponsorship) {
		sponsorDao.addSponsorship(sponsor, sponsorship, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeSponsorship(AcSponsor sponsor, AcSponsorship sponsorship) {
		sponsorDao.removeSponsorship(sponsor, sponsorship, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// PRIVATE METHODS
	// ====================================================================================================

	private SecurityContext loginAsSystem() {
		SecurityContext savedCtx = SecurityContextHolder.getContext();
		AcAutoLoginToken authenticationToken = new AcAutoLoginToken("system");
		Authentication authed = authenticationManager.authenticate(authenticationToken);
		SecurityContext system = new NonSerializableSecurityContext();
		system.setAuthentication(authed);
		SecurityContextHolder.setContext(system);
		return savedCtx;
	}

	private void logoutAsSystem(SecurityContext context) {
		SecurityContextHolder.setContext(context);

	}
}
