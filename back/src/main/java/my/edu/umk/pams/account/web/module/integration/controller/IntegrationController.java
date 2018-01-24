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
import my.edu.umk.pams.account.identity.model.AcPrincipalImpl;
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
		programCode.setProgramLevel(commonService.findProgramLevelByCode(payload.getProgramLevel().getCode()));
		commonService.saveProgramCode(programCode);

		LOG.info("Finish Receive program code");
		logoutAsSystem(ctx);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@RequestMapping(value = "/facultyCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveFacultyCode(@RequestBody List<FacultyCodePayload> facultyCodePayload) {
		SecurityContext ctx = loginAsSystem();

		LOG.info("Start Receive Faculty");
		for (FacultyCodePayload payload : facultyCodePayload) {

			// check faculty existence
			if (!commonService.isFacultyExists(payload.getCode())) {

				LOG.info("DepartmentCode Not Exists");
				AcFacultyCode faculty = new AcFacultyCodeImpl();
				faculty.setCode(payload.getCode());
				faculty.setDescription(payload.getDescription());
				commonService.saveFacultyCode(faculty);

			} else {

				LOG.info("DepartmentCode Already Exists");
				AcFacultyCode faculty = commonService.findFacultyCodeByCode(payload.getCode());
				faculty.setCode(payload.getCode());
				faculty.setDescription(payload.getDescription());

				commonService.updateFacultyCode(faculty);
			}
		}
		LOG.info("Finish Receive Faculty");

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
		LOG.info("Start incoming admission");
		SecurityContext ctx = loginAsSystem();

		// this is admission
		AcAcademicSession academicSession = accountService
				.findAcademicSessionByCode(payload.getAcademicSession().getCode());
		LOG.debug("AcademicSession:{}", academicSession.getCode());

		// Find and set Student Status
		AcStudent student = identityService.findStudentByMatricNo(payload.getStudent().getMatricNo());
		student.setStudentStatus(AcStudentStatus.get(payload.getStudent().getStudentStatus().ordinal()));
		identityService.updateStudent(student);

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
		charge.setSourceNo(student.getIdentityNo()); // todo:
		charge.setDescription("DESCRIPTION_ADMISSION"); // todo:
		charge.setAmount(BigDecimal.ZERO); // todo
		charge.setOrdinal(payload.getOrdinal());
		if (null != payload.getCohortCode())
			charge.setCohortCode(commonService.findCohortCodeByCode(payload.getCohortCode().getCode()));
		if (null != payload.getStudyMode())
			charge.setStudyMode(commonService.findStudyModeByCode(payload.getStudyMode().getCode()));
		accountService.addAccountCharge(account, charge);

		LOG.debug("AfterAddAccountCharge:{}", charge.getAccount().getActor().getIdentityNo());
		
		LOG.info("Finish Receive admission");
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

		if (commonService.findResidencyCodeByCode(payload.getResidencyCode().getCode()) == null) {
			AcResidencyCode residencyCode = new AcResidencyCodeImpl();
			residencyCode.setCode(payload.getResidencyCode().getCode());
			residencyCode.setDescription(payload.getResidencyCode().getDescriptionEn());
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
		student.setResidencyCode(commonService.findResidencyCodeByCode(payload.getResidencyCode().getCode()));
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

		// Auto Generate Invoice
		// AcInvoice invoice = new AcInvoiceImpl();
		// invoice.setDescription("Registration Invoice for " +
		// academicSession.getCode());
		// invoice.setTotalAmount(BigDecimal.ZERO);
		// invoice.setBalanceAmount(BigDecimal.ZERO);
		// invoice.setIssuedDate(charge.getChargeDate());
		// invoice.setPaid(false);
		// invoice.setSession(accountService.findCurrentAcademicSession());
		// invoice.setAccount(account);
		// billingService.startInvoiceTask(invoice);

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

	// =========================================================================
	// =========================== // STAFF //
	// =========================================================================

	@RequestMapping(value = "/staff/nonAcademicActive", method = RequestMethod.POST)
	public ResponseEntity<String> saveNonAcademicActiveStaff(@RequestBody List<StaffPayload> staffPayload)
			throws RecursiveGroupException {

		SecurityContext ctx = loginAsSystem();

		LOG.info("Start Receive Staff Non Academic Active From IMS");

		for (StaffPayload payload : staffPayload) {

			boolean staffReceive = identityService.isStaffNoExists(payload.getStaffId());

			if (staffReceive) {

				LOG.info("Non Academic Active Staff Already Exists");
				LOG.debug("Staff No :{}", payload.getStaffId());
				LOG.debug("Staff Name :{}", payload.getStaffName());
				LOG.debug("Staff Department Code:{}", payload.getStaffDepartmentCode());

				AcStaff staff = identityService.findStaffByNricNo(payload.getStaffId());

				// Check Department Code
				if (commonService.isFacultyExists(payload.getStaffDepartmentCode())) {

					// Find Staff Department Code
					AcFacultyCode department = commonService.findFacultyCodeByCode(staff.getFacultyCode().getCode());

					if (department.getCode().equals(payload.getStaffDepartmentCode())) {
						LOG.debug("DepartmentCode if sama");

						// AcFacultyCode deptSama =
						// commonService.findFacultyCodeByCode(payload.getStaffDepartmentCode());
						//
						// AcStaff staffDEPTSAMA =
						// identityService.findStaffByStaffNo(payload.getStaffId());
						// staffDEPTSAMA.setIdentityNo(payload.getStaffId());
						// staffDEPTSAMA.setName(payload.getStaffName());
						// staffDEPTSAMA.setActorType(AcActorType.STAFF);
						// staffDEPTSAMA.setStaffType(AcStaffType.NON_ACADEMIC);
						// staffDEPTSAMA.setPhone(payload.getStaffPhoneNo());
						// staffDEPTSAMA.setFacultyCode(deptSama);
						// staffDEPTSAMA.setStaffCategory(payload.getStaffCategory());
						// staffDEPTSAMA.setEmail(payload.getStaffEmail());
						// staffDEPTSAMA.setStaffDeptCode(payload.getStaffDepartmentCode());
						// identityService.updateStaff(staffDEPTSAMA);

						// Check User Existance
						if (identityService.isUserExists(staff.getEmail())) {
							LOG.debug("User Exits if");

							// Find User
							AcUser userExist = identityService.findUserByUsername(staff.getEmail());

							// Find Group Member
							AcGroup groupUserExist = identityService.findGroupByUser(userExist);

							// Find Staff Department Code
							AcFacultyCode departmentCodeExist = commonService
									.findFacultyCodeByCode(staff.getFacultyCode().getCode());

							// Jika Dept Code Sama Group Exists
							AcFacultyCode departmentCodeSame = commonService
									.findFacultyCodeByCode(payload.getStaffDepartmentCode());

							AcStaff staffUpdateSameDept = identityService.findStaffByStaffNo(payload.getStaffId());
							staffUpdateSameDept.setIdentityNo(payload.getStaffId());
							staffUpdateSameDept.setName(payload.getStaffName());
							staffUpdateSameDept.setActorType(AcActorType.STAFF);
							staffUpdateSameDept.setStaffType(AcStaffType.NON_ACADEMIC);
							staffUpdateSameDept.setPhone(payload.getStaffPhoneNo());
							staffUpdateSameDept.setFacultyCode(departmentCodeSame);
							staffUpdateSameDept.setStaffCategory(payload.getStaffCategory());
							staffUpdateSameDept.setEmail(payload.getStaffEmail());
							staffUpdateSameDept.setStaffDeptCode(payload.getStaffDepartmentCode());
							identityService.updateStaff(staffUpdateSameDept);

						} else {
							// Department sama group not exists
							LOG.debug("User not exits but same dept");

							AcFacultyCode deptSamaOnly = commonService
									.findFacultyCodeByCode(payload.getStaffDepartmentCode());

							AcStaff staffDEPTSAMAOnly = identityService.findStaffByStaffNo(payload.getStaffId());
							staffDEPTSAMAOnly.setIdentityNo(payload.getStaffId());
							staffDEPTSAMAOnly.setName(payload.getStaffName());
							staffDEPTSAMAOnly.setActorType(AcActorType.STAFF);
							staffDEPTSAMAOnly.setStaffType(AcStaffType.NON_ACADEMIC);
							staffDEPTSAMAOnly.setPhone(payload.getStaffPhoneNo());
							staffDEPTSAMAOnly.setFacultyCode(deptSamaOnly);
							staffDEPTSAMAOnly.setStaffCategory(payload.getStaffCategory());
							staffDEPTSAMAOnly.setEmail(payload.getStaffEmail());
							staffDEPTSAMAOnly.setStaffDeptCode(payload.getStaffDepartmentCode());
							identityService.updateStaff(staffDEPTSAMAOnly);

						}
					} // Jika Dept Code Tidak Sama dan user group exists
					else if (!(department.getCode().equals(payload.getStaffDepartmentCode()))) {
						LOG.debug("Department Code X sama if");

						if (identityService.isUserExists(staff.getEmail())) {
							LOG.debug("Dept Xsama but User wujud");

							AcFacultyCode deptCodeXSama = commonService
									.findFacultyCodeByCode(payload.getStaffDepartmentCode());

							AcStaff staffUpdateXSamaDept = identityService.findStaffByStaffNo(payload.getStaffId());
							staffUpdateXSamaDept.setIdentityNo(payload.getStaffId());
							staffUpdateXSamaDept.setName(payload.getStaffName());
							staffUpdateXSamaDept.setActorType(AcActorType.STAFF);
							staffUpdateXSamaDept.setStaffType(AcStaffType.NON_ACADEMIC);
							staffUpdateXSamaDept.setPhone(payload.getStaffPhoneNo());
							staffUpdateXSamaDept.setFacultyCode(deptCodeXSama);
							staffUpdateXSamaDept.setStaffCategory(payload.getStaffCategory());
							staffUpdateXSamaDept.setEmail(payload.getStaffEmail());
							staffUpdateXSamaDept.setStaffDeptCode(payload.getStaffDepartmentCode());
							identityService.updateStaff(staffUpdateXSamaDept);

							AcUser userDeptXsama = identityService.findUserByUsername(payload.getStaffEmail());
							userDeptXsama.setActor(staffUpdateXSamaDept);
							userDeptXsama.setEmail(payload.getStaffEmail());
							userDeptXsama.setEnabled(true);
							userDeptXsama.setLocked(true);
							userDeptXsama.setName(payload.getStaffEmail());
							userDeptXsama.setPassword(payload.getStaffId());
							userDeptXsama.setRealName(payload.getStaffName());
							userDeptXsama.setUsername(payload.getStaffEmail());
							userDeptXsama.setPrincipalType(AcPrincipalType.USER);
							identityService.updateUser(userDeptXsama);

							AcPrincipal principalDeptXsama = identityService
									.findPrincipalByName(payload.getStaffEmail());

							AcUser userX = identityService.findUserByUsername(staff.getEmail());

							AcGroup groupX = identityService.findGroupByUser(userX);

							identityService.deleteGroupMember(groupX, principalDeptXsama);

							// setting roles of MGSEB
							if (payload.getStaffDepartmentCode().equals("A10")) {

								if (payload.getStaffCategory().equals("A")) {

									// Principal Role
									AcPrincipalRole roleA10 = new AcPrincipalRoleImpl();
									roleA10.setPrincipal(principalDeptXsama);
									roleA10.setRole(AcRoleType.ROLE_PTJ);
									identityService.addPrincipalRole(principalDeptXsama, roleA10);

									try {
										// Group
										AcGroup groupPegawaiA10 = identityService.findGroupByName("GRP_PGW_PTJ_MGSEB");
										// GroupMember
										if (!identityService.isGroupExists(groupPegawaiA10.getName())) {

											identityService.addGroupMember(groupPegawaiA10, principalDeptXsama);
										}

									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}

								} else if (payload.getStaffCategory().equals("B")) {

									// Principal Role
									AcPrincipalRole rolePenPegA10 = new AcPrincipalRoleImpl();
									rolePenPegA10.setPrincipal(principalDeptXsama);
									rolePenPegA10.setRole(AcRoleType.ROLE_PTJ);
									identityService.addPrincipalRole(principalDeptXsama, rolePenPegA10);

									try {
										// Group
										AcGroup groupPenPegA10 = identityService
												.findGroupByName("GRP_PEN_PGW_PTJ_MGSEB");
										// GroupMember
										if (!identityService.isGroupExists(groupPenPegA10.getName())) {

											identityService.addGroupMember(groupPenPegA10, principalDeptXsama);
										}

									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}

								} else {

									// Principal Role
									AcPrincipalRole roleKRNA10 = new AcPrincipalRoleImpl();
									roleKRNA10.setPrincipal(principalDeptXsama);
									roleKRNA10.setRole(AcRoleType.ROLE_PTJ);
									identityService.addPrincipalRole(principalDeptXsama, roleKRNA10);

									try {
										// Group
										AcGroup groupKRNA10 = identityService.findGroupByName("GRP_KRN_PTJ_MGSEB");
										// GroupMember
										if (!identityService.isGroupExists(groupKRNA10.getName())) {

											identityService.addGroupMember(groupKRNA10, principalDeptXsama);
										}

									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}

								}
							} else if (payload.getStaffDepartmentCode().equals("A09")) {

								if (payload.getStaffCategory().equals("A")) {

									// Principal Role
									AcPrincipalRole roleA09 = new AcPrincipalRoleImpl();
									roleA09.setPrincipal(principalDeptXsama);
									roleA09.setRole(AcRoleType.ROLE_PTJ);
									identityService.addPrincipalRole(principalDeptXsama, roleA09);

									try {
										// Group
										AcGroup groupPegawaiA09 = identityService.findGroupByName("GRP_PGW_PTJ_CPS");
										// GroupMember
										if (!identityService.isGroupExists(groupPegawaiA09.getName())) {

											identityService.addGroupMember(groupPegawaiA09, principalDeptXsama);
										}

									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}

								} else if (payload.getStaffCategory().equals("B")) {

									// Principal Role
									AcPrincipalRole rolePenPegA09 = new AcPrincipalRoleImpl();
									rolePenPegA09.setPrincipal(principalDeptXsama);
									rolePenPegA09.setRole(AcRoleType.ROLE_PTJ);
									identityService.addPrincipalRole(principalDeptXsama, rolePenPegA09);

									try {
										// Group
										AcGroup groupPenPegA09 = identityService.findGroupByName("GRP_PEN_PGW_PTJ_CPS");
										// GroupMember
										if (!identityService.isGroupExists(groupPenPegA09.getName())) {

											identityService.addGroupMember(groupPenPegA09, principalDeptXsama);
										}

									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}

								} else {

									// Principal Role
									AcPrincipalRole roleKRNA09 = new AcPrincipalRoleImpl();
									roleKRNA09.setPrincipal(principalDeptXsama);
									roleKRNA09.setRole(AcRoleType.ROLE_PTJ);
									identityService.addPrincipalRole(principalDeptXsama, roleKRNA09);

									try {
										// Group
										AcGroup groupKRNA09 = identityService.findGroupByName("GRP_KRN_PTJ_CPS");
										// GroupMember
										if (!identityService.isGroupExists(groupKRNA09.getName())) {

											identityService.addGroupMember(groupKRNA09, principalDeptXsama);
										}

									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}

								}
							} else if (payload.getStaffDepartmentCode().equals("B03")) {

								if (payload.getStaffCategory().equals("A")) {

									// Principal Role
									AcPrincipalRole roleB03 = new AcPrincipalRoleImpl();
									roleB03.setPrincipal(principalDeptXsama);
									roleB03.setRole(AcRoleType.ROLE_BRSY);
									identityService.addPrincipalRole(principalDeptXsama, roleB03);

									try {
										// Group
										AcGroup groupPegawaiB03 = identityService.findGroupByName("GRP_PGW_ADM_BEND");
										// GroupMember
										if (!identityService.isGroupExists(groupPegawaiB03.getName())) {

											identityService.addGroupMember(groupPegawaiB03, principalDeptXsama);
										}

									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}

								} else if (payload.getStaffCategory().equals("B")) {

									// Principal Role
									AcPrincipalRole rolePenPegB03 = new AcPrincipalRoleImpl();
									rolePenPegB03.setPrincipal(principalDeptXsama);
									rolePenPegB03.setRole(AcRoleType.ROLE_BRSY);
									identityService.addPrincipalRole(principalDeptXsama, rolePenPegB03);

									try {
										// Group
										AcGroup groupPenPegA03 = identityService
												.findGroupByName("GRP_PEN_PGW_ADM_BEND");
										// GroupMember
										if (!identityService.isGroupExists(groupPenPegA03.getName())) {

											identityService.addGroupMember(groupPenPegA03, principalDeptXsama);
										}

									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}

								} else {

									// Principal Role
									AcPrincipalRole roleKRNA03 = new AcPrincipalRoleImpl();
									roleKRNA03.setPrincipal(principalDeptXsama);
									roleKRNA03.setRole(AcRoleType.ROLE_BRSY);
									identityService.addPrincipalRole(principalDeptXsama, roleKRNA03);

									try {
										// Group
										AcGroup groupKRNA03 = identityService.findGroupByName("GRP_KRN_ADM_BEND");
										// GroupMember
										if (!identityService.isGroupExists(groupKRNA03.getName())) {

											identityService.addGroupMember(groupKRNA03, principalDeptXsama);
										}

									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}

								}

							}

						} else {
							// User Not Exists

							// dept x sama dan group user not exists
							LOG.debug("Dept x sama dan user xwujud");
							AcFacultyCode deptCodeXSama = commonService
									.findFacultyCodeByCode(payload.getStaffDepartmentCode());

							AcStaff staffUpdateXSamaDept = identityService.findStaffByStaffNo(payload.getStaffId());
							staffUpdateXSamaDept.setIdentityNo(payload.getStaffId());
							staffUpdateXSamaDept.setName(payload.getStaffName());
							staffUpdateXSamaDept.setActorType(AcActorType.STAFF);
							staffUpdateXSamaDept.setStaffType(AcStaffType.NON_ACADEMIC);
							staffUpdateXSamaDept.setPhone(payload.getStaffPhoneNo());
							staffUpdateXSamaDept.setFacultyCode(deptCodeXSama);
							staffUpdateXSamaDept.setStaffCategory(payload.getStaffCategory());
							staffUpdateXSamaDept.setEmail(payload.getStaffEmail());
							staffUpdateXSamaDept.setStaffDeptCode(payload.getStaffDepartmentCode());
							identityService.updateStaff(staffUpdateXSamaDept);

							AcUser userDeptXsama = new AcUserImpl();
							userDeptXsama.setActor(staffUpdateXSamaDept);
							userDeptXsama.setEmail(payload.getStaffEmail());
							userDeptXsama.setEnabled(true);
							userDeptXsama.setLocked(true);
							userDeptXsama.setName(payload.getStaffEmail());
							userDeptXsama.setPassword(payload.getStaffId());
							userDeptXsama.setRealName(payload.getStaffName());
							userDeptXsama.setUsername(payload.getStaffEmail());
							userDeptXsama.setPrincipalType(AcPrincipalType.USER);
							identityService.saveUser(userDeptXsama);

							AcPrincipal principalDeptXsama = identityService
									.findPrincipalByName(payload.getStaffEmail());

							// setting roles of MGSEB
							if (payload.getStaffDepartmentCode().equals("A10")) {

								if (payload.getStaffCategory().equals("A")) {

									// Principal Role
									AcPrincipalRole roleA10 = new AcPrincipalRoleImpl();
									roleA10.setPrincipal(principalDeptXsama);
									roleA10.setRole(AcRoleType.ROLE_PTJ);
									identityService.addPrincipalRole(principalDeptXsama, roleA10);

									try {
										// Group
										AcGroup groupPegawaiA10 = identityService.findGroupByName("GRP_PGW_PTJ_MGSEB");
										// GroupMember
										if (!identityService.isGroupExists(groupPegawaiA10.getName())) {

											identityService.addGroupMember(groupPegawaiA10, principalDeptXsama);
										}

									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}

								} else if (payload.getStaffCategory().equals("B")) {

									// Principal Role
									AcPrincipalRole rolePenPegA10 = new AcPrincipalRoleImpl();
									rolePenPegA10.setPrincipal(principalDeptXsama);
									rolePenPegA10.setRole(AcRoleType.ROLE_PTJ);
									identityService.addPrincipalRole(principalDeptXsama, rolePenPegA10);

									try {
										// Group
										AcGroup groupPenPegA10 = identityService
												.findGroupByName("GRP_PEN_PGW_PTJ_MGSEB");
										// GroupMember
										if (!identityService.isGroupExists(groupPenPegA10.getName())) {

											identityService.addGroupMember(groupPenPegA10, principalDeptXsama);
										}

									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}

								} else {

									// Principal Role
									AcPrincipalRole roleKRNA10 = new AcPrincipalRoleImpl();
									roleKRNA10.setPrincipal(principalDeptXsama);
									roleKRNA10.setRole(AcRoleType.ROLE_PTJ);
									identityService.addPrincipalRole(principalDeptXsama, roleKRNA10);

									try {
										// Group
										AcGroup groupKRNA10 = identityService.findGroupByName("GRP_KRN_PTJ_MGSEB");
										// GroupMember
										if (!identityService.isGroupExists(groupKRNA10.getName())) {

											identityService.addGroupMember(groupKRNA10, principalDeptXsama);
										}

									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}

								}
							} else if (payload.getStaffDepartmentCode().equals("A09")) {

								if (payload.getStaffCategory().equals("A")) {

									// Principal Role
									AcPrincipalRole roleA09 = new AcPrincipalRoleImpl();
									roleA09.setPrincipal(principalDeptXsama);
									roleA09.setRole(AcRoleType.ROLE_PTJ);
									identityService.addPrincipalRole(principalDeptXsama, roleA09);

									try {
										// Group
										AcGroup groupPegawaiA09 = identityService.findGroupByName("GRP_PGW_PTJ_CPS");
										// GroupMember
										if (!identityService.isGroupExists(groupPegawaiA09.getName())) {

											identityService.addGroupMember(groupPegawaiA09, principalDeptXsama);
										}

									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}

								} else if (payload.getStaffCategory().equals("B")) {

									// Principal Role
									AcPrincipalRole rolePenPegA09 = new AcPrincipalRoleImpl();
									rolePenPegA09.setPrincipal(principalDeptXsama);
									rolePenPegA09.setRole(AcRoleType.ROLE_PTJ);
									identityService.addPrincipalRole(principalDeptXsama, rolePenPegA09);

									try {
										// Group
										AcGroup groupPenPegA09 = identityService.findGroupByName("GRP_PEN_PGW_PTJ_CPS");
										// GroupMember
										if (!identityService.isGroupExists(groupPenPegA09.getName())) {

											identityService.addGroupMember(groupPenPegA09, principalDeptXsama);
										}

									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}

								} else {

									// Principal Role
									AcPrincipalRole roleKRNA09 = new AcPrincipalRoleImpl();
									roleKRNA09.setPrincipal(principalDeptXsama);
									roleKRNA09.setRole(AcRoleType.ROLE_PTJ);
									identityService.addPrincipalRole(principalDeptXsama, roleKRNA09);

									try {
										// Group
										AcGroup groupKRNA09 = identityService.findGroupByName("GRP_KRN_PTJ_CPS");
										// GroupMember
										if (!identityService.isGroupExists(groupKRNA09.getName())) {

											identityService.addGroupMember(groupKRNA09, principalDeptXsama);
										}

									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}

								}
							} else if (payload.getStaffDepartmentCode().equals("B03")) {

								if (payload.getStaffCategory().equals("A")) {

									// Principal Role
									AcPrincipalRole roleB03 = new AcPrincipalRoleImpl();
									roleB03.setPrincipal(principalDeptXsama);
									roleB03.setRole(AcRoleType.ROLE_BRSY);
									identityService.addPrincipalRole(principalDeptXsama, roleB03);

									try {
										// Group
										AcGroup groupPegawaiB03 = identityService.findGroupByName("GRP_PGW_ADM_BEND");
										// GroupMember
										if (!identityService.isGroupExists(groupPegawaiB03.getName())) {

											identityService.addGroupMember(groupPegawaiB03, principalDeptXsama);
										}

									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}

								} else if (payload.getStaffCategory().equals("B")) {

									// Principal Role
									AcPrincipalRole rolePenPegB03 = new AcPrincipalRoleImpl();
									rolePenPegB03.setPrincipal(principalDeptXsama);
									rolePenPegB03.setRole(AcRoleType.ROLE_BRSY);
									identityService.addPrincipalRole(principalDeptXsama, rolePenPegB03);

									try {
										// Group
										AcGroup groupPenPegA03 = identityService
												.findGroupByName("GRP_PEN_PGW_ADM_BEND");
										// GroupMember
										if (!identityService.isGroupExists(groupPenPegA03.getName())) {

											identityService.addGroupMember(groupPenPegA03, principalDeptXsama);
										}

									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}

								} else {

									// Principal Role
									AcPrincipalRole roleKRNA03 = new AcPrincipalRoleImpl();
									roleKRNA03.setPrincipal(principalDeptXsama);
									roleKRNA03.setRole(AcRoleType.ROLE_BRSY);
									identityService.addPrincipalRole(principalDeptXsama, roleKRNA03);

									try {
										// Group
										AcGroup groupKRNA03 = identityService.findGroupByName("GRP_KRN_ADM_BEND");
										// GroupMember
										if (!identityService.isGroupExists(groupKRNA03.getName())) {

											identityService.addGroupMember(groupKRNA03, principalDeptXsama);
										}

									} catch (RecursiveGroupException e) {

										e.printStackTrace();
									}

								}

							}

						}

					} else {
						LOG.debug("Unknown");
					}
				} else {
					// Faculty Not Exists
					LOG.info("Department Code Not Exists !");

				}

			} else {

				LOG.info("Staff Non Academic Active not exists");
				LOG.debug("Staff Staff_No:{}", payload.getStaffId());
				LOG.debug("Staff Name:{}", payload.getStaffName());
				LOG.debug("Staff Staff Department Code:{}", payload.getStaffDepartmentCode());
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
				staff.setStaffDeptCode(facultyCode);

				if (commonService.isFacultyExists(payload.getStaffDepartmentCode())) {

					if (payload.getStaffDepartmentCode().equals("A10") || payload.getStaffDepartmentCode().equals("A09")
							|| payload.getStaffDepartmentCode().equals("B03")) {
						LOG.info("Login Into System");
						identityService.saveStaffIMSNonAcademicActive(staff);

					} else {

						LOG.info("Not Login Into System");
						identityService.saveStaff(staff);

					}

				} else {
					LOG.info("if department code not exists");
					identityService.saveStaff(staff);

				}

			}
		}
		LOG.info("Finish Receive Staff Non Academic Active From IMS");

		logoutAsSystem(ctx);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	// ====================================================================================================
	// STAFF
	// ====================================================================================================
	@RequestMapping(value = "/staff/nonAcademicInActive", method = RequestMethod.POST)
	public ResponseEntity<String> saveNonAcademicInActiveStaff(@RequestBody List<StaffPayload> staffPayload)
			throws RecursiveGroupException {
		SecurityContext ctx = loginAsSystem();

		LOG.info("Start Receive Staff From IMS");
		for (StaffPayload payload : staffPayload) {

			Boolean staffExistance = identityService.isStaffNoExists(payload.getStaffId());

			if (staffExistance) {
				// Staff Already Exists
				LOG.info("Staff Non Academic InActive Already Exists");
				LOG.debug("Staff No :{}", payload.getStaffId());
				LOG.debug("Staff Name :{}", payload.getStaffName());

				// Find Staff
				AcStaff staffExist = identityService.findStaffByStaffNo(payload.getStaffId());

				if (commonService.isFacultyExists(payload.getStaffDepartmentCode())) {
					// Department wujud
					LOG.debug("Department Code Wujud");
					AcFacultyCode departmentExist = commonService.findFacultyCodeByCode(payload.getStaffDepartmentCode());
					
					AcStaff staffDeptExist = identityService.findStaffByStaffNo(payload.getStaffId());
					staffDeptExist.setActorType(AcActorType.STAFF);
					staffDeptExist.setFacultyCode(departmentExist);
					staffDeptExist.setName(payload.getStaffName());
					staffDeptExist.setIdentityNo(payload.getStaffId());
					staffDeptExist.setActorType(AcActorType.STAFF);
					staffDeptExist.setStaffType(AcStaffType.NON_ACADEMIC);
					staffDeptExist.setPhone(payload.getStaffPhoneNo());
					staffDeptExist.setStaffCategory(payload.getStaffCategory());
					staffDeptExist.setEmail(payload.getStaffEmail());
					staffDeptExist.setStaffDeptCode(payload.getStaffDepartmentCode());
					identityService.updateStaff(staffDeptExist);
					
					
				} else {
					// Department tak wujud
					LOG.debug("Department Code X Wujud");
					AcStaff staffDeptExist = identityService.findStaffByStaffNo(payload.getStaffId());
					staffDeptExist.setActorType(AcActorType.STAFF);
					staffDeptExist.setName(payload.getStaffName());
					staffDeptExist.setIdentityNo(payload.getStaffId());
					staffDeptExist.setActorType(AcActorType.STAFF);
					staffDeptExist.setStaffType(AcStaffType.NON_ACADEMIC);
					staffDeptExist.setPhone(payload.getStaffPhoneNo());
					staffDeptExist.setStaffCategory(payload.getStaffCategory());
					staffDeptExist.setEmail(payload.getStaffEmail());
					staffDeptExist.setStaffDeptCode(payload.getStaffDepartmentCode());
					identityService.updateStaff(staffDeptExist);
				}

			} else {
				// Staff not exists
				LOG.debug("Staff Not Exist !");
				LOG.debug("Staff No :{}",payload.getStaffId());
				LOG.debug("Staff Name :{}",payload.getStaffName());
				LOG.debug("Staff Dept Code :{}",payload.getStaffDepartmentCode());
				
				AcFacultyCode department = commonService.findFacultyCodeByCode(payload.getStaffDepartmentCode());
				
				AcStaff staffDeptExist = new AcStaffImpl();
				staffDeptExist.setActorType(AcActorType.STAFF);
				staffDeptExist.setName(payload.getStaffName());
				staffDeptExist.setIdentityNo(payload.getStaffId());
				staffDeptExist.setActorType(AcActorType.STAFF);
				staffDeptExist.setStaffType(AcStaffType.NON_ACADEMIC);
				staffDeptExist.setPhone(payload.getStaffPhoneNo());
				staffDeptExist.setStaffCategory(payload.getStaffCategory());
				staffDeptExist.setEmail(payload.getStaffEmail());
				staffDeptExist.setStaffDeptCode(payload.getStaffDepartmentCode());
		
				if (commonService.isFacultyExists(payload.getStaffDepartmentCode())) {

					if (payload.getStaffDepartmentCode().equals("A10") || payload.getStaffDepartmentCode().equals("A09")
							|| payload.getStaffDepartmentCode().equals("B03")) {
						LOG.info("Login Into System");
						identityService.saveStaffIMSNonAcademicInActive(staffDeptExist);

					} else {

						LOG.info("Not Login Into System");
						identityService.saveStaff(staffDeptExist);

					}

				} else {
					LOG.info("if department code not exists");

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
