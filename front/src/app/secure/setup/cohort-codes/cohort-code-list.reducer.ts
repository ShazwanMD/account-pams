import {CohortCode} from '../../../shared/model/common/cohort-code.interface';
import {Action} from '@ngrx/store';
import {SetupActions} from '../setup.action';

export type CohortCodeListState = CohortCode[];

const initialState: CohortCodeListState = <CohortCode[]>[];

export function cohortCodeListReducer(state = initialState, action: Action): CohortCodeListState {
  switch (action.type) {
    case SetupActions.FIND_COHORT_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
