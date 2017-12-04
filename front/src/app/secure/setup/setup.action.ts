import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class SetupActions {

//===========================================================================================//
//  CHANGE TITLE                                                                             //
//===========================================================================================//
//1------------------------------------------------------------------------------------------//
  static CHANGE_TITLE = '[Setup] Change Title';

  changeTitle(title): Action {
    console.log("changeTitle");
    return {
      type: SetupActions.CHANGE_TITLE,
      payload: title
    };
  }
//2------------------------------------------------------------------------------------------//
  static CHANGE_TITLE_SUCCESS = '[Setup] Change Title Success';

  changeTitleSuccess(title): Action {
    console.log("changeTitleSuccess");
    return {
      type: SetupActions.CHANGE_TITLE_SUCCESS,
      payload: title
    };
  }

//===========================================================================================//
// BANK CODE                                                                                 //
//===========================================================================================//
//1------------------------------------------------------------------------------------------//
  static FIND_BANK_CODES = '[Setup] Find Bank Codes';

  findBankCodes(): Action {
    console.log("findBankCodes");
    return {
      type: SetupActions.FIND_BANK_CODES,
    };
  }
//2------------------------------------------------------------------------------------------//
  static FIND_BANK_CODES_SUCCESS = '[Setup] Find Bank Codes Success';

  findBankCodesSuccess(codes): Action {
    console.log("findBankCodesSuccess");
    return {
      type: SetupActions.FIND_BANK_CODES_SUCCESS,
      payload: codes
    };
  }
//3------------------------------------------------------------------------------------------//
  static SAVE_BANK_CODE = '[Setup] Save Bank Code';

  saveBankCode(code): Action {
    console.log("saveBankCode");
    return {
      type: SetupActions.SAVE_BANK_CODE,
      payload: code
    };
  }
//4------------------------------------------------------------------------------------------//
  static SAVE_BANK_CODE_SUCCESS = '[Setup] Save Bank Code  Success';

  saveBankCodeSuccess(message): Action {
    console.log("saveBankCodeSuccess");
    return {
      type: SetupActions.SAVE_BANK_CODE_SUCCESS,
      payload: message
    };
  }
//5------------------------------------------------------------------------------------------//
  static UPDATE_BANK_CODE = '[Setup] Update Bank Code';

  updateBankCode(code): Action {
    console.log("updateBankCode");
    return {
      type: SetupActions.UPDATE_BANK_CODE,
      payload: code
    };
  }
//6------------------------------------------------------------------------------------------//
  static UPDATE_BANK_CODE_SUCCESS = '[Setup] Update Bank Code  Success';

  updateBankCodeSuccess(message): Action {
    console.log("updateBankCodeSuccess");
    return {
      type: SetupActions.UPDATE_BANK_CODE_SUCCESS,
      payload: message
    };
  }
//7------------------------------------------------------------------------------------------//
  static REMOVE_BANK_CODE = '[Setup] Remove Bank Code';

  removeBankCode(code): Action {
    console.log("removeBankCode");
    return {
      type: SetupActions.REMOVE_BANK_CODE,
      payload: code
    };
  }
//8------------------------------------------------------------------------------------------//
  static REMOVE_BANK_CODE_SUCCESS = '[Setup] Remove Bank Code  Success';

  removeBankCodeSuccess(message): Action {
    console.log("removeBankCodeSuccess");
    return {
      type: SetupActions.REMOVE_BANK_CODE_SUCCESS,
      payload: message
    };
  }

//===========================================================================================//
// MARITAL CODE                                                                              //
//===========================================================================================//
//1------------------------------------------------------------------------------------------//
  static SAVE_MARITAL_CODE = '[Setup] Save Marital Code';

  saveMaritalCode(code): Action {
    console.log("saveMaritalCode");
    return {
      type: SetupActions.SAVE_MARITAL_CODE,
      payload: code
    };
  }
//2------------------------------------------------------------------------------------------//
  static SAVE_MARITAL_CODE_SUCCESS = '[Setup] Save Marital Code Success';

  saveMaritalCodeSuccess(message): Action {
    console.log("saveMaritalCodeSuccess");
    return {
      type: SetupActions.SAVE_MARITAL_CODE_SUCCESS,
      payload: message
    };
  }
//3------------------------------------------------------------------------------------------//
  static UPDATE_MARITAL_CODE = '[Setup] Update Marital Code';

  updateMaritalCode(code): Action {
    console.log("updateMaritalCode");
    return {
      type: SetupActions.UPDATE_MARITAL_CODE,
      payload: code
    };
  }
//4------------------------------------------------------------------------------------------//
  static UPDATE_MARITAL_CODE_SUCCESS = '[Setup] Update Marital Code  Success';

  updateMaritalCodeSuccess(message): Action {
    console.log("updateMaritalCodeSuccess");
    return {
      type: SetupActions.UPDATE_MARITAL_CODE_SUCCESS,
      payload: message
    };
  }

//===========================================================================================//
// COUNTRY CODE                                                                              //
//===========================================================================================//
//1------------------------------------------------------------------------------------------//
  static FIND_COUNTRY_CODES = '[Common] Find CountryCodes';

  findCountryCodes(): Action {
    console.log("findCountryCodes");
    return {
      type: SetupActions.FIND_COUNTRY_CODES,
    };
  }
//2------------------------------------------------------------------------------------------//
  static FIND_COUNTRY_CODES_SUCCESS = '[Common] Find CountryCodes Success';

  findCountryCodesSuccess(codes): Action {
    console.log("findCountryCodesSuccess");
    return {
      type: SetupActions.FIND_COUNTRY_CODES_SUCCESS,
      payload: codes
    };
  }
//3------------------------------------------------------------------------------------------//
  static SAVE_COUNTRY_CODE = '[Setup] Save Country Code';

  saveCountryCode(code): Action {
    console.log("saveCountryCode");
    return {
      type: SetupActions.SAVE_COUNTRY_CODE,
      payload: code
    };
  }

//===========================================================================================//
// STATE CODE                                                                                //
//===========================================================================================//
//1------------------------------------------------------------------------------------------//
  static FIND_STATE_CODES_SUCCESS = '[Common] Find StateCodes Success';

  findStateCodesSuccess(codes): Action {
    console.log("findStateCodesSuccess");
    return {
      type: SetupActions.FIND_STATE_CODES_SUCCESS,
      payload: codes
    };
  }
//2------------------------------------------------------------------------------------------//
  static SAVE_STATE_CODE = '[Setup] Save State Code';

  saveStateCode(code): Action {
    console.log("saveStateCode");
    return {
      type: SetupActions.SAVE_STATE_CODE,
      payload: code
    };
  }
//3------------------------------------------------------------------------------------------//
  static FIND_STATE_CODES = '[Common] Find StateCodes';

  findStateCodes(): Action {
    console.log("findStateCodes");
    return {
      type: SetupActions.FIND_STATE_CODES,
    };
  }

//===========================================================================================//
// PROGRAM CODE                                                                              //
//===========================================================================================//
//1------------------------------------------------------------------------------------------//
  static FIND_PROGRAM_CODES = '[Common] Find ProgramCodes';

  findProgramCodes(): Action {
    console.log("findProgramCodes");
    return {
      type: SetupActions.FIND_PROGRAM_CODES,
    };
  }
//2------------------------------------------------------------------------------------------//
  static FIND_PROGRAM_CODES_SUCCESS = '[Common] Find ProgramCodes Success';

  findProgramCodesSuccess(codes): Action {
    console.log("findProgramCodesSuccess");
    return {
      type: SetupActions.FIND_PROGRAM_CODES_SUCCESS,
      payload: codes
    };
  }
//3------------------------------------------------------------------------------------------//
  static SAVE_PROGRAM_CODE = '[Setup] Save Program Code';

  saveProgramCode(code): Action {
    console.log("saveProgramCode");
    return {
      type: SetupActions.SAVE_PROGRAM_CODE,
      payload: code
    };
  }
//4------------------------------------------------------------------------------------------//
  static SAVE_PROGRAM_CODE_SUCCESS = '[Setup] Save Program Code Success';

  saveProgramCodeSuccess(message): Action {
    console.log("saveProgramCodeSuccess");
    return {
      type: SetupActions.SAVE_PROGRAM_CODE_SUCCESS,
      payload: message
    };
  }
//5------------------------------------------------------------------------------------------//
  static REMOVE_PROGRAM_CODE = '[Setup] Remove Program Code';

  removeProgramCode(code): Action {
    console.log("removeProgramCode");
    return {
      type: SetupActions.REMOVE_PROGRAM_CODE,
      payload: code
    };
  }
//6------------------------------------------------------------------------------------------//
  static REMOVE_PROGRAM_CODE_SUCCESS = '[Setup] Remove Program Code Success';

  removeProgramCodeSuccess(message): Action {
    console.log("removeProgramCodeSuccess");
    return {
      type: SetupActions.REMOVE_PROGRAM_CODE_SUCCESS,
      payload: message
    };
  }
//7------------------------------------------------------------------------------------------//
  static UPDATE_PROGRAM_CODE = '[Setup] Update Program Code';

  updateProgramCode(code): Action {
    console.log("updateProgramCode");
    return {
      type: SetupActions.UPDATE_PROGRAM_CODE,
      payload: code
    };
  }
//8------------------------------------------------------------------------------------------//
  static UPDATE_PROGRAM_CODE_SUCCESS = '[Setup] Update Program Code  Success';

  updateProgramCodeSuccess(message): Action {
    console.log("updateProgramCodeSuccess");
    return {
      type: SetupActions.UPDATE_PROGRAM_CODE_SUCCESS,
      payload: message
    };
  }
  
//===========================================================================================//
// FACULTY CODE                                                                              //
//===========================================================================================//
//1------------------------------------------------------------------------------------------//
  static SAVE_FACULTY_CODE = '[Setup] Save Faculty Code';

  saveFacultyCode(code): Action {
    console.log("saveFacultyCode");
    return {
      type: SetupActions.SAVE_FACULTY_CODE,
      payload: code
    };
  }
//2------------------------------------------------------------------------------------------//
  static SAVE_FACULTY_CODE_SUCCESS = '[Setup] Save Faculty Code Success';

  saveFacultyCodeSuccess(message): Action {
    console.log("saveFacultyCodeSuccess");
    return {
      type: SetupActions.SAVE_FACULTY_CODE_SUCCESS,
      payload: message
    };
  }
//3------------------------------------------------------------------------------------------//
  static FIND_FACULTY_CODES = '[Common] Find Faculty Codes';

  findFacultyCodes(): Action {
    console.log("findFacultyCodes");
    return {
      type: SetupActions.FIND_FACULTY_CODES,
    };
  }
//4------------------------------------------------------------------------------------------//
  static FIND_FACULTY_CODES_SUCCESS = '[Common] Find SupervisorCodes Success';

  findFacultyCodesSuccess(codes): Action {
    console.log("findFacultyCodesSuccess");
    return {
      type: SetupActions.FIND_FACULTY_CODES_SUCCESS,
      payload: codes
    };
  }
//5------------------------------------------------------------------------------------------//
  static REMOVE_FACULTY_CODE = '[Setup] Remove Faculty Code';

  removeFacultyCode(code): Action {
    console.log("removeFacultyCode");
    return {
      type: SetupActions.REMOVE_FACULTY_CODE,
      payload: code
    };
  }
//6------------------------------------------------------------------------------------------//
  static REMOVE_FACULTY_CODE_SUCCESS = '[Setup] Remove Faculty Code  Success';

  removeFacultyCodeSuccess(message): Action {
    console.log("removeFacultyCodeSuccess");
    return {
      type: SetupActions.REMOVE_FACULTY_CODE_SUCCESS,
      payload: message
    };
  }
  //7------------------------------------------------------------------------------------------//
  static UPDATE_FACULTY_CODE = '[Setup] Update Faculty Code';

  updateFacultyCode(code): Action {
    console.log("updateFacultyCode");
    return {
      type: SetupActions.UPDATE_FACULTY_CODE,
      payload: code
    };
  }
//8------------------------------------------------------------------------------------------//
  static UPDATE_FACULTY_CODE_SUCCESS = '[Setup] Update Faculty Code  Success';

  updateFacultyCodeSuccess(message): Action {
    console.log("updateFacultyCodeSuccess");
    return {
      type: SetupActions.UPDATE_FACULTY_CODE_SUCCESS,
      payload: message
    };
  }

//===========================================================================================//
// RESIDENCY CODE                                                                            //
//===========================================================================================//
//1------------------------------------------------------------------------------------------//
  static FIND_RESIDENCY_CODES = '[Common] Find Residency Codes';

  findResidencyCodes(): Action {
    console.log("findResidencyCodes");
    return {
      type: SetupActions.FIND_RESIDENCY_CODES,
    };
  }
//2------------------------------------------------------------------------------------------//
  static FIND_RESIDENCY_CODES_SUCCESS = '[Common] Find Residency Codes Success';

  findResidencyCodesSuccess(codes): Action {
    console.log("findResidencyCodesSuccess");
    return {
      type: SetupActions.FIND_RESIDENCY_CODES_SUCCESS,
      payload: codes
    };
  }
//3------------------------------------------------------------------------------------------//
  static SAVE_RESIDENCY_CODE = '[Setup] Save Residency Code';

  saveResidencyCode(code): Action {
    console.log("saveResidencyCode");
    return {
      type: SetupActions.SAVE_RESIDENCY_CODE,
      payload: code
    };
  }
//4------------------------------------------------------------------------------------------//
  static SAVE_RESIDENCY_CODE_SUCCESS = '[Setup] Save Residency Code Success';

  saveResidencyCodeSuccess(message): Action {
    console.log("saveResidencyCodeSuccess");
    return {
      type: SetupActions.SAVE_RESIDENCY_CODE_SUCCESS,
      payload: message
    };
  }
//5------------------------------------------------------------------------------------------//
  static UPDATE_RESIDENCY_CODE = '[Setup] Update Residency Code';

  updateResidencyCode(code): Action {
    console.log("updateResidencyCode");
    return {
      type: SetupActions.UPDATE_RESIDENCY_CODE,
      payload: code
    };
  }
//6------------------------------------------------------------------------------------------//
  static UPDATE_RESIDENCY_CODE_SUCCESS = '[Setup] Update Residency Code  Success';

  updateResidencyCodeSuccess(message): Action {
    console.log("updateResidencyCodeSuccess");
    return {
      type: SetupActions.UPDATE_RESIDENCY_CODE_SUCCESS,
      payload: message
    };
  }
//7------------------------------------------------------------------------------------------//
  static REMOVE_RESIDENCY_CODE = '[Setup] Remove Residency Code';

  removeResidencyCode(code): Action {
    console.log("updateResidencyCode");
    return {
      type: SetupActions.REMOVE_RESIDENCY_CODE,
      payload: code
    };
  }
//8------------------------------------------------------------------------------------------//
  static REMOVE_RESIDENCY_CODE_SUCCESS = '[Setup] Remove Residency Code  Success';

  removeResidencyCodeSuccess(message): Action {
    console.log("updateResidencyCodeSuccess");
    return {
      type: SetupActions.REMOVE_RESIDENCY_CODE_SUCCESS,
      payload: message
    };
  }

//===========================================================================================//
// STUDY MODE                                                                                //
//===========================================================================================//
//1------------------------------------------------------------------------------------------//
  static SAVE_STUDY_MODE = '[Setup] Save Study Mode';

  saveStudyMode(code): Action {
    console.log("saveStudyMode");
    return {
      type: SetupActions.SAVE_STUDY_MODE,
      payload: code
    };
  }
//2------------------------------------------------------------------------------------------//
  static SAVE_STUDY_MODE_SUCCESS = '[Setup] Save Study Mode Success';

  saveStudyModeSuccess(message): Action {
    console.log("saveStudyModeSuccess");
    return {
      type: SetupActions.SAVE_STUDY_MODE_SUCCESS,
      payload: message
    };
  }
//3------------------------------------------------------------------------------------------//
  static FIND_STUDY_MODES = '[Common] Find Study Modes';

  findStudyModes(): Action {
    console.log("findStudyModes");
    return {
      type: SetupActions.FIND_STUDY_MODES,
    };
  }
//4------------------------------------------------------------------------------------------//
  static FIND_STUDY_MODES_SUCCESS = '[Common] Find Study Modes Success';

  findStudyModesSuccess(codes): Action {
    console.log("findStudyModesSuccess");
    return {
      type: SetupActions.FIND_STUDY_MODES_SUCCESS,
      payload: codes
    };
  }

//===========================================================================================//
// COHORT CODE                                                                               //
//===========================================================================================//
//1------------------------------------------------------------------------------------------//
  static SAVE_COHORT_CODE = '[Setup] Save Cohort Code';

  saveCohortCode(code): Action {
    console.log("saveCohortCode");
    return {
      type: SetupActions.SAVE_COHORT_CODE,
      payload: code
    };
  }
//2------------------------------------------------------------------------------------------//
  static SAVE_COHORT_CODE_SUCCESS = '[Setup] Save Cohort Code Success';

  saveCohortCodeSuccess(message): Action {
    console.log("saveCohortCodeSuccess");
    return {
      type: SetupActions.SAVE_COHORT_CODE_SUCCESS,
      payload: message
    };
  }
//3------------------------------------------------------------------------------------------//
  static FIND_COHORT_CODES = '[Common] Find CohortCodes';

  findCohortCodes(): Action {
    console.log("findCohortCodes");
    return {
      type: SetupActions.FIND_COHORT_CODES,
    };
  }
//4------------------------------------------------------------------------------------------//
  static FIND_COHORT_CODES_SUCCESS = '[Common] Find CohortCodes Success';

  findCohortCodesSuccess(codes): Action {
    console.log("findCohortCodesSuccess");
    return {
      type: SetupActions.FIND_COHORT_CODES_SUCCESS,
      payload: codes
    };
  }
//5------------------------------------------------------------------------------------------//
  static REMOVE_COHORT_CODE = '[Setup] Remove Cohort Code';

  removeCohortCode(code): Action {
    console.log("removeCohortCode");
    return {
      type: SetupActions.REMOVE_COHORT_CODE,
      payload: code
    };
  }
//6------------------------------------------------------------------------------------------//
  static REMOVE_COHORT_CODE_SUCCESS = '[Setup] Remove Cohort Code Success';

  removeCohortCodeSuccess(message): Action {
    console.log("removeCohortCodeSuccess");
    return {
      type: SetupActions.REMOVE_COHORT_CODE_SUCCESS,
      payload: message
    };
  }
//7------------------------------------------------------------------------------------------//
  static UPDATE_COHORT_CODE = '[Setup] Update Cohort Code';

  updateCohortCode(code): Action {
    console.log("updateCohortCode");
    return {
      type: SetupActions.UPDATE_COHORT_CODE,
      payload: code
    };
  }
//8------------------------------------------------------------------------------------------//
  static UPDATE_COHORT_CODE_SUCCESS = '[Setup] Update Cohort Code Success';

  updateCohortCodeSuccess(message): Action {
    console.log("updateCohortCodeSuccess");
    return {
      type: SetupActions.UPDATE_COHORT_CODE_SUCCESS,
      payload: message
    };
  }

//===========================================================================================//
// TAX CODE                                                                                  //
//===========================================================================================//
//1------------------------------------------------------------------------------------------//
  static FIND_TAX_CODES = '[Common] Find Tax Codes';

  findTaxCodes(): Action {
    console.log("findTaxCodes");
    return {
      type: SetupActions.FIND_TAX_CODES,
    };
  }
//2------------------------------------------------------------------------------------------//
  static FIND_TAX_CODES_SUCCESS = '[Common] Find Tax Codes Success';

  findTaxCodesSuccess(codes): Action {
    console.log("findTaxCodesSuccess");
    return {
      type: SetupActions.FIND_TAX_CODES_SUCCESS,
      payload: codes
    };
  }
//3------------------------------------------------------------------------------------------//
  static SAVE_TAX_CODE = '[Setup] Save Tax Code';

  saveTaxCode(code): Action {
    console.log("saveTaxCode");
    return {
      type: SetupActions.SAVE_TAX_CODE,
      payload: code
    };
  }
//4------------------------------------------------------------------------------------------//
  static SAVE_TAX_CODE_SUCCESS = '[Setup] Save Tax Code Success';

  saveTaxCodeSuccess(message): Action {
    console.log("saveTaxCodeSuccess");
    return {
      type: SetupActions.SAVE_TAX_CODE_SUCCESS,
      payload: message
    };
  }
//5------------------------------------------------------------------------------------------//
  static UPDATE_TAX_CODE = '[Setup] Update Tax Code';

  updateTaxCode(code): Action {
    console.log("updateTaxCode");
    return {
      type: SetupActions.UPDATE_TAX_CODE,
      payload: code
    };
  }
//6------------------------------------------------------------------------------------------//
  static UPDATE_TAX_CODE_SUCCESS = '[Setup] Update Tax Code  Success';

  updateTaxCodeSuccess(message): Action {
    console.log("updateTaxCodeSuccess");
    return {
      type: SetupActions.UPDATE_TAX_CODE_SUCCESS,
      payload: message
    };
  }
//7------------------------------------------------------------------------------------------//
  static REMOVE_TAX_CODE = '[Setup] Remove Tax Code';

  removeTaxCode(code): Action {
    console.log("updateTaxCode");
    return {
      type: SetupActions.REMOVE_TAX_CODE,
      payload: code
    };
  }
//8------------------------------------------------------------------------------------------//
  static REMOVE_TAX_CODE_SUCCESS = '[Setup] Remove Tax Code  Success';

  removeTaxCodeSuccess(message): Action {
    console.log("updateTaxCodeSuccess");
    return {
      type: SetupActions.REMOVE_TAX_CODE_SUCCESS,
      payload: message
    };
  }
//9------------------------------------------------------------------------------------------//
    static FIND_TAX_CODES_BY_ACTIVE = '[Setup] Find Tax Codes By Active';
  
    findTaxCodesByActive(): Action {
      console.log("findTaxCodesByActive");
      return {
        type: SetupActions.FIND_TAX_CODES_BY_ACTIVE,
      };
    }
//10------------------------------------------------------------------------------------------//
static FIND_TAX_CODES_BY_ACTIVE_SUCCESS = '[Setup] Find Tax Codes By Active Success';

  findTaxCodesByActiveSuccess(codes): Action {
    console.log("findTaxCodesByActiveSuccess");
    return {
      type: SetupActions.FIND_TAX_CODES_BY_ACTIVE_SUCCESS,
      payload: codes
    };
  } 
//===========================================================================================//
// SECURITY CHARGE CODE                                                                      //
//===========================================================================================//
//1------------------------------------------------------------------------------------------//
  static SAVE_SECURITY_Charge_CODE = '[Setup] Save Security Charge Code';

  saveSecurityChargeCode(code): Action {
    console.log("saveSecurityChargeCode");
    return {
      type: SetupActions.SAVE_SECURITY_Charge_CODE,
      payload: code
    };
  }
//2------------------------------------------------------------------------------------------//
  static SAVE_SECURITY_Charge_CODE_SUCCESS = '[Setup] Save Security Charge Success';

  saveSecurityChargeCodeSuccess(message): Action {
    console.log("saveSecurityChargeCodeSuccess");
    return {
      type: SetupActions.SAVE_SECURITY_Charge_CODE_SUCCESS,
      payload: message
    };
  }
//3------------------------------------------------------------------------------------------//
  static UPDATE_SECURITY_Charge_CODE = '[Setup] Update Security Charge Code';

  updateSecurityChargeCode(code): Action {
    console.log("updateSecurityChargeCode");
    return {
      type: SetupActions.UPDATE_SECURITY_Charge_CODE,
      payload: code
    };
  }
//4------------------------------------------------------------------------------------------//
  static UPDATE_SECURITY_Charge_CODE_SUCCESS = '[Setup] Update Security Charge Code  Success';

  updateSecurityChargeCodeSuccess(message): Action {
    console.log("updateSecurityChargeCodeSuccess");
    return {
      type: SetupActions.UPDATE_SECURITY_Charge_CODE_SUCCESS,
      payload: message
    };
  }
//5------------------------------------------------------------------------------------------//
  static FIND_SECURITY_CHARGE_CODES = '[Setup] Find Security Charge Codes';

  findSecurityChargeCodes(): Action {
    console.log("findSecurityChargeCodes");
    return {
      type: SetupActions.FIND_SECURITY_CHARGE_CODES,
    };
  }
//6------------------------------------------------------------------------------------------//
  static FIND_SECURITY_CHARGE_CODES_SUCCESS = '[Setup] Find Security Charge Code Success';

  findSecurityChargeCodesSuccess(codes): Action {
    console.log("findSecurityChargeCodesSuccess");
    return {
      type: SetupActions.FIND_SECURITY_CHARGE_CODES_SUCCESS,
      payload: codes
    };
  }
//7------------------------------------------------------------------------------------------//
  static REMOVE_SECURITY_Charge_CODE = '[Setup] Remove Security Charge Code';

  removeSecurityChargeCode(code): Action {
    console.log("removeSecurityChargeCode");
    return {
      type: SetupActions.REMOVE_SECURITY_Charge_CODE,
      payload: code
    };
  }
//8------------------------------------------------------------------------------------------//
  static REMOVE_SECURITY_Charge_CODE_SUCCESS = '[Setup] Remove Security Charge Code  Success';

  removeSecurityChargeCodeSuccess(message): Action {
    console.log("removeSecurityChargeCodeSuccess");
    return {
      type: SetupActions.REMOVE_SECURITY_Charge_CODE_SUCCESS,
      payload: message
    };
  }

//9------------------------------------------------------------------------------------------//
static FIND_SECURITY_CHARGE_CODES_BY_ACTIVE = '[Setup] Find Security Charge Codes By Active';

  findSecurityChargeCodesByActive(): Action {
    console.log("findSecurityChargeCodesByActive");
    return {
      type: SetupActions.FIND_SECURITY_CHARGE_CODES_BY_ACTIVE,
    };
  }

//10------------------------------------------------------------------------------------------//
  static FIND_SECURITY_CHARGE_CODES_BY_ACTIVE_SUCCESS = '[Setup] Find Security Charge Codes By Active Success';

  findSecurityChargeCodesByActiveSuccess(codes): Action {
    console.log("findSecurityChargeCodesByActiveSuccess");
    return {
      type: SetupActions.FIND_SECURITY_CHARGE_CODES_BY_ACTIVE_SUCCESS,
      payload: codes
    };
  }  

}

//===========================================================================================//
//                                                                                           //
//===========================================================================================//
