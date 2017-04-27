import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {ChargeCodeActions} from "./charge-code.action";
import {from} from "rxjs/observable/from";
import {AccountService} from "../../../services/account.service";


@Injectable()
export class ChargeCodeEffects {
  constructor(private actions$: Actions,
              private chargeCodeActions: ChargeCodeActions,
              private accountService: AccountService) {
  }

  @Effect() findChargeCodes$ = this.actions$
    .ofType(ChargeCodeActions.FIND_CHARGE_CODES)
    .switchMap(() => this.accountService.findChargeCodes())
    .map(chargeCodes => this.chargeCodeActions.findChargeCodesSuccess(chargeCodes));

  @Effect() findChargeCode$ = this.actions$
    .ofType(ChargeCodeActions.FIND_CHARGE_CODE)
    .map(action => action.payload)
    .switchMap(code => this.accountService.findChargeCodeByCode(code))
    .map(chargeCode => this.chargeCodeActions.findChargeCodeSuccess(chargeCode));

  // @Effect() saveChargeCode$ = this.actions$
  //   .ofType(ChargeCodeActions.SAVE_CHARGE_CODE)
  //   .map(action => action.payload)
  //   .switchMap(chargeCode => this.accountService.saveChargeCode(chargeCode))
  //   .map(chargeCode => this.chargeCodeActions.saveChargeCodeSuccess(chargeCode));
  //
  // @Effect() updateChargeCode$ = this.actions$
  //   .ofType(ChargeCodeActions.UPDATE_CHARGE_CODE)
  //   .map(action => action.payload)
  //   .switchMap(chargeCode => this.accountService.updateChargeCode(chargeCode))
  //   .map(chargeCode => this.chargeCodeActions.updateChargeCodeSuccess(chargeCode));
}
