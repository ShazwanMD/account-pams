import {Injectable} from "@angular/core";
import {Action} from '@ngrx/store';

@Injectable()
export class CommonActions {

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
}
