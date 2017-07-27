package my.edu.umk.pams.account.web.module.integration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.identity.service.IdentityService;

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
    // COHORT
    // ====================================================================================================

    @RequestMapping(value = "/cohortCode", method = RequestMethod.POST)
    public ResponseEntity<String> saveCohortCode() {
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @RequestMapping(value = "/programCode", method = RequestMethod.POST)
    public ResponseEntity<String> saveProgramCode() {
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @RequestMapping(value = "/facultyCode", method = RequestMethod.POST)
    public ResponseEntity<String> saveFacultyCode() {
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }
}
