import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class DebitNoteActions {

  static FIND_CHARGE_CODES = '[ChargeCode] Find ChargeCodes';
  findChargeCodes(): Action {
    return {
      type: DebitNoteActions.FIND_CHARGE_CODES
    };
  }

  static FIND_CHARGE_CODES_SUCCESS = '[ChargeCode] Find ChargeCodes Success';
  findChargeCodesSuccess(chargeCodes): Action {
    console.log("findChargeCodesSuccess");
    console.log("chargeCodes: " + chargeCodes.length);
    return {
      type: DebitNoteActions.FIND_CHARGE_CODES_SUCCESS,
      payload: chargeCodes
    };
  }

  static FIND_CHARGE_CODE = '[ChargeCode] Find ChargeCode';
  findChargeCode(code): Action {
    return {
      type: DebitNoteActions.FIND_CHARGE_CODE,
      payload: code
    };
  }

  static FIND_CHARGE_CODE_SUCCESS = '[ChargeCode] Find ChargeCode Success';
  findChargeCodeSuccess(chargeCode): Action {
    console.log("findChargeCodeSuccess");
    return {
      type: DebitNoteActions.FIND_CHARGE_CODE_SUCCESS,
      payload: chargeCode
    };
  }

  static SAVE_CHARGE_CODE = '[ChargeCode] Save ChargeCode';
  saveChargeCode(chargeCode): Action {
    return {
      type: DebitNoteActions.SAVE_CHARGE_CODE,
      payload: chargeCode
    };
  }

  static SAVE_CHARGE_CODE_SUCCESS = '[ChargeCode] Save ChargeCode Success';
  saveChargeCodeSuccess(chargeCode): Action {
    return {
      type: DebitNoteActions.SAVE_CHARGE_CODE_SUCCESS,
      payload: chargeCode
    };
  }

  static UPDATE_CHARGE_CODE = '[ChargeCode] Update ChargeCode';
  updateChargeCode(chargeCode): Action {
    return {
      type: DebitNoteActions.UPDATE_CHARGE_CODE,
      payload: chargeCode
    };
  }

  static UPDATE_CHARGE_CODE_SUCCESS = '[ChargeCode] Update ChargeCode Success';
  updateChargeCodeSuccess(chargeCode): Action {
    return {
      type: DebitNoteActions.UPDATE_CHARGE_CODE_SUCCESS,
      payload: chargeCode
    };
  }

  static REMOVE_CHARGE_CODE = '[ChargeCode] Remove ChargeCode';
  removeChargeCode(chargeCode): Action {
    return {
      type: DebitNoteActions.REMOVE_CHARGE_CODE,
      payload: chargeCode
    };
  }

  static REMOVE_CHARGE_CODE_SUCCESS = '[ChargeCode] Remove ChargeCode Success';
  removeChargeCodeSuccess(chargeCode): Action {
    return {
      type: DebitNoteActions.REMOVE_CHARGE_CODE_SUCCESS,
      payload: chargeCode
    };
  }
}
