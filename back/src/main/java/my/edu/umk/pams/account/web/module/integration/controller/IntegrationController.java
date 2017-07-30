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

import my.edu.umk.pams.account.AccountConstants;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
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
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcStudentImpl;
import my.edu.umk.pams.account.identity.model.AcStudentStatus;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.identity.model.AcUserImpl;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.security.integration.AcAutoLoginToken;
import my.edu.umk.pams.account.security.integration.NonSerializableSecurityContext;
import my.edu.umk.pams.account.system.service.SystemService;
import my.edu.umk.pams.connector.payload.AdmissionPayload;
import my.edu.umk.pams.connector.payload.CandidatePayload;
import my.edu.umk.pams.connector.payload.CohortCodePayload;
import my.edu.umk.pams.connector.payload.FacultyCodePayload;
import my.edu.umk.pams.connector.payload.IntakePayload;
import my.edu.umk.pams.connector.payload.IntakeSessionCodePayload;
import my.edu.umk.pams.connector.payload.ProgramCodePayload;

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

    @RequestMapping(value = "/cohortCodes", method = RequestMethod.POST)
    public ResponseEntity<String> saveCohortCode(@RequestBody CohortCodePayload payload) {
        LOG.info("incoming cohort code");
        SecurityContext ctx = loginAsSystem();

        AcCohortCode cohortCode = new AcCohortCodeImpl();
        cohortCode.setCode(payload.getCode());
        cohortCode.setDescription(payload.getDescription());
        cohortCode.setProgramCode(commonService.findProgramCodeByCode(payload.getProgramCode().getCode()));
        commonService.saveCohortCode(cohortCode);

        logoutAsSystem(ctx);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @RequestMapping(value = "/programCodes", method = RequestMethod.POST)
    public ResponseEntity<String> saveProgramCode(@RequestBody ProgramCodePayload payload) {
        LOG.info("incoming program code");
        SecurityContext ctx = loginAsSystem();

        // check, validate

        AcProgramCode programCode = new AcProgramCodeImpl();
        programCode.setCode(payload.getCode());
        programCode.setDescription(payload.getDescription());
        programCode.setFacultyCode(commonService.findFacultyCodeByCode(payload.getFacultyCode().getCode()));
        commonService.saveProgramCode(programCode);

        logoutAsSystem(ctx);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @RequestMapping(value = "/facultyCodes", method = RequestMethod.POST)
    public ResponseEntity<String> saveFacultyCode(@RequestBody FacultyCodePayload payload) {
        LOG.info("incoming faculty code");
        SecurityContext ctx = loginAsSystem();

        AcFacultyCode facultyCode = new AcFacultyCodeImpl();
        facultyCode.setCode(payload.getCode());
        facultyCode.setDescription(payload.getDescription());
        commonService.saveFacultyCode(facultyCode);

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
        AcAcademicSession academicSession = accountService.findAcademicSessionByCode(payload.getAcademicSession().getCode());
        AcStudent student = identityService.findStudentByMatricNo(payload.getStudent().getMatricNo());
        AcAccount account = accountService.findAccountByActor(student);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("academicSession", accountService.findCurrentAcademicSession());
        String referenceNo = systemService.generateFormattedReferenceNo(AccountConstants.ACCOUNT_CHARGE_REFRENCE_NO, map);
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

            String cohortCode =
                    offeredProgramCode.getFacultyCode().getCode()
                            + "-" + offeredProgramCode.getProgramLevel().getCode()
                            + "-" + offeredProgramCode.getCode()
                            + "-CHRT"
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
    // CANDIDATE
    // incoming from intake
    // ====================================================================================================
    @RequestMapping(value = "/candidates", method = RequestMethod.POST)
    public ResponseEntity<String> saveCandidate(@RequestBody CandidatePayload payload) {
        SecurityContext ctx = loginAsSystem();

        // student info
        AcStudent student = new AcStudentImpl();
        student.setMatricNo(payload.getMatricNo());
        student.setName(payload.getName());
        student.setEmail(payload.getEmail()); // todo: email university?
        student.setFax(payload.getFax());
        student.setPhone(payload.getPhone());
        student.setMobile(payload.getMobile());

        student.setStudentStatus(AcStudentStatus.ACTIVE);
        student.setCohortCode(commonService.findCohortCodeByCode(payload.getCohortCode()));
        student.setResidencyCode(commonService.findResidencyCodeByCode(payload.getResidencyCode().getCode()));
        identityService.saveStudent(student);
        AcStudent savedStudent = identityService.findStudentByMatricNo(payload.getMatricNo());

        // account
        AcAccount account = new AcAccountImpl();
        account.setActor(savedStudent);
        account.setCode(student.getMatricNo());
        account.setDescription("ACCOUNT;STUDENT;" + student.getMatricNo());
        accountService.saveAccount(account);

        // save user
        AcUser user = new AcUserImpl();
        user.setUsername(payload.getMatricNo());
        user.setPassword("abc123");
        user.setRealName(payload.getName());
        user.setLocked(true);
        user.setEnabled(true);
        user.setActor(savedStudent);
        identityService.saveUser(user);

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

