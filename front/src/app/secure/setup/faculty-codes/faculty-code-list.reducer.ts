import {FacultyCode} from '../../../shared/model/common/faculty-code.interface';
import {Action} from '@ngrx/store';
import {SetupActions} from '../setup.action';

export type FacultyCodeListState = FacultyCode[];

const initialState: FacultyCodeListState = <FacultyCode[]>[];

export function facultyCodeListReducer(state = initialState, action: Action): FacultyCodeListState {
  switch (action.type) {
    case SetupActions.FIND_FACULTY_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
