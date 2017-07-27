import {Action} from '@ngrx/store';
import {SettlementActions} from './settlement.action';
import {SettlementItem} from '../../../shared/model/financialaid/settlement-item.interface';

export type SettlementItemListState = SettlementItem[];

const initialState: SettlementItemListState = <SettlementItem[]>[];

export function settlementItemListReducer(state = initialState, action: Action): SettlementItemListState {
  switch (action.type) {
    case SettlementActions.FIND_SETTLEMENT_ITEMS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
