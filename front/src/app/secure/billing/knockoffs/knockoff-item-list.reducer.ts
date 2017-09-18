import {Action} from '@ngrx/store';

import * as _ from 'lodash';
import {KnockoffItem} from '../../../shared/model/billing/knockoff-item.interface';
import {KnockoffActions} from './knockoff.action';

export type KnockoffItemListState = KnockoffItem[];

const initialState: KnockoffItemListState = <KnockoffItem[]>[];

export function knockoffItemListReducer(state = initialState, action: Action): KnockoffItemListState {
  switch (action.type) {
    case KnockoffActions.FIND_KNOCKOFF_ITEM_SUCCESS: {
      return action.payload;
    }
    case KnockoffActions.FIND_KNOCKOFF_ITEM_BY_INVOICE_SUCCESS: {
        return action.payload;
      }
    default: {
      return state;
    }
  }
}