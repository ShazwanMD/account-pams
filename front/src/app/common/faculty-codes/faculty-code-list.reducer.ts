import {Action} from '@ngrx/store';
import {CommonActions} from '../common.action';
import {FacultyCode} from './faculty-code.interface';

export type FacultyCodeListState = FacultyCode[];

const initialState: FacultyCodeListState = <FacultyCode[]>[];

export function facultyCodeListReducer(state = initialState, action: Action): FacultyCodeListState {
  switch (action.type) {
    case CommonActions.FIND_FACULTY_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
