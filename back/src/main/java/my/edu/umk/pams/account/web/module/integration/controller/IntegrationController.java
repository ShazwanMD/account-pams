package my.edu.umk.pams.account.web.module.integration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.edu.umk.pams.academic.identity.model.AdStaff;
import my.edu.umk.pams.academic.identity.model.AdStaffImpl;
import my.edu.umk.pams.account.AccountConstants;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAcademicSessionImpl;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeImpl;
import my.edu.umk.pams.account.account.model.AcAccountChargeType;
import my.edu.umk.pams.account.account.model.AcAccountImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcCohortCodeImpl;
import my.edu.umk.pams.account.common.model.AcFacultyCode;
import my.edu.umk.pams.account.common.model.AcFacultyCodeImpl;
import my.edu.umk.pams.account.common.model.AcProgramCode;
import my.edu.umk.pams.account.common.model.AcProgramCodeImpl;
import my.edu.umk.pams.account.common.model.AcResidencyCode;
import my.edu.umk.pams.account.common.model.AcResidencyCodeImpl;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.identity.dao.RecursiveGroupException;
import my.edu.umk.pams.account.identity.model.AcActorType;
import my.edu.umk.pams.account.identity.model.AcGroup;
import my.edu.umk.pams.account.identity.model.AcGroupMember;
import my.edu.umk.pams.account.identity.model.AcGroupMemberImpl;
import my.edu.umk.pams.account.identity.model.AcGuardian;
import my.edu.umk.pams.account.identity.model.AcGuardianImpl;
import my.edu.umk.pams.account.identity.model.AcGuardianType;
import my.edu.umk.pams.account.identity.model.AcPrincipal;
import my.edu.umk.pams.account.identity.model.AcPrincipalRole;
import my.edu.umk.pams.account.identity.model.AcPrincipalRoleImpl;
import my.edu.umk.pams.account.identity.model.AcPrincipalType;
import my.edu.umk.pams.account.identity.model.AcRoleType;
import my.edu.umk.pams.account.identity.model.AcStaff;
import my.edu.umk.pams.account.identity.model.AcStaffImpl;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcStudentImpl;
import my.edu.umk.pams.account.identity.model.AcStudentStatus;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.identity.model.AcUserImpl;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.security.integration.AcAutoLoginToken;
import my.edu.umk.pams.account.security.integration.NonSerializableSecurityContext;
import my.edu.umk.pams.account.system.service.SystemService;
import my.edu.umk.pams.connector.payload.AcademicSessionCodePayload;
import my.edu.umk.pams.connector.payload.AdmissionPayload;
import my.edu.umk.pams.connector.payload.CandidatePayload;
import my.edu.umk.pams.connector.payload.CohortCodePayload;
import my.edu.umk.pams.connector.payload.FacultyCodePayload;
import my.edu.umk.pams.connector.payload.GuardianPayload;
import my.edu.umk.pams.connector.payload.IntakePayload;
import my.edu.umk.pams.connector.payload.IntakeSessionCodePayload;
import my.edu.umk.pams.connector.payload.MinAmountPayload;
import my.edu.umk.pams.connector.payload.ProgramCodePayload;
import my.edu.umk.pams.connector.payload.StaffPayload;

@Transactional
@RestController
@RequestMapping("/api/integration")
public class IntegrationController {

	private static final Logger LOG = LoggerFactory.getLogger(IntegrationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CommonService commonService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private BillingService billingService;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private SystemService systemService;

	// ====================================================================================================
	// CODES
	// ====================================================================================================

	// ====================================================================================================
	// IMS STAFF
	// ====================================================================================================
	@RequestMapping(value = "/staffs/nonAcademicActive", method = RequestMethod.POST)
	public ResponseEntity<String> saveStaff(@RequestBody List<StaffPayload> payloads) {
		SecurityContext ctx = loginAsSystem();

		for (StaffPayload payload : payloads) {

			// find if staf exist
			AcStaff isExist = identityService.findStaffByStaffNo(payload.getStaffId());

			if (isExist.getIdentityNo().isEmpty()) {
				AcStaff staff = new AcStaffImpl();
				staff.setIdentityNo(payload.getStaffId());
				staff.setName(payload.getStaffName());
				identityService.saveStaffNonAcdmcActv(staff);
			}
			else
			{
				AcStaff staff = new AcStaffImpl();
				staff.setIdentityNo(payload.getStaffId());
				staff.setName(payload.getStaffName());
				identityService.updateStaff(staff);
			}
		}

		logoutAsSystem(ctx);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@RequestMapping(value = "/cohortCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveCohortCode(@RequestBody CohortCodePayload payload) {
		LOG.info("Incoming Cohort Code Payload");
		SecurityContext ctx = loginAsSystem();

		AcProgramCode programCode = commonService.findProgramCodeByCode(payload.getProgramCode().getCode());

		AcCohortCode cohortCode = new AcCohortCodeImpl();
		cohortCode.setCode(payload.getCode());
		cohortCode.setDescription(payload.getDescription());
		cohortCode.setProgramCode(programCode);
		commonService.saveCohortCode(cohortCode);
		LOG.info("Received Cohort Code Payload");
		logoutAsSystem(ctx);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@RequestMapping(value = "/programCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveProgramCode(@RequestBody ProgramCodePayload payload) {
		LOG.info("Start Receive program code");
		SecurityContext ctx = loginAsSystem();

		AcProgramCode programCode = new AcProgramCodeImpl();
		programCode.setCode(payload.getCode());
		programCode.setDescription(payload.getDescriptionMs());
		programCode.setFacultyCode(commonService.findFacultyCodeByCode(payload.getFacultyCode().getCode()));
		commonService.saveProgramCode(programCode);

		LOG.info("Finish Receive program code");
		logoutAsSystem(ctx);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@RequestMapping(value = "/facultyCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveFacultyCode(@RequestBody FacultyCodePayload payload) {
		LOG.info("Start Receive faculty code");
		SecurityContext ctx = loginAsSystem();

		AcFacultyCode facultyCode = new AcFacultyCodeImpl();
		facultyCode.setCode(payload.getCode());
		facultyCode.setDescription(payload.getDescription());
		commonService.saveFacultyCode(facultyCode);

		LOG.info("Finish Reveive faculty code");

		logoutAsSystem(ctx);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@RequestMapping(value = "/guardians", method = RequestMethod.POST)
	public ResponseEntity<String> saveGuardian(@RequestBody GuardianPayload payload) {
		LOG.info("incoming Guardian");
		SecurityContext ctx = loginAsSystem();

		AcStudent student = identityService.findStudentByMatricNo(payload.getStudentPayload().getMatricNo());
		LOG.debug("Student Guardian:{}", student.getIdentityNo());

		AcGuardian guardian = new AcGuardianImpl();
		guardian.setIdentityNo(payload.getIdentityNo());
		guardian.setName(payload.getName());
		guardian.setPhone(payload.getPhone());
		guardian.setStudent(student);
		guardian.setType(AcGuardianType.get(payload.getType().ordinal()));

		identityService.addGuardian(student, guardian);
		LOG.debug("Student Guardian:{}", guardian.getId());
		LOG.info("Finish Receive Guardian");

		logoutAsSystem(ctx);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	// ====================================================================================================
	// ADMISSION & ENROLLMENT
	// ====================================================================================================

	@RequestMapping(value = "/admissions", method = RequestMethod.POST)
	public ResponseEntity<String> saveAdmission(@RequestBody AdmissionPayload payload) {
		LOG.info("incoming admission");
		SecurityContext ctx = loginAsSystem();

		// this is admission
		AcAcademicSession academicSession = accountService
				.findAcademicSessionByCode(payload.getAcademicSession().getCode());
		LOG.debug("AcademicSession:{}", academicSession.getCode());

		AcStudent student = identityService.findStudentByMatricNo(payload.getStudent().getMatricNo());
		AcAccount account = accountService.findAccountByActor(student);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("academicSession", accountService.findCurrentAcademicSession());
		String referenceNo = systemService.generateFormattedReferenceNo(AccountConstants.ACCOUNT_CHARGE_REFRENCE_NO,
				map);
		AcAccountCharge charge = new AcAccountChargeImpl();
		charge.setChargeType(AcAccountChargeType.ADMISSION);
		charge.setAccount(account);
		charge.setSession(academicSession);
		charge.setChargeDate(new Date());
		charge.setReferenceNo(referenceNo);
		charge.setSourceNo("SN"); // todo:
		charge.setDescription("DESCRIPTION"); // todo:
		charge.setAmount(BigDecimal.ZERO); // todo
		charge.setOrdinal(payload.getOrdinal());
		if (null != payload.getCohortCode())
			charge.setCohortCode(commonService.findCohortCodeByCode(payload.getCohortCode().getCode()));
		if (null != payload.getStudyMode())
			charge.setStudyMode(commonService.findStudyModeByCode(payload.getStudyMode().getCode()));
		accountService.addAccountCharge(account, charge);

		LOG.debug("AfterAddAccountCharge:{}", charge.getAccount().getActor().getIdentityNo());
		logoutAsSystem(ctx);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	// ====================================================================================================
	// COHORT
	// ====================================================================================================
	@RequestMapping(value = "/intakes", method = RequestMethod.POST)
	public ResponseEntity<String> ingestIntake(@RequestBody IntakePayload payload) {
		SecurityContext ctx = loginAsSystem();

		IntakeSessionCodePayload intakeSession = payload.getIntakeSession();
		List<ProgramCodePayload> offeredProgramCodes = payload.getOfferedProgramCodes();

		for (ProgramCodePayload offeredProgramCode : offeredProgramCodes) {

			String cohortCode = offeredProgramCode.getFacultyCode().getCode() + "-"
					+ offeredProgramCode.getProgramLevel().getCode() + "-" + offeredProgramCode.getCode() + "-CHRT"
					+ "-" + intakeSession.getCode();

			AcCohortCode cohort = new AcCohortCodeImpl();
			cohort.setCode(cohortCode);
			cohort.setDescription(cohortCode);
			cohort.setProgramCode(commonService.findProgramCodeByCode(offeredProgramCode.getCode()));
			commonService.saveCohortCode(cohort);
		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	// ====================================================================================================
	// ACADEMIC SESSIONS
	// ====================================================================================================
	@RequestMapping(value = "/sessions", method = RequestMethod.POST)
	public ResponseEntity<String> saveAcademicSession(@RequestBody AcademicSessionCodePayload payload) {
		SecurityContext ctx = loginAsSystem();

		if (accountService.isAcademicSessionCodeExists(payload.getCode())) {

			System.out.println("Duplicate session:" + payload.getCode());
			return new ResponseEntity<String>("Duplicate", HttpStatus.OK);
		} else {

			System.out.println("Update previous session TRUE to FALSE first then save");

			AcAcademicSession academicSession1 = accountService.findCurrentAcademicSession();
			if (academicSession1.isCurrent() == true) {
				System.out.println("Check state TRUE" + academicSession1.isCurrent());
				academicSession1.setCurrent(false);
				accountService.updateAcademicSession(academicSession1);
				System.out.println("Updated session:" + academicSession1);

				AcAcademicSession academicSession = new AcAcademicSessionImpl();
				academicSession.setCode(payload.getCode());
				academicSession.setCurrent(true);
				academicSession.setDescription(payload.getDescription());
				academicSession.setStartDate(payload.getStartDate());
				academicSession.setEndDate(payload.getEndDate());
				accountService.saveAcademicSession(academicSession);
				LOG.info("Finish save academic session");
			}
		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	// ====================================================================================================
	// CANDIDATE
	// incoming from intake
	// ====================================================================================================
	@RequestMapping(value = "/candidates", method = RequestMethod.POST)
	public ResponseEntity<String> saveCandidate(@RequestBody CandidatePayload payload) {
		SecurityContext ctx = loginAsSystem();

		LOG.info("Start Receive Candidate");

		if (commonService.findResidencyCodeByCode(payload.getNationalityCode().getCode()) == null) {
			AcResidencyCode residencyCode = new AcResidencyCodeImpl();
			residencyCode.setCode(payload.getNationalityCode().getCode());
			residencyCode.setDescription(payload.getNationalityCode().getDescriptionEn());
			commonService.saveResidencyCode(residencyCode);
		}

		if (commonService.findCohortCodeByCode(payload.getCohortCode()) == null) {

			AcCohortCode cohortCode = new AcCohortCodeImpl();
			cohortCode.setCode(payload.getCohortCode());
			cohortCode.setDescription(payload.getCohortCode());
			cohortCode.setProgramCode(commonService.findProgramCodeByCode(payload.getProgramCode()));
			commonService.saveCohortCode(cohortCode);
		}

		// student infos
		AcStudent student = new AcStudentImpl();
		student.setMatricNo(payload.getMatricNo());
		student.setName(payload.getName());
		student.setEmail(payload.getEmail()); // todo: email university?
		student.setFax(payload.getFax());
		student.setPhone(payload.getPhone());
		student.setMobile(payload.getMobile());

		student.setStudentStatus(AcStudentStatus.ACTIVE);
		student.setCohortCode(commonService.findCohortCodeByCode(payload.getCohortCode()));
		student.setResidencyCode(commonService.findResidencyCodeByCode(payload.getNationalityCode().getCode()));
		identityService.saveStudent(student);

		// account
		AcAccount account = new AcAccountImpl();
		account.setActor(student);
		account.setCode(student.getMatricNo());
		account.setDescription("ACCOUNT;STUDENT;" + student.getMatricNo());
		account.setBalance(BigDecimal.ZERO);
		accountService.saveAccount(account);

		// save user
		AcUser user = new AcUserImpl();
		user.setUsername(payload.getEmail());
		user.setPassword(payload.getUserPayload().getPassword());
		user.setRealName(payload.getName());
		user.setEmail(payload.getEmail());
		user.setActor(student);
		identityService.saveUser(user);

		LOG.info("Finish Receive Candidates");
		logoutAsSystem(ctx);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	// ====================================================================================================
	// STAFF
	// ====================================================================================================
	@RequestMapping(value = "/staff", method = RequestMethod.POST)
	public ResponseEntity<String> saveStaff(@RequestBody List<StaffPayload> staffPayload)
			throws RecursiveGroupException {
		SecurityContext ctx = loginAsSystem();

		LOG.info("Start Receive Staff From IMS");
		for (StaffPayload payload : staffPayload) {

			LOG.debug("Staff Staff_No:{}", payload.getStaffId());
			LOG.debug("Staff Name:{}", payload.getStaffName());

			AcStaff staff = new AcStaffImpl();
			staff.setIdentityNo(payload.getStaffId());
			staff.setName(payload.getStaffName());
			staff.setActorType(AcActorType.STAFF);
			identityService.saveStaff(staff);

			// User
			AcUser user = new AcUserImpl();
			user.setActor(staff);
			user.setEmail(payload.getStaffEmail());
			user.setUsername(payload.getStaffEmail());
			user.setPassword("ABC123");
			user.setRealName(payload.getStaffName());
			identityService.saveUser(user);

			// Principal
			AcPrincipal principal = identityService.findPrincipalByName(payload.getStaffEmail());
			principal.setName(payload.getStaffEmail());
			principal.setPrincipalType(AcPrincipalType.USER);
			principal.setEnabled(true);
			principal.setLocked(true);

			// Principal Role
			AcPrincipalRole role = new AcPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(AcRoleType.ROLE_USER);
			identityService.addPrincipalRole(principal, role);

			// Group
			AcGroup group = identityService.findGroupByName("GRP_STDN");
			// GroupMember
			AcGroupMember member = new AcGroupMemberImpl();
			member.setGroup(group);
			member.setPrincipal(principal);
			identityService.addGroupMember(group, principal);

		}
		LOG.info("Finish Receive Staff From IMS");

		logoutAsSystem(ctx);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	// ====================================================================================================
	// MIN AMOUNT
	// incoming from academic
	// ====================================================================================================

	@RequestMapping(value = "/minAmounts", method = RequestMethod.POST)
	public ResponseEntity<String> saveMinimumAmount(@RequestBody MinAmountPayload payload) {

		SecurityContext ctx = loginAsSystem();

		LOG.info("Start Receive AcAccountCharge");

		AcAccount account = accountService.findAccountByCode(payload.getStudentPayload().getMatricNo());
		LOG.debug("Student MatricNO:{}", account.getCode());

		AcStudent student = identityService.findStudentByMatricNo(payload.getStudentPayload().getMatricNo());
		LOG.debug("Student Name:{}", student.getName());

		AcCohortCode cohort = student.getCohortCode();

		AcAccountCharge accountCharge = new AcAccountChargeImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("academicSession", accountService.findCurrentAcademicSession());
		String referenceNo = systemService.generateFormattedReferenceNo(AccountConstants.ACCOUNT_CHARGE_REFRENCE_NO,
				map);
		accountCharge.setReferenceNo(referenceNo);
		accountCharge.setSourceNo(payload.getStudentPayload().getMatricNo());
		accountCharge.setDescription("Minimal amount for this student" + payload.getStudentPayload().getMatricNo());
		accountCharge.setAmount(payload.getMinimalAmount());
		accountCharge.setChargeType(AcAccountChargeType.ACADEMIC);
		accountCharge.setAccount(account);
		accountCharge.setSession(accountService.findCurrentAcademicSession());
		accountCharge.setCode(payload.getStudentPayload().getMatricNo());
		accountCharge.setCohortCode(cohort);

		accountService.addAccountCharge(account, accountCharge);

		LOG.info("Finish Receive AcAccountCharge");

		logoutAsSystem(ctx);
		return new ResponseEntity<String>("success", HttpStatus.OK);
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
