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

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.edu.umk.pams.account.AccountConstants;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAcademicSessionImpl;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeImpl;
import my.edu.umk.pams.account.account.model.AcAccountChargeType;
import my.edu.umk.pams.account.account.model.AcAccountImpl;
import my.edu.umk.pams.account.account.model.AcFeeSchedule;
import my.edu.umk.pams.account.account.model.AcFeeScheduleItem;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.chain.AdmissionChargeAttachChain;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceImpl;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcInvoiceItemImpl;
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
import my.edu.umk.pams.account.financialaid.model.AcGraduateCenterType;
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
import my.edu.umk.pams.account.identity.model.AcStaffType;
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
	
	@Autowired
	private AdmissionChargeAttachChain chain;

	// ====================================================================================================
	// CODES
	// ====================================================================================================

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
			cohortCode.setProgramCode(commonService.findProgramCodeByCode(payload.getProgramCode().getCode()));
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
		student.setIcNo(payload.getUserPayload().getNric());
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

		// admission
		AcAcademicSession academicSession = accountService.findCurrentAcademicSession();
		LOG.debug("AcademicSession:{}", academicSession.getCode());

		student = identityService.findStudentByMatricNo(payload.getMatricNo());
		account = accountService.findAccountByActor(student);
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
		charge.setSourceNo(account.getCode()); // todo:
		charge.setDescription("DESCRIPTION"); // todo:
		charge.setAmount(BigDecimal.ZERO); // todo
		charge.setOrdinal(1);
		if (payload.getFacultyCode().getCode().equals("A10")) {
			charge.setGraduateCenterType(AcGraduateCenterType.MGSEB);
		} else {
			charge.setGraduateCenterType(AcGraduateCenterType.CPS);
		}
		if (null != payload.getStudyCenter())
			charge.setStudyCenterCode(commonService.findStudyCenterCodeByCode(payload.getStudyCenter().getCode()));
		if (null != payload.getCohortCode())
			charge.setCohortCode(commonService.findCohortCodeByCode(payload.getCohortCode()));
		if (null != payload.getStudyMode())
			charge.setStudyMode(commonService.findStudyModeByCode(payload.getStudyMode().getCode()));
		accountService.addAccountCharge(account, charge);
		
		//Auto Generate Invoice
//		AcInvoice invoice = new AcInvoiceImpl();
//        invoice.setDescription("Registration Invoice for " + academicSession.getCode());
//        invoice.setTotalAmount(BigDecimal.ZERO);
//        invoice.setBalanceAmount(BigDecimal.ZERO);
//        invoice.setIssuedDate(charge.getChargeDate());
//        invoice.setPaid(false);
//        invoice.setSession(accountService.findCurrentAcademicSession());
//        invoice.setAccount(account);
//        billingService.startInvoiceTask(invoice);
		
		LOG.info("Finish Receive Candidates");
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

	/*
	 * //
	 * =========================================================================
	 * =========================== // STAFF //
	 * =========================================================================
	 * ===========================
	 * 
	 * @RequestMapping(value = "/staff/nonAcademicActive", method =
	 * RequestMethod.POST) public ResponseEntity<String> saveStaff(@RequestBody
	 * List<StaffPayload> staffPayload) throws RecursiveGroupException {
	 * SecurityContext ctx = loginAsSystem();
	 * 
	 * LOG.info("Start Receive Staff From IMS"); for (StaffPayload payload :
	 * staffPayload) {
	 * 
	 * boolean staffReceive =
	 * identityService.isStaffNoExists(payload.getStaffId());
	 * 
	 * if (staffReceive) {
	 * 
	 * LOG.info("Staff already exists"); LOG.debug("Staff Staff_No:{}",
	 * payload.getStaffId()); LOG.debug("Staff Name:{}",
	 * payload.getStaffName()); // Find Staff AcStaff staff =
	 * identityService.findStaffByNricNo(payload.getStaffId());
	 * 
	 * // Find Department Code if
	 * (commonService.isFacultyExists(payload.getStaffDepartmentCode())) {
	 * 
	 * LOG.debug("Has Faculty"); AcFacultyCode departmentCode =
	 * commonService.findFacultyCodeByCode(staff.getStaffDeptCode()); // Find
	 * User AcUser user =
	 * identityService.findUserByUsername(staff.getIdentityNo()); // Find Group
	 * AcGroup group = identityService.findGroupByUser(user);
	 * 
	 * if (departmentCode.equals(payload.getStaffDepartmentCode()) &&
	 * identityService.isGroupExists(group.getName())) {
	 * 
	 * AcFacultyCode faculty =
	 * commonService.findFacultyCodeByCode(payload.getStaffDepartmentCode());
	 * 
	 * AcStaff staffUpdate =
	 * identityService.findStaffByStaffNo(payload.getStaffId());
	 * staffUpdate.setIdentityNo(payload.getStaffId());
	 * staffUpdate.setName(payload.getStaffName());
	 * staffUpdate.setActorType(AcActorType.STAFF);
	 * staff.setStaffType(AcStaffType.NON_ACADEMIC);
	 * staffUpdate.setPhone(payload.getStaffPhoneNo());
	 * staffUpdate.setFacultyCode(faculty);
	 * staffUpdate.setStaffCategory(payload.getStaffCategory());
	 * staffUpdate.setEmail(payload.getStaffEmail());
	 * identityService.updateStaff(staffUpdate);
	 * 
	 * } else if ((!departmentCode.equals(payload.getStaffDepartmentCode()))) {
	 * 
	 * AcFacultyCode faculty =
	 * commonService.findFacultyCodeByCode(payload.getStaffDepartmentCode());
	 * 
	 * AcStaff staffUpdate =
	 * identityService.findStaffByStaffNo(payload.getStaffId());
	 * staffUpdate.setIdentityNo(payload.getStaffId());
	 * staffUpdate.setName(payload.getStaffName());
	 * staffUpdate.setActorType(AcActorType.STAFF);
	 * staffUpdate.setStaffType(AcStaffType.NON_ACADEMIC);
	 * staffUpdate.setPhone(payload.getStaffPhoneNo());
	 * staffUpdate.setFacultyCode(faculty);
	 * staffUpdate.setStaffCategory(payload.getStaffCategory());
	 * staffUpdate.setEmail(payload.getStaffEmail());
	 * 
	 * AcUser updateUser = new AcUserImpl(); user.setActor(staffUpdate);
	 * user.setEmail(staffUpdate.getEmail());
	 * user.setUsername(staffUpdate.getEmail());
	 * user.setPassword(staffUpdate.getStaffNo());
	 * user.setRealName(staffUpdate.getName());
	 * user.setName(staffUpdate.getIdentityNo()); user.setEnabled(true);
	 * user.setLocked(true); user.setPrincipalType(AcPrincipalType.USER);
	 * identityService.saveUser(updateUser);
	 * 
	 * AcPrincipal principal =
	 * identityService.findPrincipalByName(payload.getStaffId());
	 * 
	 * //Check Group Existence if
	 * (identityService.isGroupExists(group.getName())) {
	 * 
	 * //setting roles of MGSEB if
	 * (payload.getStaffDepartmentCode().equals("A10")) {
	 * 
	 * if(payload.getStaffCategory().equals("A")){
	 * 
	 * // Principal Role AcPrincipalRole roleA10 = new AcPrincipalRoleImpl();
	 * roleA10.setPrincipal(principal); roleA10.setRole(AcRoleType.ROLE_MGSEB);
	 * identityService.addPrincipalRole(principal, roleA10);
	 * 
	 * try{ // Group AcGroup groupPegawaiA10 =
	 * identityService.findGroupByName("GRP_PGW_ADM_A10"); // GroupMember
	 * AcGroupMember memberA10 = new AcGroupMemberImpl();
	 * memberA10.setGroup(groupPegawaiA10); memberA10.setPrincipal(principal);
	 * identityService.addGroupMember(groupPegawaiA10, principal); } catch
	 * (RecursiveGroupException e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * }else{
	 * 
	 * // Principal Role AcPrincipalRole roleKRNA10 = new AcPrincipalRoleImpl();
	 * roleKRNA10.setPrincipal(principal);
	 * roleKRNA10.setRole(AcRoleType.ROLE_MGSEB);
	 * identityService.addPrincipalRole(principal, roleKRNA10);
	 * 
	 * try{ // Group AcGroup groupKRNA10 =
	 * identityService.findGroupByName("GRP_KRN_ADM_A10"); // GroupMember
	 * AcGroupMember memberKRNA10 = new AcGroupMemberImpl();
	 * memberKRNA10.setGroup(groupKRNA10); memberKRNA10.setPrincipal(principal);
	 * identityService.addGroupMember(groupKRNA10, principal); } catch
	 * (RecursiveGroupException e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * } } //Setting roles of CPS else if
	 * (payload.getStaffDepartmentCode().equals("A09")) {
	 * 
	 * if(payload.getStaffCategory().equals("A")){
	 * 
	 * // Principal Role AcPrincipalRole roleA09 = new AcPrincipalRoleImpl();
	 * roleA09.setPrincipal(principal);
	 * roleA09.setRole(AcRoleType.ROLE_ADMINISTRATOR);
	 * identityService.addPrincipalRole(principal, roleA09);
	 * 
	 * try{ // Group AcGroup groupPegawaiA09 =
	 * identityService.findGroupByName("GRP_PGW_ADM_A09"); // GroupMember
	 * AcGroupMember memberPegawaiA09 = new AcGroupMemberImpl();
	 * memberPegawaiA09.setGroup(groupPegawaiA09);
	 * memberPegawaiA09.setPrincipal(principal);
	 * identityService.addGroupMember(groupPegawaiA09, principal); } catch
	 * (RecursiveGroupException e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * }else{
	 * 
	 * // Principal Role AcPrincipalRole roleKRNA09 = new AcPrincipalRoleImpl();
	 * roleKRNA09.setPrincipal(principal);
	 * roleKRNA09.setRole(AcRoleType.ROLE_ADMINISTRATOR);
	 * identityService.addPrincipalRole(principal, roleKRNA09);
	 * 
	 * try{ // Group AcGroup groupKRNA09 =
	 * identityService.findGroupByName("GRP_KRN_ADM_A09"); // GroupMember
	 * AcGroupMember memberKRNA09 = new AcGroupMemberImpl();
	 * memberKRNA09.setGroup(groupKRNA09); memberKRNA09.setPrincipal(principal);
	 * identityService.addGroupMember(groupKRNA09, principal); } catch
	 * (RecursiveGroupException e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * } } //Setting roles of Others Faculty else {
	 * if(payload.getStaffCategory().equals("A")){
	 * LOG.info("If All Faculty and Category A Only");
	 * 
	 * // Principal Role AcPrincipalRole roleAllFac = new AcPrincipalRoleImpl();
	 * roleAllFac.setPrincipal(principal);
	 * roleAllFac.setRole(AcRoleType.ROLE_FACULTY);
	 * identityService.addPrincipalRole(principal, roleAllFac);
	 * 
	 * try{ // Group AcGroup groupAllFac =
	 * identityService.findGroupByName("GRP_PGW_FCTY_"+
	 * payload.getStaffDepartmentCode()); // GroupMember
	 * 
	 * AcGroupMember memberAllFac = new AcGroupMemberImpl();
	 * memberAllFac.setGroup(groupAllFac); memberAllFac.setPrincipal(principal);
	 * identityService.addGroupMember(groupAllFac, principal); } catch
	 * (RecursiveGroupException e) {
	 * 
	 * e.printStackTrace(); } }else{ LOG.info("If All Faculty Only");
	 * 
	 * // Principal Role AcPrincipalRole roleAllFaculty = new
	 * AcPrincipalRoleImpl(); roleAllFaculty.setPrincipal(principal);
	 * roleAllFaculty.setRole(AcRoleType.ROLE_FACULTY);
	 * identityService.addPrincipalRole(principal, roleAllFaculty);
	 * 
	 * try{ // Group AcGroup groupAllFaculty =
	 * identityService.findGroupByName("GRP_KRN_FCTY_"+
	 * payload.getStaffDepartmentCode()); // GroupMember AcGroupMember
	 * memberAllFaculty = new AcGroupMemberImpl();
	 * memberAllFaculty.setGroup(groupAllFaculty);
	 * memberAllFaculty.setPrincipal(principal);
	 * identityService.addGroupMember(groupAllFaculty, principal); } catch
	 * (RecursiveGroupException e) {
	 * 
	 * e.printStackTrace(); } } } }
	 * 
	 * identityService.updateStaff(staffUpdate); }
	 * 
	 * } else {
	 * 
	 * LOG.debug("NoFaculty"); AcFacultyCode faculty =
	 * commonService.findFacultyCodeByCode(payload.getStaffDepartmentCode());
	 * 
	 * AcStaff staffUpdate =
	 * identityService.findStaffByStaffNo(payload.getStaffId());
	 * staffUpdate.setIdentityNo(payload.getStaffId());
	 * staffUpdate.setName(payload.getStaffName());
	 * staffUpdate.setActorType(AcActorType.STAFF);
	 * staff.setStaffType(AcStaffType.NON_ACADEMIC);
	 * staffUpdate.setPhone(payload.getStaffPhoneNo());
	 * staffUpdate.setFacultyCode(faculty);
	 * staffUpdate.setStaffCategory(payload.getStaffCategory());
	 * staffUpdate.setEmail(payload.getStaffEmail());
	 * identityService.updateStaff(staffUpdate); }
	 * 
	 * } else {
	 * 
	 * LOG.info("Staff not exists"); LOG.debug("Staff Staff_No:{}",
	 * payload.getStaffId()); LOG.debug("Staff Name:{}",
	 * payload.getStaffName()); LOG.debug("Staff Department_Code:{}",
	 * payload.getStaffDepartmentCode()); LOG.debug("Staff Category:{}",
	 * payload.getStaffCategory());
	 * 
	 * String facultyCode = payload.getStaffDepartmentCode(); AcFacultyCode
	 * faculty = commonService.findFacultyCodeByCode(facultyCode);
	 * 
	 * AcStaff staff = new AcStaffImpl();
	 * staff.setIdentityNo(payload.getStaffId());
	 * staff.setStaffType(AcStaffType.NON_ACADEMIC);
	 * staff.setName(payload.getStaffName());
	 * staff.setActorType(AcActorType.STAFF);
	 * staff.setPhone(payload.getStaffPhoneNo()); staff.setFacultyCode(faculty);
	 * staff.setStaffCategory(payload.getStaffCategory());
	 * staff.setEmail(payload.getStaffEmail()); if
	 * (commonService.isFacultyExists(payload.getStaffDepartmentCode())) {
	 * LOG.info("if faculty exists");
	 * identityService.saveStaffIMSNonAcademicActive(staff);
	 * 
	 * } else { LOG.info("if faculty not exists");
	 * identityService.saveStaff(staff);
	 * 
	 * } } } LOG.info("Finish Receive Staff From IMS");
	 * 
	 * logoutAsSystem(ctx); return new ResponseEntity<String>("success",
	 * HttpStatus.OK); }
	 */

	// ====================================================================================================
	// STAFF
	// ====================================================================================================
	@RequestMapping(value = "/staff/nonAcademicActive", method = RequestMethod.POST)
	public ResponseEntity<String> saveStaff(@RequestBody List<StaffPayload> staffPayload)
			throws RecursiveGroupException {
		SecurityContext ctx = loginAsSystem();

		LOG.info("Start Receive Staff From IMS");
		for (StaffPayload payload : staffPayload) {

			boolean staffReceive = identityService.isStaffNoExists(payload.getStaffId());

			if (staffReceive) {

				LOG.info("Staff already exists");
				LOG.debug("Staff Staff_No:{}", payload.getStaffId());
				LOG.debug("Staff Name:{}", payload.getStaffName());

				// Find Staff By Identity No
				AcStaff staff = identityService.findStaffByStaffNo(payload.getStaffId());

				// Find Department Code Existence
				if (commonService.isFacultyExists(payload.getStaffDepartmentCode())) {

					LOG.info("Faculty Already Exists");

					// Find Department Code
					AcFacultyCode departmentCode = commonService
							.findFacultyCodeByCode(staff.getFacultyCode().getCode());

					// Find User
					AcUser user = identityService.findUserByUsername(staff.getEmail());

					// Find Group
					AcGroup group = identityService.findGroupByUser(user);

					if (departmentCode.equals(payload.getStaffDepartmentCode())
							&& identityService.isGroupExists(group.getName())) {

						AcFacultyCode faculty = commonService.findFacultyCodeByCode(payload.getStaffDepartmentCode());

						AcStaff staffUpdate = identityService.findStaffByStaffNo(payload.getStaffId());
						staffUpdate.setIdentityNo(payload.getStaffId());
						staffUpdate.setName(payload.getStaffName());
						staffUpdate.setActorType(AcActorType.STAFF);
						staffUpdate.setStaffType(AcStaffType.NON_ACADEMIC);
						staffUpdate.setPhone(payload.getStaffPhoneNo());
						staffUpdate.setFacultyCode(faculty);
						staffUpdate.setStaffCategory(payload.getStaffCategory());
						staffUpdate.setEmail(payload.getStaffEmail());
						identityService.updateStaff(staffUpdate);

					} else if ((!departmentCode.equals(payload.getStaffDepartmentCode()))) {

						AcFacultyCode faculty = commonService.findFacultyCodeByCode(payload.getStaffDepartmentCode());

						AcStaff staffUpdate = identityService.findStaffByStaffNo(payload.getStaffId());
						staffUpdate.setIdentityNo(payload.getStaffId());
						staffUpdate.setName(payload.getStaffName());
						staffUpdate.setActorType(AcActorType.STAFF);
						staffUpdate.setStaffType(AcStaffType.NON_ACADEMIC);
						staffUpdate.setPhone(payload.getStaffPhoneNo());
						staffUpdate.setFacultyCode(faculty);
						staffUpdate.setStaffCategory(payload.getStaffCategory());
						staffUpdate.setEmail(payload.getStaffEmail());

						AcUser updateUser = identityService.findUserByUsername(payload.getStaffEmail());
						updateUser.setActor(staffUpdate);
						updateUser.setEmail(payload.getStaffEmail());
						updateUser.setUsername(payload.getStaffEmail());
						updateUser.setPassword(payload.getStaffId());
						updateUser.setRealName(payload.getStaffName());
						updateUser.setName(payload.getStaffEmail());
						updateUser.setEnabled(true);
						updateUser.setLocked(true);
						updateUser.setPrincipalType(AcPrincipalType.USER);
						identityService.saveUser(updateUser);

						AcPrincipal principal = identityService.findPrincipalByName(payload.getStaffEmail());
						LOG.debug("Principal Atas:{}", principal);

						// Check Group Existence
						if (identityService.isGroupExists(group.getName())) {

							// setting roles of MGSEB
							if (payload.getStaffDepartmentCode().equals("A10")) {

								if (payload.getStaffCategory().equals("A")) {

									// Principal Role
									AcPrincipalRole roleA10 = new AcPrincipalRoleImpl();
									roleA10.setPrincipal(principal);
									roleA10.setRole(AcRoleType.ROLE_MGSEB);
									identityService.addPrincipalRole(principal, roleA10);

									try {
										// Group
										AcGroup groupPegawaiA10 = identityService.findGroupByName("GRP_PGW_ADM_A10");
										// GroupMember
										if (!identityService.isGroupExists(groupPegawaiA10.getName())) {

											identityService.addGroupMember(groupPegawaiA10, principal);
										}

									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}

								} else {

									// Principal Role
									AcPrincipalRole roleKRNA10 = new AcPrincipalRoleImpl();
									roleKRNA10.setPrincipal(principal);
									roleKRNA10.setRole(AcRoleType.ROLE_MGSEB);
									identityService.addPrincipalRole(principal, roleKRNA10);

									try {
										// Group
										AcGroup groupKRNA10 = identityService.findGroupByName("GRP_KRN_ADM_A10");
										// GroupMember
										if (!identityService.isGroupExists(groupKRNA10.getName())) {

											identityService.addGroupMember(groupKRNA10, principal);
										}

									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}

								}
							}
							// Setting roles of CPS
							else if (payload.getStaffDepartmentCode().equals("A09")) {

								if (payload.getStaffCategory().equals("A")) {

									// Principal Role
									AcPrincipalRole roleA09 = new AcPrincipalRoleImpl();
									roleA09.setPrincipal(principal);
									roleA09.setRole(AcRoleType.ROLE_CPS);
									identityService.addPrincipalRole(principal, roleA09);

									try {
										// Group
										AcGroup groupPegawaiA09 = identityService.findGroupByName("GRP_PGW_ADM_A09");
										// GroupMember
										if (!identityService.isGroupExists(groupPegawaiA09.getName())) {

											identityService.addGroupMember(groupPegawaiA09, principal);
										}
									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}

								} else {

									// Principal Role
									AcPrincipalRole roleKRNA09 = new AcPrincipalRoleImpl();
									roleKRNA09.setPrincipal(principal);
									roleKRNA09.setRole(AcRoleType.ROLE_CPS);
									identityService.addPrincipalRole(principal, roleKRNA09);

									try {
										// Group
										AcGroup groupKRNA09 = identityService.findGroupByName("GRP_KRN_ADM_A09");
										// GroupMember
										if (!identityService.isGroupExists(groupKRNA09.getName())) {

											identityService.addGroupMember(groupKRNA09, principal);
										}

									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}

								}
							}
							// Setting roles of Others Faculty
							else {
								if (payload.getStaffCategory().equals("A")) {
									LOG.info("If All Faculty and Category A Only");

									// Principal Role
									AcPrincipalRole roleAllFac = new AcPrincipalRoleImpl();
									roleAllFac.setPrincipal(principal);
									roleAllFac.setRole(AcRoleType.ROLE_FACULTY);
									identityService.addPrincipalRole(principal, roleAllFac);
									LOG.debug("roleAllFac:{}", roleAllFac);
									try {
										// Group

										AcGroup groupAllFac = identityService
												.findGroupByName("GRP_PGW_FCTY_" + payload.getStaffDepartmentCode());
										LOG.debug("Group:{}", groupAllFac);
										// GroupMember
										if (!identityService.isGroupExists(groupAllFac.getName())) {

											identityService.addGroupMember(groupAllFac, principal);
										}
									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}
								} else {
									LOG.info("If All Faculty Only");

									// Principal Role
									AcPrincipalRole roleAllFaculty = new AcPrincipalRoleImpl();
									roleAllFaculty.setPrincipal(principal);
									roleAllFaculty.setRole(AcRoleType.ROLE_FACULTY);
									identityService.addPrincipalRole(principal, roleAllFaculty);

									try {
										// Group
										AcGroup groupAllFaculty = identityService
												.findGroupByName("GRP_KRN_FCTY_" + payload.getStaffDepartmentCode());
										LOG.debug("Group:{}", groupAllFaculty);
										// GroupMember
										if (!identityService.isGroupExists(groupAllFaculty.getName())) {

											identityService.addGroupMember(groupAllFaculty, principal);
										}

									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}
								}
							}
						}

						identityService.updateStaff(staffUpdate);
					}

				} else {
					LOG.info("Faculty Not Exists");

					// update staff
					AcStaff staffUpdate = identityService.findStaffByStaffNo(payload.getStaffId());
					staffUpdate.setIdentityNo(payload.getStaffId());
					staffUpdate.setName(payload.getStaffName());
					staffUpdate.setActorType(AcActorType.STAFF);
					staffUpdate.setStaffType(AcStaffType.NON_ACADEMIC);
					staffUpdate.setPhone(payload.getStaffPhoneNo());
					staffUpdate.setStaffCategory(payload.getStaffCategory());
					staffUpdate.setEmail(payload.getStaffEmail());
					identityService.updateStaff(staffUpdate);
				}

			} else {

				LOG.info("Staff not exists");
				LOG.debug("Staff Staff_No:{}", payload.getStaffId());
				LOG.debug("Staff Name:{}", payload.getStaffName());
				LOG.debug("Staff Department_Code:{}", payload.getStaffDepartmentCode());
				LOG.debug("Staff Category:{}", payload.getStaffCategory());

				String facultyCode = payload.getStaffDepartmentCode();
				AcFacultyCode faculty = commonService.findFacultyCodeByCode(facultyCode);

				AcStaff staff = new AcStaffImpl();
				staff.setIdentityNo(payload.getStaffId());
				staff.setStaffType(AcStaffType.NON_ACADEMIC);
				staff.setName(payload.getStaffName());
				staff.setActorType(AcActorType.STAFF);
				staff.setPhone(payload.getStaffPhoneNo());
				staff.setFacultyCode(faculty);
				staff.setStaffCategory(payload.getStaffCategory());
				staff.setEmail(payload.getStaffEmail());
				if (commonService.isFacultyExists(payload.getStaffDepartmentCode())) {
					LOG.info("if faculty exists");
					identityService.saveStaffIMSNonAcademicActive(staff);

				} else {
					LOG.info("if faculty not exists");
					identityService.saveStaff(staff);

				}
			}
		}
		LOG.info("Finish Receive Staff From IMS");

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
