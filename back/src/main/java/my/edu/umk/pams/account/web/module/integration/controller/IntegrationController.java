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

import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcCohortCodeImpl;
import my.edu.umk.pams.account.common.model.AcFacultyCode;
import my.edu.umk.pams.account.common.model.AcFacultyCodeImpl;
import my.edu.umk.pams.account.common.model.AcProgramCode;
import my.edu.umk.pams.account.common.model.AcProgramCodeImpl;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.security.integration.AcAutoLoginToken;
import my.edu.umk.pams.account.security.integration.NonSerializableSecurityContext;
import my.edu.umk.pams.connector.payload.CohortCodePayload;
import my.edu.umk.pams.connector.payload.FacultyCodePayload;
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
    private IdentityService identityService;

    // ====================================================================================================
    // CODES
    // ====================================================================================================


    @RequestMapping(value = "/cohortCodes", method = RequestMethod.POST)
    public ResponseEntity<String> saveCohortCode(@RequestBody CohortCodePayload payload) {
        SecurityContext ctx = loginAsSystem();

        AcCohortCode cohortCode = new AcCohortCodeImpl();
        cohortCode.setCode(payload.getCode());
        cohortCode.setDescription(payload.getDescription());
        cohortCode.setProgramCode(commonService.findProgramCodeByCode(payload.getProgramCode().getCode()));

        logoutAsSystem(ctx);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @RequestMapping(value = "/programCodes", method = RequestMethod.POST)
    public ResponseEntity<String> saveProgramCode(@RequestBody ProgramCodePayload payload) {
        SecurityContext ctx = loginAsSystem();

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
        SecurityContext ctx = loginAsSystem();

        AcFacultyCode facultyCode = new AcFacultyCodeImpl();
        facultyCode.setCode(payload.getCode());
        facultyCode.setDescription(payload.getDescription());

        logoutAsSystem(ctx);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

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
