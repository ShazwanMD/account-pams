import {Action} from '@ngrx/store';

import * as _ from 'lodash';
import {AcademicSessionActions} from './academic-session.action';
import {AcademicSession} from '../../../shared/model/account/academic-session.interface';

export type AcademicSessionListState = AcademicSession[];

const initialState: AcademicSessionListState = <AcademicSession[]>[];

export function academicSessionListReducer(state = initialState, action: Action): AcademicSessionListState {
  switch (action.type) {
    case AcademicSessionActions.FIND_ACADEMIC_SESSIONS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
