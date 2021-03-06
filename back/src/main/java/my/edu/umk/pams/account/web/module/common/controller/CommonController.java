package my.edu.umk.pams.account.web.module.common.controller;

import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.common.model.*;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.security.integration.AcAutoLoginToken;
import my.edu.umk.pams.account.web.module.common.vo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static java.lang.Boolean.TRUE;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/common")
public class CommonController {

    @Autowired
    private CommonService commonService;
    
    @Autowired
    private AccountService accountService;

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
                commonService.findCohortCodes("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }

    @RequestMapping(value = "/cohortCodes/{code}", method = RequestMethod.GET)
    public ResponseEntity<CohortCode> findCohortCodeByCode(@PathVariable String code) {
        return new ResponseEntity<CohortCode>(commonTransformer.toCohortCodeVo(
                commonService.findCohortCodeByCode(code)), HttpStatus.OK);
    }

    @RequestMapping(value = "/cohortCodes", method = RequestMethod.POST)
    public ResponseEntity<String> saveCohortCode(@RequestBody CohortCode vo) {
        

        AcCohortCode cohortCode = new AcCohortCodeImpl();
        cohortCode.setCode(vo.getCode());
        cohortCode.setDescription(vo.getDescription());
        commonService.saveCohortCode(cohortCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/cohortCodes/{code}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateCohortCode(@PathVariable String code, @RequestBody CohortCode vo) {
        

        AcCohortCode cohortCode = commonService.findCohortCodeById(vo.getId());
        cohortCode.setCode(vo.getCode());
        cohortCode.setDescription(vo.getDescription());
        commonService.updateCohortCode(cohortCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/cohortCodes/{code}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeCohortCode(@PathVariable String code) {
        

        AcCohortCode cohortCode = commonService.findCohortCodeByCode(code);
        commonService.removeCohortCode(cohortCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
    //====================================================================================================
    // RESIDENCY_CODE
    //====================================================================================================

    @RequestMapping(value = "/studyCenterCodes", method = RequestMethod.GET)
    public ResponseEntity<List<StudyCenterCode>> findStudyCenterCodes() {
        return new ResponseEntity<List<StudyCenterCode>>(commonTransformer.toStudyCenterCodeVos(
                commonService.findStudyCenterCodes("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }

    @RequestMapping(value = "/studyCenterCodes/{code}", method = RequestMethod.GET)
    public ResponseEntity<StudyCenterCode> findStudyCenterCodeByCode(@PathVariable String code) {
        return new ResponseEntity<StudyCenterCode>(commonTransformer.toStudyCenterCodeVo(
                commonService.findStudyCenterCodeByCode(code)), HttpStatus.OK);
    }

    //====================================================================================================
    // RESIDENCY_CODE
    //====================================================================================================

    @RequestMapping(value = "/residencyCodes", method = RequestMethod.GET)
    public ResponseEntity<List<ResidencyCode>> findResidencyCodes() {
        return new ResponseEntity<List<ResidencyCode>>(commonTransformer.toResidencyCodeVos(
                commonService.findResidencyCodes("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }

    @RequestMapping(value = "/residencyCodes/{code}", method = RequestMethod.GET)
    public ResponseEntity<ResidencyCode> findResidencyCodeByCode(@PathVariable String code) {
        return new ResponseEntity<ResidencyCode>(commonTransformer.toResidencyCodeVo(
                commonService.findResidencyCodeByCode(code)), HttpStatus.OK);
    }

    @RequestMapping(value = "/residencyCodes", method = RequestMethod.POST)
    public ResponseEntity<String> saveResidencyCode(@RequestBody ResidencyCode vo) {
        

        AcResidencyCode residencyCode = new AcResidencyCodeImpl();
        residencyCode.setCode(vo.getCode());
        residencyCode.setDescription(vo.getDescription());
        commonService.saveResidencyCode(residencyCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/residencyCodes/{code}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateResidencyCode(@PathVariable String code, @RequestBody ResidencyCode vo) {
        

        AcResidencyCode residencyCode = commonService.findResidencyCodeById(vo.getId());
        residencyCode.setCode(vo.getCode());
        residencyCode.setDescription(vo.getDescription());
        commonService.updateResidencyCode(residencyCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/residencyCodes/{code}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeResidencyCode(@PathVariable String code) {
        

        AcResidencyCode residencyCode = commonService.findResidencyCodeByCode(code);
        commonService.removeResidencyCode(residencyCode);
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
        

        AcStudyMode studyMode = new AcStudyModeImpl();
        studyMode.setCode(vo.getCode());
        studyMode.setDescriptionMs(vo.getDescriptionMs());
        studyMode.setDescriptionEn(vo.getDescriptionEn());
        commonService.saveStudyMode(studyMode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/studyModes/{mode}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateStudyMode(@PathVariable String mode, @RequestBody StudyMode vo) {
        

        AcStudyMode studyMode = commonService.findStudyModeById(vo.getId());
        studyMode.setCode(vo.getCode());
        studyMode.setDescriptionMs(vo.getDescriptionMs());
        studyMode.setDescriptionEn(vo.getDescriptionEn());
        commonService.updateStudyMode(studyMode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/studyModes/{mode}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeStudyMode(@PathVariable String mode) {
        

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
                commonService.findFacultyCodes("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }


    @RequestMapping(value = "/facultyCodes/{code}", method = RequestMethod.GET)
    public ResponseEntity<FacultyCode> findFacultyCode(@PathVariable String code) {
        return new ResponseEntity<FacultyCode>(commonTransformer.toFacultyCodeVo(
                commonService.findFacultyCodeByCode(code)), HttpStatus.OK);
    }


    @RequestMapping(value = "/facultyCodes", method = RequestMethod.POST)
    public ResponseEntity<String> saveFacultyCode(@RequestBody FacultyCode vo) {
        

        AcFacultyCode facultyCode = new AcFacultyCodeImpl();
        facultyCode.setCode(vo.getCode());
        facultyCode.setDescription(vo.getDescription());
        commonService.saveFacultyCode(facultyCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/facultyCodes/{code}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateFacultyCode(@PathVariable String code, @RequestBody FacultyCode vo) {
        

        AcFacultyCode facultyCode = commonService.findFacultyCodeById(vo.getId());
        facultyCode.setCode(vo.getCode());
        facultyCode.setDescription(vo.getDescription());
        commonService.updateFacultyCode(facultyCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/facultyCodes/{code}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeFacultyCode(@PathVariable String code) {
        

        AcFacultyCode facultyCode = commonService.findFacultyCodeByCode(code);
        commonService.removeFacultyCode(facultyCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
    //====================================================================================================
    // BANK_CODE
    //====================================================================================================

    @RequestMapping(value = "/bankCodes", method = RequestMethod.GET)
    public ResponseEntity<List<BankCode>> findBankCodes() {
        return new ResponseEntity<List<BankCode>>(commonTransformer.toBankCodeVos(
                commonService.findBankCodes("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }

    @RequestMapping(value = "/bankCodes/{code}", method = RequestMethod.GET)
    public ResponseEntity<BankCode> findBankCodeByCode(@PathVariable String code) {
        return new ResponseEntity<BankCode>(commonTransformer.toBankCodeVo(
                commonService.findBankCodeByCode(code)), HttpStatus.OK);
    }

    @RequestMapping(value = "/bankCodes", method = RequestMethod.POST)
    public ResponseEntity<String>saveBankCode(@RequestBody BankCode vo) {
        

        AcBankCode bankCode = new AcBankCodeImpl();
        bankCode.setCode(vo.getCode());
        bankCode.setName(vo.getName());
        bankCode.setSwiftCode(vo.getSwiftCode());
//        bankCode.setIbgCode(vo.getIbgCode());
        commonService.saveBankCode(bankCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/bankCodes/{code}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateBankCode(@PathVariable String code, @RequestBody BankCode vo) {
        

        AcBankCode bankCode = commonService.findBankCodeById(vo.getId());
        bankCode.setCode(vo.getCode());
        bankCode.setName(vo.getName());
        bankCode.setSwiftCode(vo.getSwiftCode());
//        bankCode.setIbgCode(vo.getIbgCode());
        commonService.updateBankCode(bankCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/bankCodes/{code}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeBankCode(@PathVariable String code) {
        

        AcBankCode bankCode = commonService.findBankCodeByCode(code);
        commonService.removeBankCode(bankCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
    //====================================================================================================
    // TAX_CODE
    //====================================================================================================

    @RequestMapping(value = "/taxCodes", method = RequestMethod.GET)
    public ResponseEntity<List<TaxCode>> findTaxCodes() {
        return new ResponseEntity<List<TaxCode>>(commonTransformer.toTaxCodeVos(
                commonService.findTaxCodes("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }

    @RequestMapping(value = "/taxCodes/{code}", method = RequestMethod.GET)
    public ResponseEntity<TaxCode> findTaxCodeByCode(@PathVariable String code) {
        return new ResponseEntity<TaxCode>(commonTransformer.toTaxCodeVo(
                commonService.findTaxCodeByCode(code)), HttpStatus.OK);
    }

    @RequestMapping(value = "/taxCodes", method = RequestMethod.POST)
    public ResponseEntity<String> saveTaxCode(@RequestBody TaxCode vo) {
        

        AcTaxCode taxCode = new AcTaxCodeImpl();
        taxCode.setCode(vo.getCode());
        taxCode.setDescription(vo.getDescription());
        taxCode.setTaxRate(vo.getTaxRate());
        taxCode.setActive(vo.getActive());
        commonService.saveTaxCode(taxCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/taxCodes/{code}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateTaxCode(@PathVariable String code, @RequestBody TaxCode vo) {
        

        AcTaxCode taxCode = commonService.findTaxCodeById(vo.getId());
        taxCode.setCode(vo.getCode());
        taxCode.setDescription(vo.getDescription());
        taxCode.setTaxRate(vo.getTaxRate());
        taxCode.setActive(vo.getActive());
        commonService.updateTaxCode(taxCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/taxCodes/{code}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeTaxCode(@PathVariable String code) {
        

        AcTaxCode taxCode = commonService.findTaxCodeByCode(code);
        commonService.removeTaxCode(taxCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/taxCodesActive", method = RequestMethod.GET)
    public ResponseEntity<List<TaxCode>> findTaxCodesByActive() {
        List<AcTaxCode> taxCodes = commonService.findTaxCodesByActive(TRUE);
        return new ResponseEntity<List<TaxCode>>(commonTransformer.toTaxCodeVos(taxCodes), HttpStatus.OK);
    }
    
    //====================================================================================================
    // SECURITY Charge CODE
    //====================================================================================================

    @RequestMapping(value = "/securityChargeCodes", method = RequestMethod.GET)
    public ResponseEntity<List<SecurityChargeCode>> findSecurityChargeCode() {
        return new ResponseEntity<List<SecurityChargeCode>>(commonTransformer.toSecurityChargeCodeVos(
                commonService.findSecurityChargeCodes("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }

    @RequestMapping(value = "/securityChargeCodesActive", method = RequestMethod.GET)
    public ResponseEntity<List<SecurityChargeCode>> findSecurityChargeCodesByActive() {
        List<AcSecurityChargeCode> securityChargeCodes = commonService.findSecurityChargeCodesByActive(TRUE);
        return new ResponseEntity<List<SecurityChargeCode>>(commonTransformer.toSecurityChargeCodeVos(securityChargeCodes), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/securityChargeCode/{code}", method = RequestMethod.GET)
    public ResponseEntity<SecurityChargeCode> findSecurityChargeCode(@PathVariable String code) {
        return new ResponseEntity<SecurityChargeCode>(commonTransformer.toSecurityChargeCodeVo(
                commonService.findSecurityChargeCodeByCode(code)), HttpStatus.OK);
    }


    @RequestMapping(value = "/securityChargeCode", method = RequestMethod.POST)
    public ResponseEntity<String> saveSecurityChargeCode(@RequestBody SecurityChargeCode vo) {
        

        AcSecurityChargeCode securityChargeCode = new AcSecurityChargeCodeImpl();
        securityChargeCode.setSection(vo.getSection());
        securityChargeCode.setDescription(vo.getDescription());
        securityChargeCode.setOffense(vo.getOffense());
        securityChargeCode.setOffenseDescription(vo.getOffenseDescription());
        securityChargeCode.setAmount(vo.getAmount());
        securityChargeCode.setAmountDescription(vo.getAmountDescription());                
        securityChargeCode.setActive(vo.getActive());
        securityChargeCode.setInclusive(vo.getInclusive());        
        if (null != vo.getTaxCode())
        	securityChargeCode.setTaxCode(commonService.findTaxCodeById(vo.getTaxCode().getId()));
        commonService.calculateSecurityChargeCodeNetAmount(securityChargeCode);
        securityChargeCode.setBalanceAmount(vo.getNetAmount());
        commonService.saveSecurityChargeCode(securityChargeCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/securityChargeCode/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateSecurityChargeCode(@PathVariable Long id, @RequestBody SecurityChargeCode vo) {
        

        AcSecurityChargeCode securityChargeCode = commonService.findSecurityChargeCodeById(id);
        securityChargeCode.setSection(vo.getSection());
        securityChargeCode.setDescription(vo.getDescription());
        securityChargeCode.setOffense(vo.getOffense());
        securityChargeCode.setOffenseDescription(vo.getOffenseDescription());
        securityChargeCode.setAmount(vo.getAmount());
        securityChargeCode.setAmountDescription(vo.getAmountDescription());
        securityChargeCode.setActive(vo.getActive());
        securityChargeCode.setInclusive(vo.getInclusive());
        if (null != vo.getTaxCode())
        	securityChargeCode.setTaxCode(commonService.findTaxCodeById(vo.getTaxCode().getId()));
        commonService.calculateSecurityChargeCodeNetAmount(securityChargeCode);
        securityChargeCode.setBalanceAmount(vo.getNetAmount());
        commonService.updateSecurityChargeCode(securityChargeCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    //====================================================================================================
    // PROGRAM_CODE
    //====================================================================================================
    @RequestMapping(value = "/programCodes", method = RequestMethod.GET)
    public ResponseEntity<List<ProgramCode>> findProgramCodes() {
        return new ResponseEntity<List<ProgramCode>>(commonTransformer.toProgramCodeVos(
                commonService.findProgramCodes("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }
    
    //====================================================================================================
    // PROGRAM_LEVEL
    //====================================================================================================
    @RequestMapping(value = "/programLevels", method = RequestMethod.GET)
    public ResponseEntity<List<ProgramLevel>> findProgramLevels() {
        return new ResponseEntity<List<ProgramLevel>>(commonTransformer.toProgramLevelVos(
                commonService.findProgramLevels("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }
}
