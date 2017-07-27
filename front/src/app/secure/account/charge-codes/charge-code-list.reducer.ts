import {Action} from '@ngrx/store';
import {ChargeCodeActions} from './charge-code.action';
import {ChargeCode} from '../../../shared/model/account/charge-code.interface';

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
