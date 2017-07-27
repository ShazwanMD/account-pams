import {Action} from '@ngrx/store';
import {PromoCodeActions} from './promo-code.action';
import {PromoCode} from '../../../shared/model/marketing/promo-code.interface';

export type PromoCodeListState = PromoCode[];

const initialState: PromoCodeListState = <PromoCode[]>[];

export function promoCodeListReducer(state = initialState, action: Action): PromoCodeListState {
  switch (action.type) {
    case PromoCodeActions.FIND_PROMO_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
