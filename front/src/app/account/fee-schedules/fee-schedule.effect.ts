import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {from} from 'rxjs/observable/from';
import {FeeScheduleActions} from './fee-schedule.action';
import {AccountService} from '../../../services/account.service';
import {AccountModuleState} from '../index';
import {Store} from '@ngrx/store';
import {AccountActions} from '../accounts/account.action';
import {Router} from '@angular/router';

@Injectable()
export class FeeScheduleEffects {
  constructor(private actions$: Actions,
              private feeScheduleActions: FeeScheduleActions,
              private accountActions: AccountActions,
              private accountService: AccountService,
              private router: Router,
              private store$: Store<AccountModuleState>) {
  }

  private FEE_SCHEDULE: string[] = 'accountModuleState.feeSchedule'.split('.');
  private ACCOUNT: string[] = 'accountModuleState.account'.split('.');

  @Effect() findFeeSchedules$ = this.actions$
    .ofType(FeeScheduleActions.FIND_FEE_SCHEDULES)
    .switchMap(() => this.accountService.findFeeSchedules())
    .map((accounts) => this.feeScheduleActions.findFeeSchedulesSuccess(accounts));

  @Effect() findFeeScheduleByCode$ = this.actions$
    .ofType(FeeScheduleActions.FIND_FEE_SCHEDULE_BY_CODE)
    .map((action) => action.payload)
    .switchMap((code) => this.accountService.findFeeScheduleByCode(code))
    .map((account) => this.feeScheduleActions.findFeeScheduleByCodeSuccess(account))
    .mergeMap((action) => from([action, this.feeScheduleActions.findFeeScheduleItems(action.payload)]));

  @Effect() findFeeScheduleItems$ = this.actions$
    .ofType(FeeScheduleActions.FIND_FEE_SCHEDULE_ITEMS)
    .map((action) => action.payload)
    .switchMap((account) => this.accountService.findFeeScheduleItems(account))
    .map((items) => this.feeScheduleActions.findFeeScheduleItemsSuccess(items));

  @Effect() saveFeeSchedule$ = this.actions$
    .ofType(FeeScheduleActions.SAVE_FEE_SCHEDULE)
    .map((action) => action.payload)
    .switchMap((feeSchedule) => this.accountService.saveFeeSchedule(feeSchedule))
    .map((message) => this.feeScheduleActions.saveFeeScheduleSuccess(message))
    .mergeMap((action) => from([action, this.feeScheduleActions.findFeeSchedules()]));

  @Effect() updateFeeSchedule$ = this.actions$
    .ofType(FeeScheduleActions.UPDATE_FEE_SCHEDULE)
    .map((action) => action.payload)
    .switchMap((account) => this.accountService.updateFeeSchedule(account))
    .map((account) => this.feeScheduleActions.updateFeeScheduleSuccess(account));

  @Effect() addFeeScheduleItem$ =
    this.actions$
      .ofType(FeeScheduleActions.ADD_FEE_SCHEDULE_ITEM)
      .map((action) => action.payload)
      .switchMap((payload) => this.accountService.addFeeScheduleItem(payload.feeSchedule, payload.item))
      .map((message) => this.feeScheduleActions.addFeeScheduleItemSuccess(message))
      .withLatestFrom(this.store$.select(...this.FEE_SCHEDULE))
      .map((state) => state[1])
      .map((feeSchedule) => this.feeScheduleActions.findFeeScheduleItems(feeSchedule))
      .do((action) => this.router.navigate(['/account/fee-schedules/:code', action.payload])).ignoreElements();

  @Effect() updateFeeScheduleItem$ = this.actions$
    .ofType(FeeScheduleActions.UPDATE_FEE_SCHEDULE_ITEM)
    .map((action) => action.payload)
    .switchMap((payload) => this.accountService.updateFeeScheduleItem(payload.feeSchedule, payload.item))
    .map((message) => this.feeScheduleActions.updateFeeScheduleItemSuccess(message))
    .withLatestFrom(this.store$.select(...this.FEE_SCHEDULE))
    .map((state) => state[1])
    .map((feeSchedule) => this.feeScheduleActions.findFeeScheduleItems(feeSchedule));

  @Effect() deleteFeeScheduleItem$ = this.actions$
    .ofType(FeeScheduleActions.DELETE_FEE_SCHEDULE_ITEM)
    .map((action) => action.payload)
    .switchMap((payload) => this.accountService.deleteFeeScheduleItem(payload.feeSchedule, payload.item))
    .map((message) => this.feeScheduleActions.deleteFeeScheduleItemSuccess(message))
    .withLatestFrom(this.store$.select(...this.FEE_SCHEDULE))
    .map((state) => state[1])
    .map((feeSchedule) => this.feeScheduleActions.findFeeScheduleItems(feeSchedule));

  @Effect() uploadFeeSchedule$ = this.actions$
    .ofType(FeeScheduleActions.UPLOAD_FEE_SCHEDULE)
    .map((action) => action.payload)
    .switchMap((payload) => this.accountService.uploadFeeSchedule(payload))
    .map((message) => this.feeScheduleActions.uploadFeeScheduleSuccess(message));

  @Effect() downloadFeeSchedule$ = this.actions$
    .ofType(FeeScheduleActions.DOWNLOAD_FEE_SCHEDULE)
    .map((action) => action.payload)
    .switchMap((offering) => this.accountService.downloadFeeSchedule(offering))
    .map((file) => {
      let url = URL.createObjectURL(file);
      let a = document.createElement('a');
      a.href = url;
      a.download = file.name;
      document.body.appendChild(a);
      a.click();
      document.body.removeChild(a);
      URL.revokeObjectURL(url);
    }).ignoreElements();

}
