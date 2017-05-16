package my.edu.umk.pams.account.web.module.common.controller;

import my.edu.umk.pams.account.common.model.*;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.security.integration.AcAutoLoginToken;
import my.edu.umk.pams.account.web.module.common.vo.CohortCode;
import my.edu.umk.pams.account.web.module.common.vo.FacultyCode;
import my.edu.umk.pams.account.web.module.common.vo.StudyMode;
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


    //====================================================================================================
    // STUDY_MODE
    //====================================================================================================

    @RequestMapping(value = "/studyModes", method = RequestMethod.GET)
    public ResponseEntity<List<StudyMode>> findStudyModes() {
        return new ResponseEntity<List<StudyMode>>(commonTransformer.toStudyModeVos(
                commonService.findStudyModes()), HttpStatus.OK);
    }

    @RequestMapping(value = "/studyModes/{code}", method = RequestMethod.GET)
    public ResponseEntity<StudyMode> findStudyModeByMode(@PathVariable String code) {
        return new ResponseEntity<StudyMode>(commonTransformer.toStudyModeVo(
                commonService.findStudyModeByCode(code)), HttpStatus.OK);
    }

    @RequestMapping(value = "/studyModes", method = RequestMethod.POST)
    public ResponseEntity<String> saveStudyMode(@RequestBody StudyMode vo) {
        dummyLogin();

        AcStudyMode studyMode = new AcStudyModeImpl();
        studyMode.setCode(vo.getCode());
        studyMode.setDescriptionMs(vo.getDescriptionMs());
        studyMode.setDescriptionEn(vo.getDescriptionEn());
        commonService.saveStudyMode(studyMode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/studyModes/{mode}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateStudyMode(@PathVariable String mode, @RequestBody StudyMode vo) {
        dummyLogin();

        AcStudyMode studyMode = commonService.findStudyModeById(vo.getId());
        studyMode.setCode(vo.getCode());
        studyMode.setDescriptionMs(vo.getDescriptionMs());
        studyMode.setDescriptionEn(vo.getDescriptionEn());
        commonService.updateStudyMode(studyMode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/studyModes/{mode}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeStudyMode(@PathVariable String mode) {
        dummyLogin();

        AcStudyMode studyMode = commonService.findStudyModeByCode(mode);
        commonService.removeStudyMode(studyMode);
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
    
    // ====================================================================================================
    // FACULTY CODES
    //====================================================================================================

    @RequestMapping(value = "/facultyCodes", method = RequestMethod.GET)
    public ResponseEntity<List<FacultyCode>> findFacultyCodes() {
        return new ResponseEntity<List<FacultyCode>>(commonTransformer.toFacultyCodeVos(
                commonService.findFacultyCodes()), HttpStatus.OK);
    }

    @RequestMapping(value = "/facultyCodes/{code}", method = RequestMethod.GET)
    public ResponseEntity<FacultyCode> findFacultyCode(@PathVariable String code) {
        return new ResponseEntity<FacultyCode>(commonTransformer.toFacultyCodeVo(
                commonService.findFacultyCodeByCode(code)), HttpStatus.OK);
    }


    @RequestMapping(value = "/facultyCodes", method = RequestMethod.POST)
    public ResponseEntity<String> saveFacultyCode(@RequestBody FacultyCode vo) {
        dummyLogin();

        AcFacultyCode facultyCode = new AcFacultyCodeImpl();
        facultyCode.setCode(vo.getCode());
        facultyCode.setDescription(vo.getDescription());
        commonService.saveFacultyCode(facultyCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/facultyCodes/{code}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateFacultyCode(@PathVariable String code, @RequestBody FacultyCode vo) {
        dummyLogin();

        AcFacultyCode facultyCode = commonService.findFacultyCodeById(vo.getId());
        facultyCode.setCode(vo.getCode());
        facultyCode.setDescription(vo.getDescription());
        commonService.updateFacultyCode(facultyCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/facultyCodes/{code}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeFacultyCode(@PathVariable String code) {
        dummyLogin();

        AcFacultyCode facultyCode = commonService.findFacultyCodeByCode(code);
        commonService.removeFacultyCode(facultyCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
}
