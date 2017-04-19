import {Action} from '@ngrx/store';
import {PromoCode} from "./promo-code.interface";
import {PromoCodeActions} from "./promo-code.action";

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