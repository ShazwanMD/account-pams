import {Action} from '@ngrx/store';
import {ChargeCodeActions} from "./charge-code.action";
import {ChargeCode} from "./charge-code.interface";

export type ChargeCodeState = ChargeCode;

const initialState: ChargeCodeState = <ChargeCode>{};

export function chargeCodeReducer(state = initialState, action: Action): ChargeCodeState {
  switch (action.type) {
    case ChargeCodeActions.FIND_CHARGE_CODE_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
