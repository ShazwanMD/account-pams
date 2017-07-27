import {Action} from '@ngrx/store';
import {CommonActions} from '../common.action';
import {StudyMode} from '../../shared/model/common/study-mode.interface';

export type StudyModeListState = StudyMode[];

const initialState: StudyModeListState = <StudyMode[]>[];

export function studyModeListReducer(state = initialState, action: Action): StudyModeListState {
  switch (action.type) {
    case CommonActions.FIND_STUDY_MODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
