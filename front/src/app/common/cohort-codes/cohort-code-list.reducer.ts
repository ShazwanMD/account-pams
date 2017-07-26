import {Action} from '@ngrx/store';
import {CommonActions} from '../common.action';
import {CohortCode} from './cohort-code.interface';

export type CohortCodeListState = CohortCode[];

const initialState: CohortCodeListState = <CohortCode[]>[];

export function cohortCodeListReducer(state = initialState, action: Action): CohortCodeListState {
  switch (action.type) {
    case CommonActions.FIND_COHORT_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
