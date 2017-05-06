import {Injectable} from "@angular/core";
import {Action} from '@ngrx/store';

@Injectable()
export class CommonActions {

  static FIND_DUN_CODES = '[Common] Find DunCodes';

  findDunCodes(): Action {
    console.log("findDunCodes");
    return {
      type: CommonActions.FIND_DUN_CODES
  };
  }

  static FIND_DUN_CODES_SUCCESS = '[Common] Find DunCodes Success';

  findDunCodesSuccess(codes): Action {
    console.log("findDunCodesSuccess");
    return {
      type: CommonActions.FIND_DUN_CODES_SUCCESS,
      payload: codes
    };
  }

  static FIND_PARLIAMENT_CODES = '[Common] Find ParliamentCodes';

  findParliamentCodes(): Action {
    console.log("findParliamentCodes");
    return {
      type: CommonActions.FIND_PARLIAMENT_CODES
  };
  }

  static FIND_PARLIAMENT_CODES_SUCCESS = '[Common] Find ParliamentCodes Success';

  findParliamentCodesSuccess(codes): Action {
    console.log("findParliamentCodesSuccess");
    return {
      type: CommonActions.FIND_PARLIAMENT_CODES_SUCCESS,
      payload: codes
    };
  }


  static FIND_GENDER_CODES = '[Common] Find Gender Codes';

  findGenderCodes(): Action {
    console.log("findGenderCodes");
    return {
      type: CommonActions.FIND_GENDER_CODES,
    };
  }

  static FIND_GENDER_CODES_SUCCESS = '[Common] Find Gender Codes Success';

  findGenderCodesSuccess(codes): Action {
    console.log("findGenderCodesSuccess");
    return {
      type: CommonActions.FIND_GENDER_CODES_SUCCESS,
      payload: codes
    };
  }

  static FIND_MARITAL_CODES = '[Common] Find Marital Codes';

  findMaritalCodes(): Action {
    console.log("findMaritalCodes");
    return {
      type: CommonActions.FIND_MARITAL_CODES,
    };
  }

  static FIND_MARITAL_CODES_SUCCESS = '[Common] Find Marital Codes Success';

  findMaritalCodesSuccess(codes): Action {
    console.log("findMaritalCodesSuccess");
    return {
      type: CommonActions.FIND_MARITAL_CODES_SUCCESS,
      payload: codes
    };
  }

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

  static FIND_GRADUATE_CENTRES = '[Common] Find Graduate Centres';

  findGraduateCentres(): Action {
    console.log("findGraduateCentres");
    return {
      type: CommonActions.FIND_GRADUATE_CENTRES,
    };
  }

  static FIND_GRADUATE_CENTRES_SUCCESS = '[Common] Find Graduate Centres Success';

  findGraduateCentresSuccess(centres): Action {
    console.log("findGraduateCentresSuccess");
    return {
      type: CommonActions.FIND_GRADUATE_CENTRES_SUCCESS,
      payload: centres
    };
  }

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

  static FIND_SUPERVISOR_CODES = '[Common] Find Supervisor Codes';

  findSupervisorCodes(): Action {
    console.log("findSupervisorCodes");
    return {
      type: CommonActions.FIND_SUPERVISOR_CODES,
    };
  }

  static FIND_SUPERVISOR_CODES_SUCCESS = '[Common] Find Supervisor Codes Success';

  findSupervisorCodesSuccess(codes): Action {
    console.log("findSupervisorCodesSuccess");
    return {
      type: CommonActions.FIND_SUPERVISOR_CODES_SUCCESS,
      payload: codes
    };
  }

  static FIND_STUDY_MODES = '[Common] Find Study Modes';

  findStudyModes(): Action {
    console.log("findStudyModes");
    return {
      type: CommonActions.FIND_STUDY_MODES,
    };
  }

  static FIND_STUDY_MODES_SUCCESS = '[Common] Find Study Modes Success';

  findStudyModeSuccess(codes): Action {
    console.log("findStudyModeSuccess");
    return {
      type: CommonActions.FIND_STUDY_MODES_SUCCESS,
      payload: codes
    };
  }
}
