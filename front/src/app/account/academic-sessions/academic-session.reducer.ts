import {Action} from '@ngrx/store';
import {AcademicSession} from "./academic-session.interface";
import {AcademicSessionActions} from "./component/academic-session.action";

export type AcademicSessionState = AcademicSession;

const initialState: AcademicSessionState = <AcademicSession>{};

export function academicSessionReducer(state = initialState, action: Action): AcademicSession {
  switch (action.type) {
    case AcademicSessionActions.FIND_ACADEMIC_SESSION_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
