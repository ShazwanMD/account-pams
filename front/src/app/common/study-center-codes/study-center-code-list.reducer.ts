import {Action} from '@ngrx/store';
import {CommonActions} from '../common.action';
import {StudyCenterCode} from '../../shared/model/common/study-center-code.interface';

export type StudyCenterCodeListState = StudyCenterCode[];

const initialState: StudyCenterCodeListState = <StudyCenterCode[]>[];

export function studyCenterCodeListReducer(state = initialState, action: Action): StudyCenterCodeListState {
  switch (action.type) {
    case CommonActions.FIND_STUDY_CENTER_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
