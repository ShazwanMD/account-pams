import {Action} from '@ngrx/store';
import {SetupActions} from '../setup.action';
import { TaxCode } from '../../../shared/model/common/tax-code.interface';

export type TaxCodeByActiveListState = TaxCode[];

const initialState: TaxCodeByActiveListState = <TaxCode[]>[];

export function taxCodeByActiveListReducer(state = initialState, action: Action): TaxCodeByActiveListState {
  switch (action.type) {
    case SetupActions.FIND_TAX_CODES_BY_ACTIVE_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
