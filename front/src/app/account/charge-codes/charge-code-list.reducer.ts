import {Action} from '@ngrx/store';
import {ChargeCode} from "./charge-code.interface";
import {ChargeCodeActions} from "./charge-code.action";

export type ChargeCodeListState = ChargeCode[];

const initialState: ChargeCodeListState = <ChargeCode[]>[];

export function chargeCodeListReducer(state = initialState, action: Action): ChargeCodeListState {
  switch (action.type) {
    case ChargeCodeActions.FIND_CHARGE_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
