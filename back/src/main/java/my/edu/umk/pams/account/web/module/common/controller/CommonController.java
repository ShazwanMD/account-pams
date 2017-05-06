package my.edu.umk.pams.account.web.module.common.controller;

import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcCohortCodeImpl;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.security.integration.AcAutoLoginToken;
import my.edu.umk.pams.account.web.module.common.vo.CohortCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/common")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private CommonTransformer commonTransformer;

    @Autowired
    private AuthenticationManager authenticationManager;

    //====================================================================================================
    // COHORT_CODE
    //====================================================================================================

    @RequestMapping(value = "/cohortCodes", method = RequestMethod.GET)
    public ResponseEntity<List<CohortCode>> findCohortCodes() {
        return new ResponseEntity<List<CohortCode>>(commonTransformer.toCohortCodeVos(
                commonService.findCohortCodes()), HttpStatus.OK);
    }

    @RequestMapping(value = "/cohortCodes/{code}", method = RequestMethod.GET)
    public ResponseEntity<CohortCode> findCohortCodeByCode(@PathVariable String code) {
        return new ResponseEntity<CohortCode>(commonTransformer.toCohortCodeVo(
                commonService.findCohortCodeByCode(code)), HttpStatus.OK);
    }

    @RequestMapping(value = "/cohortCodes", method = RequestMethod.POST)
    public ResponseEntity<String> saveCohortCode(@RequestBody CohortCode vo) {
        dummyLogin();

        AcCohortCode cohortCode = new AcCohortCodeImpl();
        cohortCode.setCode(vo.getCode());
        cohortCode.setDescription(vo.getDescription());
        commonService.saveCohortCode(cohortCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/cohortCodes/{code}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateCohortCode(@PathVariable String code, @RequestBody CohortCode vo) {
        dummyLogin();

        AcCohortCode cohortCode = commonService.findCohortCodeById(vo.getId());
        cohortCode.setCode(vo.getCode());
        cohortCode.setDescription(vo.getDescription());
        commonService.updateCohortCode(cohortCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/cohortCodes/{code}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeCohortCode(@PathVariable String code) {
        dummyLogin();

        AcCohortCode cohortCode = commonService.findCohortCodeByCode(code);
        commonService.removeCohortCode(cohortCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    // ====================================================================================================
    // PRIVATE METHODS
    // ====================================================================================================

    private void dummyLogin() {
        AcAutoLoginToken token = new AcAutoLoginToken("root");
        Authentication authed = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authed);
    }
}
