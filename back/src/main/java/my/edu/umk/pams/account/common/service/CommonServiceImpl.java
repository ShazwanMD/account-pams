package my.edu.umk.pams.account.common.service;

import my.edu.umk.pams.account.common.dao.*;
import my.edu.umk.pams.account.common.model.*;
import my.edu.umk.pams.account.util.Util;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService {

    @Autowired
    private AcCountryCodeDao countryCodeDao;

    @Autowired
    private AcStateCodeDao stateCodeDao;

    @Autowired
    private AcCityCodeDao cityCodeDao;

    @Autowired
    private AcDistrictCodeDao districtCodeDao;

    @Autowired
    private AcProgramCodeDao programCodeDao;

    @Autowired
    private AcBankCodeDao bankCodeDao;

    @Autowired
    private AcNationalityCodeDao nationalityCodeDao;

    @Autowired
    private AcResidencyCodeDao residencyCodeDao;

    @Autowired
    private AcResidencyCodeDao residenceCodeDao;

    @Autowired
    private AcFacultyCodeDao facultyCodeDao;

    @Autowired
    private AcStudyCenterCodeDao studyCenterCodeDao;

    @Autowired
    private SessionFactory sessionFactory;

    //====================================================================================================
    // COUNTRY CODE
    //====================================================================================================


    @Override
    public AcCountryCode findCountryCodeById(Long id) {
        return countryCodeDao.findById(id);
    }

    @Override
    public AcCountryCode findCountryCodeByCode(String code) {
        return countryCodeDao.findByCode(code);
    }

    @Override
    public List<AcCountryCode> findCountryCodes(String filter, Integer offset, Integer limit) {
        return countryCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countCountryCode(String filter) {
        return countryCodeDao.count(filter);
    }


    @Override
    public void saveCountryCode(AcCountryCode countryCode) {
        countryCodeDao.save(countryCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateCountryCode(AcCountryCode countryCode) {
        countryCodeDao.update(countryCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeCountryCode(AcCountryCode countryCode) {
        countryCodeDao.remove(countryCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    //====================================================================================================
    // STATE CODE
    //====================================================================================================

    @Override
    public AcStateCode findStateCodeById(Long id) {
        return stateCodeDao.findById(id);
    }

    @Override
    public AcStateCode findStateCodeByCode(String code) {
        return stateCodeDao.findByCode(code);
    }

    @Override
    public List<AcStateCode> findStateCodes(Integer offset, Integer limit) {
        return stateCodeDao.find(offset, limit);
    }

    @Override
    public List<AcStateCode> findStateCodes(String filter, Integer offset, Integer limit) {
        return stateCodeDao.find(filter, offset, limit);
    }

    @Override
    public List<AcStateCode> findStateCodes(AcCountryCode countryCode, Integer offset, Integer limit) {
        return stateCodeDao.find(countryCode, offset, limit);
    }

    @Override
    public List<AcStateCode> findStateCodes(String filter, AcCountryCode countryCode, Integer offset, Integer limit) {
        return stateCodeDao.find(filter, countryCode, offset, limit);
    }

    @Override
    public Integer countStateCode() {
        return stateCodeDao.count();
    }

    @Override
    public Integer countStateCode(String filter) {
        return stateCodeDao.count(filter);
    }

    @Override
    public Integer countStateCode(AcCountryCode countryCode) {
        return stateCodeDao.count(countryCode);
    }

    @Override
    public Integer countStateCode(String filter, AcCountryCode countryCode) {
        return stateCodeDao.count(filter, countryCode);
    }

    @Override
    public void saveStateCode(AcStateCode stateCode) {
        stateCodeDao.save(stateCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateStateCode(AcStateCode stateCode) {
        stateCodeDao.update(stateCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeStateCode(AcStateCode stateCode) {
        stateCodeDao.remove(stateCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }


    //====================================================================================================
    // DISTRICT CODE
    //====================================================================================================


    @Override
    public AcDistrictCode findDistrictCodeById(Long id) {
        return districtCodeDao.findById(id);
    }

    @Override
    public AcDistrictCode findDistrictCodeByCode(String code) {
        return districtCodeDao.findByCode(code);
    }

    @Override
    public List<AcDistrictCode> findDistrictCodes(AcStateCode stateCode, Integer offset, Integer limit) {
        return districtCodeDao.find(offset, limit);
    }

    @Override
    public List<AcDistrictCode> findDistrictCodes(String filter, AcStateCode stateCode, Integer offset, Integer limit) {
        return districtCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countDistrictCode(AcStateCode stateCode) {
        return districtCodeDao.count();
    }

    @Override
    public Integer countDistrictCode(String filter, AcStateCode stateCode) {
        return districtCodeDao.count(filter);
    }


    @Override
    public void saveDistrictCode(AcDistrictCode districtCode) {
        districtCodeDao.save(districtCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateDistrictCode(AcDistrictCode districtCode) {
        districtCodeDao.update(districtCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeDistrictCode(AcDistrictCode districtCode) {
        districtCodeDao.remove(districtCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }


    //====================================================================================================
    // CITY CODE
    //====================================================================================================

    @Override
    public AcCityCode findCityCodeById(Long id) {
        return cityCodeDao.findById(id);
    }

    @Override
    public AcCityCode findCityCodeByCode(String code) {
        return cityCodeDao.findByCode(code);
    }

    @Override
    public List<AcCityCode> findCityCodes(AcStateCode stateCode, Integer offset, Integer limit) {
        return cityCodeDao.find(stateCode, offset, limit);
    }

    @Override
    public List<AcCityCode> findCityCodes(String filter, AcStateCode stateCode, Integer offset, Integer limit) {
        return cityCodeDao.find(filter, stateCode, offset, limit);
    }

    @Override
    public Integer countCityCode(AcStateCode stateCode) {
        return cityCodeDao.count(stateCode);
    }

    @Override
    public Integer countCityCode(String filter, AcStateCode stateCode) {
        return cityCodeDao.count(filter, stateCode);
    }

    @Override
    public void saveCityCode(AcCityCode cityCode) {
        cityCodeDao.save(cityCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateCityCode(AcCityCode cityCode) {
        cityCodeDao.update(cityCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeCityCode(AcCityCode cityCode) {
        cityCodeDao.remove(cityCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    //====================================================================================================
    // PROGRAM CODE
    //====================================================================================================

    @Override
    public AcProgramCode findProgramCodeById(Long id) {
        return programCodeDao.findById(id);
    }

    @Override
    public AcProgramCode findProgramCodeByCode(String code) {
        return programCodeDao.findByCode(code);
    }

    @Override
    public AcProgramCode findProgramCodeByUpuCode(String upuCode) {
        return programCodeDao.findByUpuCode(upuCode);
    }

    @Override
    public List<AcProgramCode> findProgramCodes() {
        return programCodeDao.find();
    }

    @Override
    public List<AcProgramCode> findProgramCodes(String filter, Integer offset, Integer limit) {
        return programCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countProgramCode() {
        return programCodeDao.count();
    }

    @Override
    public Integer countProgramCode(String filter) {
        return programCodeDao.count(filter);
    }

    @Override
    public void saveProgramCode(AcProgramCode programCode) {
        programCodeDao.save(programCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateProgramCode(AcProgramCode programCode) {
        programCodeDao.update(programCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeProgramCode(AcProgramCode programCode) {
        programCodeDao.remove(programCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }


    //====================================================================================================
    // NATIONALITY CODE
    //====================================================================================================

    @Override
    public AcNationalityCode findNationalityCodeById(Long id) {
        return nationalityCodeDao.findById(id);
    }

    @Override
    public AcNationalityCode findNationalityCodeByCode(String code) {
        return nationalityCodeDao.findByCode(code);
    }

    @Override
    public List<AcNationalityCode> findNationalityCodes() {
        return nationalityCodeDao.find();
    }

    @Override
    public List<AcNationalityCode> findNationalityCodes(String filter, Integer offset, Integer limit) {
        return nationalityCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countNationalityCode() {
        return nationalityCodeDao.count();
    }

    @Override
    public Integer countNationalityCode(String filter) {
        return nationalityCodeDao.count(filter);
    }


    @Override
    public void saveNationalityCode(AcNationalityCode nationalityCode) {
        nationalityCodeDao.save(nationalityCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateNationalityCode(AcNationalityCode nationalityCode) {
        nationalityCodeDao.update(nationalityCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeNationalityCode(AcNationalityCode nationalityCode) {
        nationalityCodeDao.remove(nationalityCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }


    //====================================================================================================
    // RESIDENCY CODE
    //====================================================================================================

    @Override
    public AcResidencyCode findResidencyCodeById(Long id) {
        return residencyCodeDao.findById(id);
    }

    @Override
    public AcResidencyCode findResidencyCodeByCode(String code) {
        return residencyCodeDao.findByCode(code);
    }

    @Override
    public List<AcResidencyCode> findResidencyCodes() {
        return residencyCodeDao.find();
    }

    @Override
    public List<AcResidencyCode> findResidencyCodes(String filter, Integer offset, Integer limit) {
        return residencyCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countResidencyCode() {
        return residencyCodeDao.count();
    }

    @Override
    public Integer countResidencyCode(String filter) {
        return residencyCodeDao.count(filter);
    }


    @Override
    public void saveResidencyCode(AcResidencyCode residencyCode) {
        residencyCodeDao.save(residencyCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateResidencyCode(AcResidencyCode residencyCode) {
        residencyCodeDao.update(residencyCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeResidencyCode(AcResidencyCode residencyCode) {
        residencyCodeDao.remove(residencyCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    //====================================================================================================
    // FACULTY CODE
    //====================================================================================================
    @Override
    public AcFacultyCode findFacultyCodeById(Long id) {
        return facultyCodeDao.findById(id);
    }

    @Override
    public AcFacultyCode findFacultyCodeByCode(String code) {
        return facultyCodeDao.findByCode(code);
    }

    @Override
    public List<AcFacultyCode> findFacultyCodes() {
        return facultyCodeDao.find();
    }

    @Override
    public List<AcFacultyCode> findFacultyCodes(String filter, Integer offset, Integer limit) {
        return facultyCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countFacultyCode() {
        return facultyCodeDao.count();
    }

    @Override
    public Integer countFacultyCode(String filter) {
        return facultyCodeDao.count(filter);
    }

    @Override
    public void saveFacultyCode(AcFacultyCode facultyCode) {
        facultyCodeDao.save(facultyCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateFacultyCode(AcFacultyCode facultyCode) {
        facultyCodeDao.update(facultyCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeFacultyCode(AcFacultyCode facultyCode) {
        facultyCodeDao.remove(facultyCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    //====================================================================================================
    // STUDY CENTER CODE
    //====================================================================================================

    @Override
    public AcStudyCenterCode findStudyCenterCodeById(Long id) {
        return studyCenterCodeDao.findById(id);
    }

    @Override
    public AcStudyCenterCode findStudyCenterCodeByCode(String code) {
        return studyCenterCodeDao.findByCode(code);
    }

    @Override
    public List<AcStudyCenterCode> findStudyCenterCodes() {
        return studyCenterCodeDao.find();
    }

    @Override
    public List<AcStudyCenterCode> findStudyCenterCodes(String filter, Integer offset, Integer limit) {
        return studyCenterCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countStudyCenterCode() {
        return studyCenterCodeDao.count();
    }

    @Override
    public Integer countStudyCenterCode(String filter) {
        return studyCenterCodeDao.count(filter);
    }


    @Override
    public void saveStudyCenterCode(AcStudyCenterCode studyCenterCode) {
        studyCenterCodeDao.save(studyCenterCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateStudyCenterCode(AcStudyCenterCode studyCenterCode) {
        studyCenterCodeDao.update(studyCenterCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeStudyCenterCode(AcStudyCenterCode studyCenterCode) {
        studyCenterCodeDao.remove(studyCenterCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }


    //====================================================================================================
    // BANK CODE
    //====================================================================================================

    @Override
    public AcBankCode findBankCodeById(Long id) {
        return bankCodeDao.findById(id);
    }

    @Override
    public AcBankCode findBankCodeByCode(String code) {
        return bankCodeDao.findByCode(code);
    }

    @Override
    public List<AcBankCode> findBankCodes() {
        return bankCodeDao.find();
    }

    @Override
    public List<AcBankCode> findBankCodes(String filter, Integer offset, Integer limit) {
        return bankCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countBankCode() {
        return bankCodeDao.count();
    }

    @Override
    public Integer countBankCode(String filter) {
        return bankCodeDao.count(filter);
    }


    @Override
    public void saveBankCode(AcBankCode bankCode) {
        bankCodeDao.save(bankCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateBankCode(AcBankCode bankCode) {
        bankCodeDao.update(bankCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeBankCode(AcBankCode bankCode) {
        bankCodeDao.remove(bankCode, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }
}
