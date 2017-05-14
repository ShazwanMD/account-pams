import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {from} from "rxjs/observable/from";
import {ChargeCodeActions} from "./charge-code.action";
import {AccountService} from "../../../services/account.service";
import {AccountModuleState} from "../index";
import {Store} from "@ngrx/store";


@Injectable()
export class ChargeCodeEffects {
  constructor(private actions$: Actions,
              private chargeCodeActions: ChargeCodeActions,
              private accountService: AccountService,
              private store$: Store<AccountModuleState>) {
  }

  private CHARGE_CODE = "accountModuleState.chargeCode".split(".");

  @Effect() findChargeCodes$ = this.actions$
    .ofType(ChargeCodeActions.FIND_CHARGE_CODES)
    .map(action => action.payload)
    .switchMap(() => this.accountService.findChargeCodes())
    .map(codes => this.chargeCodeActions.findChargeCodesSuccess(codes));

  @Effect() findChargeCodeByCode$ = this.actions$
    .ofType(ChargeCodeActions.FIND_CHARGE_CODE_BY_CODE)
    .map(action => action.payload)
    .switchMap(code => this.accountService.findChargeCodeByCode(code))
    .map(account => this.chargeCodeActions.findChargeCodeByCodeSuccess(account));

  @Effect() saveChargeCode$ = this.actions$
    .ofType(ChargeCodeActions.SAVE_CHARGE_CODE)
    .map(action => action.payload)
    .switchMap(chargeCode => this.accountService.saveChargeCode(chargeCode))
    .map(message => this.chargeCodeActions.saveChargeCodeSuccess(message))
    .mergeMap(action => from([action, this.chargeCodeActions.findChargeCodes()]));

  @Effect() updateChargeCode$ = this.actions$
    .ofType(ChargeCodeActions.UPDATE_CHARGE_CODE)
    .map(action => action.payload)
    .switchMap(account => this.accountService.updateChargeCode(account))
    .map(account => this.chargeCodeActions.updateChargeCodeSuccess(account));
}
