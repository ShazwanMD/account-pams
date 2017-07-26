import {Action} from '@ngrx/store';
import {SettlementActions} from './settlement.action';
import {Settlement} from '../../shared/model/financialaid/settlement.interface';

export type SettlementState = Settlement;

const initialState: SettlementState = <SettlementState>{};

export function settlementReducer(state = initialState, action: Action): SettlementState {
  switch (action.type) {
    case SettlementActions.FIND_SETTLEMENT_BY_ID_SUCCESS: {
      return action.payload;
    }
    case SettlementActions.FIND_SETTLEMENT_BY_REFERENCE_NO_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
