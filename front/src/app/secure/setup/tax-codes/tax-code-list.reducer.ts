import {Action} from '@ngrx/store';
import {SetupActions} from '../setup.action';
import {TaxCode} from '../../../shared/model/common/tax-code.interface';

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
