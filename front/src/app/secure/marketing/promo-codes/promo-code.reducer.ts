import {Action} from '@ngrx/store';
import {PromoCodeActions} from './promo-code.action';
import {PromoCode} from '../../../shared/model/marketing/promo-code.interface';

export type PromoCodeState = PromoCode;

const initialState: PromoCodeState = <PromoCodeState>{};

export function promoCodeReducer(state = initialState, action: Action): PromoCodeState {
  switch (action.type) {
    case PromoCodeActions.FIND_PROMO_CODE_BY_ID_SUCCESS: {
      return action.payload;
    }
    case PromoCodeActions.FIND_PROMO_CODE_BY_REFERENCE_NO_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
