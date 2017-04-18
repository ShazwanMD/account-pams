import {Action} from '@ngrx/store';
import {Observable} from 'rxjs/Observable';

import * as _ from 'lodash';
import {PromoCodeActions} from "./promo-code.action";
import {PromoCodeItem} from "./promo-code-item.interface";
import {state} from "@angular/core";

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
