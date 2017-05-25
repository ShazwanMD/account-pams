import {Injectable} from "@angular/core";
import {Action} from '@ngrx/store';

@Injectable()
export class CommonActions {

  // ====================================================================================================
  // COHORT CODES
  // ====================================================================================================

  static FIND_COHORT_CODES = '[Common] Find Cohort Codes';

  findCohortCodes(): Action {
    console.log("findCohortCodes");
    return {
      type: CommonActions.FIND_COHORT_CODES,
    };
  }

  static FIND_COHORT_CODES_SUCCESS = '[Common] Find Cohort Codes Success';

  findCohortCodesSuccess(codes): Action {
    console.log("findCohortCodesSuccess");
    return {
      type: CommonActions.FIND_COHORT_CODES_SUCCESS,
      payload: codes
    };
  }
  // ====================================================================================================
  // FACULTY CODES
  // ====================================================================================================

  static FIND_FACULTY_CODES = '[Common] Find Faculty Codes';

  findFacultyCodes(): Action {
    console.log("findFacultyCodes");
    return {
      type: CommonActions.FIND_FACULTY_CODES,
    };
  }

  static FIND_FACULTY_CODES_SUCCESS = '[Common] Find Faculty Codes Success';

  findFacultyCodesSuccess(codes): Action {
    console.log("findFacultyCodesSuccess");
    return {
      type: CommonActions.FIND_FACULTY_CODES_SUCCESS,
      payload: codes
    };
  }

  // ====================================================================================================
  // BANK CODES
  // ====================================================================================================

  static FIND_BANK_CODES = '[Common] Find Bank Codes';

  findBankCodes(): Action {
    console.log("findBankCodes");
    return {
      type: CommonActions.FIND_BANK_CODES,
    };
  }

  static FIND_BANK_CODES_SUCCESS = '[Common] Find Bank Codes Success';

  findBankCodesSuccess(codes): Action {
    console.log("findBankCodesSuccess");
    return {
      type: CommonActions.FIND_BANK_CODES_SUCCESS,
      payload: codes
    };
  }

  // ====================================================================================================
  // PROGRAM CODES
  // ====================================================================================================

  static FIND_PROGRAM_CODES = '[Common] Find Program Codes';

  findProgramCodes(): Action {
    console.log("findProgramCodes");
    return {
      type: CommonActions.FIND_PROGRAM_CODES,
    };
  }

  static FIND_PROGRAM_CODES_SUCCESS = '[Common] Find Program Codes Success';

  findProgramCodesSuccess(codes): Action {
    console.log("findProgramCodesSuccess");
    return {
      type: CommonActions.FIND_PROGRAM_CODES_SUCCESS,
      payload: codes
    };
  }
  
  // ====================================================================================================
  // RESIDENCY CODES
  // ====================================================================================================

  static FIND_RESIDENCY_CODES = '[Common] Find Residency Codes';

  findResidencyCodes(): Action {
    console.log("findResidencyCodes");
    return {
      type: CommonActions.FIND_RESIDENCY_CODES,
    };
  }

  static FIND_RESIDENCY_CODES_SUCCESS = '[Common] Find Residency Codes Success';

  findResidencyCodesSuccess(codes): Action {
    console.log("findResidencyCodesSuccess");
    return {
      type: CommonActions.FIND_RESIDENCY_CODES_SUCCESS,
      payload: codes
    };
  }

  // ====================================================================================================
  // STUDY MODES
  // ====================================================================================================


  static FIND_STUDY_MODES = '[Common] Find Study Modes';

  findStudyModes(): Action {
    console.log("findStudyModes");
    return {
      type: CommonActions.FIND_STUDY_MODES,
    };
  }

  static FIND_STUDY_MODES_SUCCESS = '[Common] Find Study Modes Success';

  findStudyModesSuccess(codes): Action {
    console.log("findStudyModesSuccess");
    return {
      type: CommonActions.FIND_STUDY_MODES_SUCCESS,
      payload: codes
    };
  }

}
