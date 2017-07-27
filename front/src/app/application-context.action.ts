import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class ApplicationContextActions {

  static SET_ERROR_MESSAGE: string = '[ApplicationContext] Set Error Message';
  static SET_ERROR_MESSAGE_SUCCESS: string = '[ApplicationContext] Set Error Message Success';
  static RESET_ERROR_MESSAGE: string = '[ApplicationContext] Reset Error Message';
  static RESET_ERROR_MESSAGE_SUCCESS: string = '[ApplicationContext] Reset Error Message Success';

  setErrorMessage(errorMessage: string): Action {
    console.log('setErrorMessage');
    return {
      type: ApplicationContextActions.SET_ERROR_MESSAGE,
      payload: errorMessage,
    };
  }

  setErrorMessageSuccess(errorMessage: string): Action {
    console.log('setErrorMessageSuccess');
    return {
      type: ApplicationContextActions.SET_ERROR_MESSAGE_SUCCESS,
      payload: errorMessage,
    };
  }

  resetErrorMessage(): Action {
    console.log('resetErrorMessage');
    return {
      type: ApplicationContextActions.RESET_ERROR_MESSAGE,
    };
  }

  resetErrorMessageSuccess(): Action {
    console.log('resetErrorMessageSuccess');
    return {
      type: ApplicationContextActions.RESET_ERROR_MESSAGE,
    };
  }
}
