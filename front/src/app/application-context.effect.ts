import {Injectable} from '@angular/core';
import {Actions, Effect} from '@ngrx/effects';
import {ApplicationContextActions} from './application-context.action';

@Injectable()
export class ApplicationContextEffects {
  constructor(private actions$: Actions,
              private ctxActions: ApplicationContextActions) {
  }

  @Effect() setErrorMessage$ = this.actions$
    .ofType(ApplicationContextActions.SET_ERROR_MESSAGE)
    .map((action) => action.payload)
    .map((payload) => this.ctxActions.setErrorMessageSuccess(payload));

  @Effect() resetErrorMessage$ = this.actions$
    .ofType(ApplicationContextActions.RESET_ERROR_MESSAGE)
    .map(() => this.ctxActions.resetErrorMessageSuccess());
}
