import {Injectable} from '@angular/core';
import {Actions, Effect} from '@ngrx/effects';
import {AccountService} from '../../../../../services/account.service';
import {AcademicSessionActions} from './academic-session.action';

@Injectable()
export class AcademicSessionEffects {
  constructor(private actions$: Actions,
              private accountActions: AcademicSessionActions,
              private accountService: AccountService) {
  }

  @Effect() findAcademicSessions$ = this.actions$
    .ofType(AcademicSessionActions.FIND_ACADEMIC_SESSIONS)
    .switchMap(() => this.accountService.findAcademicSessions())
    .map((accounts) => this.accountActions.findAcademicSessionsSuccess(accounts));

  @Effect() findAcademicSession$ = this.actions$
    .ofType(AcademicSessionActions.FIND_ACADEMIC_SESSION)
    .map((action) => action.payload)
    .switchMap((code) => this.accountService.findAcademicSessionByCode(code))
    .map((account) => this.accountActions.findAcademicSessionSuccess(account));

  @Effect() saveAccount$ = this.actions$
    .ofType(AcademicSessionActions.SAVE_ACADEMIC_SESSION)
    .map((action) => action.payload)
    .switchMap((account) => this.accountService.saveAcademicSession(account))
    .map((account) => this.accountActions.saveAcademicSessionSuccess(account));

  @Effect() updateAcademicSession$ = this.actions$
    .ofType(AcademicSessionActions.UPDATE_ACADEMIC_SESSION)
    .map((action) => action.payload)
    .switchMap((account) => this.accountService.updateAcademicSession(account))
    .map((account) => this.accountActions.updateAcademicSessionSuccess(account));
}
