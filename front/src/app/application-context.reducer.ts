import {Action} from '@ngrx/store';
import {ApplicationContextActions} from './application-context.action';

export type ApplicationContextState = {
  hasError: boolean;
  errorMessage: string;
};

export const INITIAL_APPLICATION_CONTEXT_STATE: ApplicationContextState = {
  hasError: false,
  errorMessage: undefined,
};

export function applicationContextReducer(state = INITIAL_APPLICATION_CONTEXT_STATE, action: Action): ApplicationContextState {
  switch (action.type) {
    case ApplicationContextActions.SET_ERROR_MESSAGE_SUCCESS: {
      return {hasError: true, errorMessage: action.payload};
    }
    case ApplicationContextActions.RESET_ERROR_MESSAGE_SUCCESS : {
      return {hasError: false, errorMessage: undefined};
    }
    default: {
      return state;
    }
  }
}
