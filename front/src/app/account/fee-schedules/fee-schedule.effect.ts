import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {from} from "rxjs/observable/from";
import {FeeScheduleActions} from "./fee-schedule.action";
import {AccountService} from "../../../services/account.service";
import {AccountModuleState} from "../index";
import {Store} from "@ngrx/store";


@Injectable()
export class FeeScheduleEffects {
  constructor(private actions$: Actions,
              private feeScheduleActions: FeeScheduleActions,
              private accountService: AccountService,
              private store$: Store<AccountModuleState>) {
  }

  private FEE_SCHEDULE = "accountModuleState.feeSchedule".split(".");

  @Effect() findFeeSchedules$ = this.actions$
    .ofType(FeeScheduleActions.FIND_FEE_SCHEDULES)
    .switchMap(() => this.accountService.findFeeSchedules())
    .map(accounts => this.feeScheduleActions.findFeeSchedulesSuccess(accounts));

  @Effect() findFeeScheduleByCode$ = this.actions$
    .ofType(FeeScheduleActions.FIND_FEE_SCHEDULE_BY_CODE)
    .map(action => action.payload)
    .switchMap(code => this.accountService.findFeeScheduleByCode(code))
    .map(account => this.feeScheduleActions.findFeeScheduleByCodeSuccess(account))
    .mergeMap(action => from([action, this.feeScheduleActions.findFeeScheduleItems(action.payload)]));

  @Effect() findFeeScheduleItems$ = this.actions$
    .ofType(FeeScheduleActions.FIND_FEE_SCHEDULE_ITEMS)
    .map(action => action.payload)
    .switchMap(account => this.accountService.findFeeScheduleItems(account))
    .map(items => this.feeScheduleActions.findFeeScheduleItemsSuccess(items));

  @Effect() saveFeeSchedule$ = this.actions$
    .ofType(FeeScheduleActions.SAVE_FEE_SCHEDULE)
    .map(action => action.payload)
    .switchMap(account => this.accountService.saveFeeSchedule(account))
    .map(account => this.feeScheduleActions.saveFeeScheduleSuccess(account));

  @Effect() updateFeeSchedule$ = this.actions$
    .ofType(FeeScheduleActions.UPDATE_FEE_SCHEDULE)
    .map(action => action.payload)
    .switchMap(account => this.accountService.updateFeeSchedule(account))
    .map(account => this.feeScheduleActions.updateFeeScheduleSuccess(account));

  @Effect() addFeeScheduleItem$ =
    this.actions$
      .ofType(FeeScheduleActions.ADD_FEE_SCHEDULE_ITEM)
      .map(action => action.payload)
      .switchMap(payload => this.accountService.addFeeScheduleItem(payload.feeSchedule, payload.item))
      .map(message => this.feeScheduleActions.addFeeScheduleItemSuccess(message))
      .withLatestFrom(this.store$.select(...this.FEE_SCHEDULE))
      .map(state => state[1])
      .map(feeSchedule => this.feeScheduleActions.findFeeScheduleItems(feeSchedule));
}
