package my.edu.umk.pams.account.common.service;

import my.edu.umk.pams.account.common.model.*;

import java.util.List;

public interface CommonService {

    //====================================================================================================
    // COUNTRY CODE
    //====================================================================================================

    AcCountryCode findCountryCodeById(Long id);

    AcCountryCode findCountryCodeByCode(String code);

    List<AcCountryCode> findCountryCodes(String filter, Integer offset, Integer limit);

    Integer countCountryCode(String filter);

    void saveCountryCode(AcCountryCode countryCode);

    void updateCountryCode(AcCountryCode countryCode);

    void removeCountryCode(AcCountryCode countryCode);


    //====================================================================================================
    // STATE CODE
    //====================================================================================================

    AcStateCode findStateCodeById(Long id);

    AcStateCode findStateCodeByCode(String code);

    List<AcStateCode> findStateCodes(Integer offset, Integer limit);

    List<AcStateCode> findStateCodes(String filter, Integer offset, Integer limit);

    List<AcStateCode> findStateCodes(AcCountryCode countryCode, Integer offset, Integer limit);

    List<AcStateCode> findStateCodes(String filter, AcCountryCode countryCode, Integer offset, Integer limit);

    Integer countStateCode();

    Integer countStateCode(String filter);

    Integer countStateCode(AcCountryCode countryCode);

    Integer countStateCode(String filter, AcCountryCode countryCode);

    void saveStateCode(AcStateCode stateCode);

    void updateStateCode(AcStateCode stateCode);

    void removeStateCode(AcStateCode stateCode);

    //====================================================================================================
    // DISTRICT CODE
    //====================================================================================================

    AcDistrictCode findDistrictCodeById(Long id);

    AcDistrictCode findDistrictCodeByCode(String code);

    List<AcDistrictCode> findDistrictCodes(AcStateCode stateCod, Integer offset, Integer limit);

    List<AcDistrictCode> findDistrictCodes(String filter, AcStateCode stateCode, Integer offset, Integer limit);

    Integer countDistrictCode(AcStateCode stateCode);

    Integer countDistrictCode(String filter, AcStateCode stateCode);

    void saveDistrictCode(AcDistrictCode districtCode);

    void updateDistrictCode(AcDistrictCode districtCode);

    void removeDistrictCode(AcDistrictCode districtCode);

    //====================================================================================================
    // CITY CODE
    //====================================================================================================

    AcCityCode findCityCodeById(Long id);

    AcCityCode findCityCodeByCode(String code);

    List<AcCityCode> findCityCodes(AcStateCode stateCode, Integer offset, Integer limit);

    List<AcCityCode> findCityCodes(String filter, AcStateCode stateCode, Integer offset, Integer limit);

    Integer countCityCode(AcStateCode stateCode);

    Integer countCityCode(String filter, AcStateCode stateCode);

    void saveCityCode(AcCityCode cityCode);

    void updateCityCode(AcCityCode cityCode);

    void removeCityCode(AcCityCode cityCode);

    //====================================================================================================
    // FACULTY CODE
    //====================================================================================================

    AcFacultyCode findFacultyCodeById(Long id);

    AcFacultyCode findFacultyCodeByCode(String code);

    List<AcFacultyCode> findFacultyCodes();
    
    List<AcFacultyCode> findFacultyCodes(String filter, Integer offset, Integer limit);

    Integer countFacultyCode();

    Integer countFacultyCode(String filter);

    void saveFacultyCode(AcFacultyCode facultyCode);

    void updateFacultyCode(AcFacultyCode facultyCode);

    void removeFacultyCode(AcFacultyCode facultyCode);

    //====================================================================================================
    // STUDY CENTER CODE
    //====================================================================================================

    AcStudyCenterCode findStudyCenterCodeById(Long id);

    AcStudyCenterCode findStudyCenterCodeByCode(String code);

    List<AcStudyCenterCode> findStudyCenterCodes();

    List<AcStudyCenterCode> findStudyCenterCodes(String filter, Integer offset, Integer limit);

    Integer countStudyCenterCode();

    Integer countStudyCenterCode(String filter);

    void saveStudyCenterCode(AcStudyCenterCode studyCenterCode);

    void updateStudyCenterCode(AcStudyCenterCode studyCenterCode);

    void removeStudyCenterCode(AcStudyCenterCode studyCenterCode);

    //====================================================================================================
    // BANK CODE
    //====================================================================================================

    AcBankCode findBankCodeById(Long id);

    AcBankCode findBankCodeByCode(String code);

    List<AcBankCode> findBankCodes();

    List<AcBankCode> findBankCodes(String filter, Integer offset, Integer limit);

    Integer countBankCode();

    Integer countBankCode(String filter);

    void saveBankCode(AcBankCode bankCode);

    void updateBankCode(AcBankCode bankCode);

    void removeBankCode(AcBankCode bankCode);


    //====================================================================================================
    // PROGRAM CODE
    //====================================================================================================

    AcProgramCode findProgramCodeById(Long id);

    AcProgramCode findProgramCodeByCode(String code);

    AcProgramCode findProgramCodeByUpuCode(String upuCode);

    List<AcProgramCode> findProgramCodes(AcFacultyCode facultyCode);

    List<AcProgramCode> findProgramCodes(String filter, Integer offset, Integer limit);

    Integer countProgramCode();

    Integer countProgramCode(String filter);

    void saveProgramCode(AcProgramCode programCode);

    void updateProgramCode(AcProgramCode programCode);

    void removeProgramCode(AcProgramCode programCode);


    //====================================================================================================
    // NATIONALITY CODE
    //====================================================================================================

    AcNationalityCode findNationalityCodeById(Long id);

    AcNationalityCode findNationalityCodeByCode(String code);

    List<AcNationalityCode> findNationalityCodes();

    List<AcNationalityCode> findNationalityCodes(String filter, Integer offset, Integer limit);

    Integer countNationalityCode();

    Integer countNationalityCode(String filter);

    void saveNationalityCode(AcNationalityCode nationalityCode);

    void updateNationalityCode(AcNationalityCode nationalityCode);

    void removeNationalityCode(AcNationalityCode nationalityCode);


    //====================================================================================================
    // RESIDENCY CODE
    //====================================================================================================

    AcResidencyCode findResidencyCodeById(Long id);

    AcResidencyCode findResidencyCodeByCode(String code);

    List<AcResidencyCode> findResidencyCodes();

    List<AcResidencyCode> findResidencyCodes(String filter, Integer offset, Integer limit);

    Integer countResidencyCode();

    Integer countResidencyCode(String filter);

    void saveResidencyCode(AcResidencyCode residencyCode);

    void updateResidencyCode(AcResidencyCode residencyCode);

    void removeResidencyCode(AcResidencyCode residencyCode);


    //====================================================================================================
    // COHORT CODE
    //====================================================================================================

    AcCohortCode findCohortCodeById(Long id);

    AcCohortCode findCohortCodeByCode(String code);

    List<AcCohortCode> findCohortCodes();

    List<AcCohortCode> findCohortCodes(Integer offset, Integer limit);

    List<AcCohortCode> findCohortCodes(String filter, Integer offset, Integer limit);

    Integer countCohortCode();

    Integer countCohortCode(String filter);

    void saveCohortCode(AcCohortCode cohortCode);

    void updateCohortCode(AcCohortCode cohortCode);

    void removeCohortCode(AcCohortCode cohortCode);

    // ====================================================================================================
    // STUDY MODE
    // ====================================================================================================

    AcStudyMode findStudyModeById(Long id);

    AcStudyMode findStudyModeByCode(String code);

    List<AcStudyMode> findStudyModes();

    List<AcStudyMode> findStudyModes(String filter, Integer offset, Integer limit);

    Integer countStudyMode();

    Integer countStudyMode(String filter);

    void saveStudyMode(AcStudyMode studyMode);

    void updateStudyMode(AcStudyMode studyMode);

    void removeStudyMode(AcStudyMode studyMode);

    //====================================================================================================
    // TAX CODE
    //====================================================================================================

    AcTaxCode findTaxCodeById(Long id);

    AcTaxCode findTaxCodeByCode(String code);

    List<AcTaxCode> findTaxCodes();

    List<AcTaxCode> findTaxCodes(String filter, Integer offset, Integer limit);

    Integer countTaxCode();

    Integer countTaxCode(String filter);

    void saveTaxCode(AcTaxCode taxCode);

    void updateTaxCode(AcTaxCode taxCode);

    void removeTaxCode(AcTaxCode taxCode);
    
    //====================================================================================================
    // SECURITY Charge CODE 
    //====================================================================================================
    
    AcSecurityChargeCode findSecurityChargeCodeById(Long id);

    AcSecurityChargeCode findSecurityChargeCodeByCode(String code);

    List<AcSecurityChargeCode> findSecurityChargeCodes();

    List<AcSecurityChargeCode> findSecurityChargeCodes(String filter, Integer offset, Integer limit);

    Integer countSecurityChargeCode();

    Integer countSecurityChargeCode(String filter);

    void saveSecurityChargeCode(AcSecurityChargeCode securityChargeCode);

    void updateSecurityChargeCode(AcSecurityChargeCode securityChargeCode);

    void removeSecurityChargeCode(AcSecurityChargeCode securityChargeCode);

	void calculateSecurityChargeCodeNetAmount(AcSecurityChargeCode securityChargeCode);

	List<AcSecurityChargeCode> findSecurityChargeCodesByActive(Boolean active);




    
}
