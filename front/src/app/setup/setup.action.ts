import {Injectable} from "@angular/core";
import {Action} from '@ngrx/store';

@Injectable()
export class SetupActions {

  static CHANGE_TITLE = '[Setup] Change Title';

  changeTitle(title): Action {
    console.log("changeTitle");
    return {
      type: SetupActions.CHANGE_TITLE,
      payload: title
    };
  }

  static CHANGE_TITLE_SUCCESS = '[Setup] Change Title Success';

  changeTitleSuccess(title): Action {
    console.log("changeTitleSuccess");
    return {
      type: SetupActions.CHANGE_TITLE_SUCCESS,
      payload: title
    };
  }


  static FIND_BANK_CODES = '[Setup] Find Bank Codes';

  findBankCodes(): Action {
    console.log("findBankCodes");
    return {
      type: SetupActions.FIND_BANK_CODES,
    };
  }

  static FIND_BANK_CODES_SUCCESS = '[Setup] Find Bank Codes Success';

  findBankCodesSuccess(codes): Action {
    console.log("findBankCodesSuccess");
    return {
      type: SetupActions.FIND_BANK_CODES_SUCCESS,
      payload: codes
    };
  }

  static SAVE_BANK_CODE = '[Setup] Save Bank Code';

  saveBankCode(code): Action {
    console.log("saveBankCode");
    return {
      type: SetupActions.SAVE_BANK_CODE,
      payload: code
    };
  }

  static SAVE_BANK_CODE_SUCCESS = '[Setup] Save Bank Code  Success';

  saveBankCodeSuccess(message): Action {
    console.log("saveBankCodeSuccess");
    return {
      type: SetupActions.SAVE_BANK_CODE_SUCCESS,
      payload: message
    };
  }

  static UPDATE_BANK_CODE = '[Setup] Update Bank Code';

  updateBankCode(code): Action {
    console.log("updateBankCode");
    return {
      type: SetupActions.UPDATE_BANK_CODE,
      payload: code
    };
  }

  static UPDATE_BANK_CODE_SUCCESS = '[Setup] Update Bank Code  Success';

  updateBankCodeSuccess(message): Action {
    console.log("updateBankCodeSuccess");
    return {
      type: SetupActions.UPDATE_BANK_CODE_SUCCESS,
      payload: message
    };
  }

    static REMOVE_BANK_CODE = '[Setup] Remove Bank Code';

  removeBankCode(code): Action {
    console.log("removeBankCode");
    return {
      type: SetupActions.REMOVE_BANK_CODE,
      payload: code
    };
  }

  static REMOVE_BANK_CODE_SUCCESS = '[Setup] Remove Bank Code  Success';

  removeBankCodeSuccess(message): Action {
    console.log("removeBankCodeSuccess");
    return {
      type: SetupActions.REMOVE_BANK_CODE_SUCCESS,
      payload: message
    };
  }

    static SAVE_MARITAL_CODE = '[Setup] Save Marital Code';

  saveMaritalCode(code): Action {
    console.log("saveMaritalCode");
    return {
      type: SetupActions.SAVE_MARITAL_CODE,
      payload: code
    };
  }

  static SAVE_MARITAL_CODE_SUCCESS = '[Setup] Save Marital Code Success';

  saveMaritalCodeSuccess(message): Action {
    console.log("saveMaritalCodeSuccess");
    return {
      type: SetupActions.SAVE_MARITAL_CODE_SUCCESS,
      payload: message
    };
  }

  static UPDATE_MARITAL_CODE = '[Setup] Update Marital Code';

  updateMaritalCode(code): Action {
    console.log("updateMaritalCode");
    return {
      type: SetupActions.UPDATE_MARITAL_CODE,
      payload: code
    };
  }

  static UPDATE_MARITAL_CODE_SUCCESS = '[Setup] Update Marital Code  Success';

  updateMaritalCodeSuccess(message): Action {
    console.log("updateMaritalCodeSuccess");
    return {
      type: SetupActions.UPDATE_MARITAL_CODE_SUCCESS,
      payload: message
    };
  }

  static FIND_COUNTRY_CODES = '[Common] Find CountryCodes';

  findCountryCodes(): Action {
    console.log("findCountryCodes");
    return {
      type: SetupActions.FIND_COUNTRY_CODES,
    };
  }

  static FIND_COUNTRY_CODES_SUCCESS = '[Common] Find CountryCodes Success';

  findCountryCodesSuccess(codes): Action {
    console.log("findCountryCodesSuccess");
    return {
      type: SetupActions.FIND_COUNTRY_CODES_SUCCESS,
      payload: codes
    };
  }

  static SAVE_COUNTRY_CODE = '[Setup] Save Country Code';

  saveCountryCode(code): Action {
    console.log("saveCountryCode");
    return {
      type: SetupActions.SAVE_COUNTRY_CODE,
      payload: code
    };
  }

  static FIND_STATE_CODES_SUCCESS = '[Common] Find StateCodes Success';

  findStateCodesSuccess(codes): Action {
    console.log("findStateCodesSuccess");
    return {
      type: SetupActions.FIND_STATE_CODES_SUCCESS,
      payload: codes
    };
  }

  static SAVE_STATE_CODE = '[Setup] Save State Code';

  saveStateCode(code): Action {
    console.log("saveStateCode");
    return {
      type: SetupActions.SAVE_STATE_CODE,
      payload: code
    };
  }

  static FIND_STATE_CODES = '[Common] Find StateCodes';

  findStateCodes(): Action {
    console.log("findStateCodes");
    return {
      type: SetupActions.FIND_STATE_CODES,
    };
  }

  static FIND_PROGRAM_CODES = '[Common] Find ProgramCodes';

  findProgramCodes(): Action {
    console.log("findProgramCodes");
    return {
      type: SetupActions.FIND_PROGRAM_CODES,
    };
  }

  static FIND_PROGRAM_CODES_SUCCESS = '[Common] Find ProgramCodes Success';

  findProgramCodesSuccess(codes): Action {
    console.log("findProgramCodesSuccess");
    return {
      type: SetupActions.FIND_PROGRAM_CODES_SUCCESS,
      payload: codes
    };
  }

  static SAVE_PROGRAM_CODE = '[Setup] Save Program Code';

  saveProgramCode(code): Action {
    console.log("saveProgramCode");
    return {
      type: SetupActions.SAVE_PROGRAM_CODE,
      payload: code
    };
  }

  static SAVE_PROGRAM_CODE_SUCCESS = '[Setup] Save Program Code Success';

  saveProgramCodeSuccess(message): Action {
    console.log("saveProgramCodeSuccess");
    return {
      type: SetupActions.SAVE_PROGRAM_CODE_SUCCESS,
      payload: message
    };
  }

  static REMOVE_PROGRAM_CODE = '[Setup] Remove Program Code';

  removeProgramCode(code): Action {
    console.log("removeProgramCode");
    return {
      type: SetupActions.REMOVE_PROGRAM_CODE,
      payload: code
    };
  }

  static REMOVE_PROGRAM_CODE_SUCCESS = '[Setup] Remove Program Code Success';

  removeProgramCodeSuccess(message): Action {
    console.log("removeProgramCodeSuccess");
    return {
      type: SetupActions.REMOVE_PROGRAM_CODE_SUCCESS,
      payload: message
    };
  }



  static SAVE_FACULTY_CODE = '[Setup] Save Faculty Code';

  saveFacultyCode(code): Action {
    console.log("saveFacultyCode");
    return {
      type: SetupActions.SAVE_FACULTY_CODE,
      payload: code
    };
  }

  static SAVE_FACULTY_CODE_SUCCESS = '[Setup] Save Faculty Code Success';

  saveFacultyCodeSuccess(message): Action {
    console.log("saveFacultyCodeSuccess");
    return {
      type: SetupActions.SAVE_FACULTY_CODE_SUCCESS,
      payload: message
    };
  }

  static FIND_FACULTY_CODES = '[Common] Find Faculty Codes';

  findFacultyCodes(): Action {
    console.log("findFacultyCodes");
    return {
      type: SetupActions.FIND_FACULTY_CODES,
    };
  }

  static FIND_FACULTY_CODES_SUCCESS = '[Common] Find SupervisorCodes Success';

  findFacultyCodesSuccess(codes): Action {
    console.log("findFacultyCodesSuccess");
    return {
      type: SetupActions.FIND_FACULTY_CODES_SUCCESS,
      payload: codes
    };
  }

  static SAVE_STUDY_MODE = '[Setup] Save Study Mode';

  saveStudyMode(code): Action {
    console.log("saveStudyMode");
    return {
      type: SetupActions.SAVE_STUDY_MODE,
      payload: code
    };
  }

  static SAVE_STUDY_MODE_SUCCESS = '[Setup] Save Study Mode Success';

  saveStudyModeSuccess(message): Action {
    console.log("saveStudyModeSuccess");
    return {
      type: SetupActions.SAVE_STUDY_MODE_SUCCESS,
      payload: message
    };
  }

  static FIND_STUDY_MODES = '[Common] Find Study Modes';

  findStudyModes(): Action {
    console.log("findStudyModes");
    return {
      type: SetupActions.FIND_STUDY_MODES,
    };
  }

  static FIND_STUDY_MODES_SUCCESS = '[Common] Find Study Modes Success';

  findStudyModesSuccess(codes): Action {
    console.log("findStudyModesSuccess");
    return {
      type: SetupActions.FIND_STUDY_MODES_SUCCESS,
      payload: codes
    };
  }

}
