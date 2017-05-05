import {Action} from '@ngrx/store';
import {Observable} from 'rxjs/Observable';

import * as _ from 'lodash';
import {AcademicSession} from "../academic-session.interface";
import {AcademicSessionActions} from "./academic-session.action";

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
