import {Action} from '@ngrx/store';
import {CommonActions} from "../common.action";
import {TaxCode} from "./tax-code.interface";

export type TaxCodeListState = TaxCode[];

const initialState: TaxCodeListState = <TaxCode[]>[];

export function taxCodeListReducer(state = initialState, action: Action): TaxCodeListState {
  switch (action.type) {
    case CommonActions.FIND_TAX_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
