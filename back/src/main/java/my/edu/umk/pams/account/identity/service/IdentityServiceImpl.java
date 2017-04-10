package my.edu.umk.pams.account.identity.service;

import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcFacultyCode;
import my.edu.umk.pams.account.common.model.AcProgramCode;
import my.edu.umk.pams.account.identity.dao.*;
import my.edu.umk.pams.account.identity.event.ApplicantUpdatedEvent;
import my.edu.umk.pams.account.identity.event.StaffCreatedEvent;
import my.edu.umk.pams.account.identity.event.StaffUpdatedEvent;
import my.edu.umk.pams.account.identity.event.StudentCreatedEvent;
import my.edu.umk.pams.account.identity.model.*;
import my.edu.umk.pams.account.security.service.SecurityService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author canang technologies
 * @since 1/30/14
 */
@Transactional
@Service("acIdentityService")
public class IdentityServiceImpl implements IdentityService {

    private static final String GROUP_ROOT = "GRP_ADMN";

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private SecurityService securityService;

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
    private AcStudentDao studentDao;
    
    @Autowired
    private AcSponsorDao sponsorDao;
    
    //====================================================================================================
    // PRINCIPAL
    //====================================================================================================

    @Override
    public AcPrincipal findPrincipalById(Long id) {
        return null;
    }

    @Override
    public AcPrincipal findPrincipalByName(String name) {
        return null;
    }

    @Override
    public List<AcPrincipal> findPrincipals(String filter, Integer offset, Integer limit) {
        return null;
    }

    @Override
    public Set<String> findSids(AcPrincipal principal) {
        return null;
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

    //====================================================================================================
    // USER
    //====================================================================================================

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
        userDao.update(user, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeUser(AcUser user) {
        userDao.remove(user, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void changePassword(AcUser user, String newPassword) {
        user.setPassword(newPassword);
        userDao.update(user, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    //====================================================================================================
    // GROUP
    //====================================================================================================

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
        for (AcGroup acGroup : groupSet) {
            groups.add(acGroup.getName());
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

    //====================================================================================================
    // ACTOR
    //====================================================================================================

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


    //====================================================================================================
    // STAFF
    //====================================================================================================

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


    //====================================================================================================
    // student
    //====================================================================================================

    @Override
    public AcStudent findStudentById(Long id) {
        return studentDao.findById(id);
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
	public List<AcStudent> findByCohort(AcCohortCode cohortCode) {
		return studentDao.findCohort(cohortCode);
	}
	
    @Override
    public List<AcSponsorship> findSponsorships(AcStudent student) {
        return studentDao.find(student);
    }
    
    @Override
    public List<AcSponsorship> findSponsorships(AcProgramCode programCode){
    	return studentDao.findSponsorships(programCode);
    }

	@Override
	public List<AcSponsorship> findSponsorships(AcFacultyCode facultyCode){
		return studentDao.findSponsorship(facultyCode);
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
    public Integer countSponsorship(AcStudent student) {
        return studentDao.countSponsorship(student);
    }

    @Override
    public void saveStudent(AcStudent student) {
        studentDao.save(student, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateStudent(AcStudent student) {
        studentDao.update(student, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
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
    public void broadcastCreated(AcStudent student) {
        StudentCreatedEvent event = new StudentCreatedEvent(student);
        applicationContext.publishEvent(event);
    }

    @Override
    public void broadcastUpdated(AcStudent student) {
        ApplicantUpdatedEvent event = new ApplicantUpdatedEvent(student);
        applicationContext.publishEvent(event);
    }
    
    //====================================================================================================
    // sponsor
    //====================================================================================================

    @Override
    public AcSponsor findSponsorById(Long id) {
        return sponsorDao.findById(id);
    }

    @Override
    public AcSponsor findSponsorBySponsorNo(String sponsorNo){
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

    //====================================================================================================
    // SPONSORSHIP
    //====================================================================================================
    
	@Override
	public AcSponsorship findSponsorshipById(Long id) {
		return sponsorDao.findSponsorshipById(id);
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



}
