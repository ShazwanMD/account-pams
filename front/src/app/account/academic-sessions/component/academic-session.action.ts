import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class AcademicSessionActions {

  static FIND_ACADEMIC_SESSIONS = '[AcademicSession] Find AcademicSessions';
  findAcademicSessions(): Action {
    return {
      type: AcademicSessionActions.FIND_ACADEMIC_SESSIONS
    };
  }

  static FIND_ACADEMIC_SESSIONS_SUCCESS = '[AcademicSession] Find AcademicSessions Success';
  findAcademicSessionsSuccess(academic_sessions): Action {
    console.log("findAcademicSessionsSuccess");
    console.log("academic_sessions: " + academic_sessions.length);
    return {
      type: AcademicSessionActions.FIND_ACADEMIC_SESSIONS_SUCCESS,
      payload: academic_sessions
    };
  }

  static FIND_ACADEMIC_SESSION = '[AcademicSession] Find AcademicSession';
  findAcademicSession(code): Action {
    return {
      type: AcademicSessionActions.FIND_ACADEMIC_SESSION,
      payload: code
    };
  }

  static FIND_ACADEMIC_SESSION_SUCCESS = '[AcademicSession] Find AcademicSession Success';
  findAcademicSessionSuccess(academic_session): Action {
    console.log("findAcademicSessionSuccess");
    return {
      type: AcademicSessionActions.FIND_ACADEMIC_SESSION_SUCCESS,
      payload: academic_session
    };
  }

  static SAVE_ACADEMIC_SESSION = '[AcademicSession] Save AcademicSession';
  saveAcademicSession(academic_session): Action {
    return {
      type: AcademicSessionActions.SAVE_ACADEMIC_SESSION,
      payload: academic_session
    };
  }

  static SAVE_ACADEMIC_SESSION_SUCCESS = '[AcademicSession] Save AcademicSession Success';
  saveAcademicSessionSuccess(academic_session): Action {
    return {
      type: AcademicSessionActions.SAVE_ACADEMIC_SESSION_SUCCESS,
      payload: academic_session
    };
  }

  static UPDATE_ACADEMIC_SESSION = '[AcademicSession] Update AcademicSession';
  updateAcademicSession(academic_session): Action {
    return {
      type: AcademicSessionActions.UPDATE_ACADEMIC_SESSION,
      payload: academic_session
    };
  }

  static UPDATE_ACADEMIC_SESSION_SUCCESS = '[AcademicSession] Update AcademicSession Success';
  updateAcademicSessionSuccess(academic_session): Action {
    return {
      type: AcademicSessionActions.UPDATE_ACADEMIC_SESSION_SUCCESS,
      payload: academic_session
    };
  }


  static REMOVE_ACADEMIC_SESSION = '[AcademicSession] Remove AcademicSession';
  removeAcademicSession(academic_session): Action {
    return {
      type: AcademicSessionActions.REMOVE_ACADEMIC_SESSION,
      payload: academic_session
    };
  }

  static REMOVE_ACADEMIC_SESSION_SUCCESS = '[AcademicSession] Remove AcademicSession Success';
  removeAcademicSessionSuccess(academic_session): Action {
    return {
      type: AcademicSessionActions.REMOVE_ACADEMIC_SESSION_SUCCESS,
      payload: academic_session
    };
  }
}
