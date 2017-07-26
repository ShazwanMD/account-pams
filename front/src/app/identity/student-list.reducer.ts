import {Action} from '@ngrx/store';

import * as _ from 'lodash';
import {StudentActions} from './student.action';
import {Student} from '../shared/model/identity/student.interface';

export type StudentListState = Student[];

const initialState: StudentListState = <Student[]>[];

export function studentListReducer(state = initialState, action: Action): StudentListState {
  switch (action.type) {
    case StudentActions.FIND_STUDENTS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
