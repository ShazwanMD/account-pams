import { TaxCode } from './../../common/tax-codes/tax-code.interface';
import {Action} from '@ngrx/store';
import {SetupActions} from "../setup.action";

export type TaxCodeListState = TaxCode[];

const initialState: TaxCodeListState = <TaxCode[]>[];

export function taxCodeListReducer(state = initialState, action: Action): TaxCodeListState {
  switch (action.type) {
    case SetupActions.FIND_TAX_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}