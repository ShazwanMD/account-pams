import {Action} from '@ngrx/store';

import * as _ from 'lodash';
import {PromoCodeActions} from './promo-code.action';
import {PromoCodeItem} from '../../../shared/model/marketing/promo-code-item.interface';

export type PromoCodeItemListState = PromoCodeItem[];

const initialState: PromoCodeItemListState = <PromoCodeItem[]>[];

export function promoCodeItemListReducer(state = initialState, action: Action): PromoCodeItemListState {
  switch (action.type) {
    case PromoCodeActions.FIND_PROMO_CODE_ITEMS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
