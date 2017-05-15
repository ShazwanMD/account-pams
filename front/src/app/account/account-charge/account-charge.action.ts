import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class AccountChargeActions {

  static FIND_ACCOUNT_CHARGES= '[AccountCharge] Find AccountCharges';
  findAccountCharges(): Action {
    return {
      type: AccountChargeActions.FIND_ACCOUNT_CHARGES
    };
  }

  static FIND_ACCOUNT_CHARGES_SUCCESS = '[AccountCharge] Find AccountCharges Success';
  findAccountChargesSuccess(accountCharges): Action {
    console.log("findAccountChargesSuccess");
    console.log("accountCharges: " + accountCharges.length);
    return {
      type: AccountChargeActions.FIND_ACCOUNT_CHARGES_SUCCESS,
      payload: accountCharges
    };
  }

  // static SAVE_ACCOUNT_CHARGE = '[AccountCharge] Save AccountCharges';
  // saveFeeSchedule(feeSchedule): Action {
  //   console.log("saveAccountCharge");
  //   return {
  //     type: AccountChargeActions.SAVE_ACCOUNT_CHARGE,
  //     payload: feeSchedule
  //   };
  // }

  // static SAVE_ACCOUNT_CHARGE_SUCCESS = '[AccountCharge] Save AccountCharges Success';
  // saveFeeScheduleSuccess(feeSchedule): Action {
  //   return {
  //     type: AccountChargeActions.SAVE_ACCOUNT_CHARGE_SUCCESS,
  //     payload: feeSchedule
  //   };
  // }
}
