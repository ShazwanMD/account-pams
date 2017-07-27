import {Action} from '@ngrx/store';
import {SettlementActions} from './settlement.action';
import {Settlement} from '../../../shared/model/financialaid/settlement.interface';

export type SettlementListState = Settlement[];

const initialState: SettlementListState = <Settlement[]>[];

export function settlementListReducer(state = initialState, action: Action): SettlementListState {
  switch (action.type) {
    case SettlementActions.FIND_SETTLEMENTS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
